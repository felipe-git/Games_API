package com.felipe.gamesapp.controllers.games.ivoriesOfLier;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove.DecisionType;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierCubeRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierGameRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierPlayerRepository;
import com.felipe.gamesapp.repositories.main.RoomRepository;
import com.felipe.gamesapp.repositories.main.UserRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierGameService;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierMoveService;

@Controller
@RequestMapping(path="/games/ivoriesoflier")
public class IvoriesOfLierController {

	@Autowired
	private IvoriesOfLierGameRepository gameRepository;
	@Autowired
	private IvoriesOfLierCubeRepository cubeRepository;
	@Autowired
	private IvoriesOfLierPlayerRepository gamerRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private IIvoriesOfLierGameService gameService;
	@Autowired
	private IIvoriesOfLierMoveService moveService;

	@PostMapping(path="/start")
	public @ResponseBody String startGame(@RequestParam int roomId) throws Exception {
		// utworzenie gry
		IvoriesOfLierGame game = gameService.startGame(roomId);
		
		return "Game started!";
	}

	@PostMapping(path="/move")
	public @ResponseBody String move(@RequestParam int playerId,
			@RequestParam int gameId,
			@RequestParam DecisionType decisionType,
			@RequestParam int numberOfCubes,
			@RequestParam int cubeValue) {
		
		IvoriesOfLierMove move = new IvoriesOfLierMove();
		move.setDecisionType(decisionType);
		move.setDecisionCubeValue(cubeValue);
		move.setDecisionNumberOfCubes(numberOfCubes);
		
		moveService.makeMove(playerId, gameId, move);
		
		// pobranie gry
		IvoriesOfLierGame game = gameRepository.findById(gameId).get();

		// TODO sprawdzić czy ruch jest możliwy
		
		// pobranie wszystkich graczy
		List<IvoriesOfLierPlayer> playingGamers = gamerRepository.findByIvoriesOfLierGameIdAndPlayingOrderByOrderValue(gameId, true);
		// bieżący gracz
		IvoriesOfLierPlayer currentPlayer = playingGamers.stream().filter(g -> g.getId().equals(gamerId)).findFirst().orElse(null);
		// zapis jego ruchu
//		currentPlayer.setDecisionType(decisionType);
//		currentPlayer.setDecisionNumberOfCubes(numberOfCubes);
//		currentPlayer.setDecisionCubeValue(cubeValue);
		gamerRepository.save(currentPlayer);
		
		// pobranie kości danego numeru
		List<IvoriesOfLierCube> cubes = cubeRepository.findByIvoriesOfLierGameIdAndValue(gameId, cubeValue);
		switch(decisionType) {
			case AT_LEAST:
				return nextPlayerMove(game, playingGamers);
			case LESS_THAN:
				if(game.getUserIdPreviousMove() == null) {
					return "błąd! pierwszy ruch w grze!";
				}
				// sprawdzenie czy jest więcej
				if(cubes.size() < numberOfCubes) {
					return previousPlayerLost(game, playingGamers);
				} else {
					return currentPlayerLost(game, playingGamers);
				}
			case EXACTLY:
				// sprawdzenie czy jest dokładnie tyle
				if(cubes.size() == numberOfCubes) {
					return currentPlayerWon(game, playingGamers);
				} else {
					return currentPlayerLost(game, playingGamers);
				}
		}

		return "brak ruchu!";
	}

	private String nextPlayerMove(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {
		// ruch następnego gracza
		
		// szukanie następnego gracza do ruchu
		final int previousPlayerOrder;
		// bieżący gracz
		IvoriesOfLierPlayer currentPlayer = playingGamers.stream().filter(g -> g.getId().equals(game.getUserIdCurrentMove())).findFirst().orElse(null);
		if(currentPlayer == null) {
			// gracz przed chwilą odpadł z gry
			IvoriesOfLierPlayer previousPlayer = playingGamers.stream().filter(g -> g.getId().equals(game.getUserIdCurrentMove())).findFirst().orElse(null);
			previousPlayerOrder = previousPlayer.getOrderValue();
		} else {
			Integer userIdCurrentMove = game.getUserIdCurrentMove();
			// przypisanie bieżącego gracza do poprzedniego ruchu
			game.setUserIdPreviousMove(userIdCurrentMove);
			previousPlayerOrder = currentPlayer.getOrderValue();
		}
		// kolejny gracz lub pierwszy na liście
		IvoriesOfLierPlayer nextPlayer = playingGamers.stream().filter(g -> g.getOrderValue() > previousPlayerOrder)
				.findFirst().orElse(playingGamers.get(0));
		game.setUserIdCurrentMove(nextPlayer.getId());
		
		gameRepository.save(game);

		return "";
	}


	private String previousPlayerLost(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {
		// poprzedni gracz oddaje kość

		Integer userIdPreviousMove = game.getUserIdPreviousMove();
		// poprzedni gracz
		IvoriesOfLierPlayer previousPlayer = playingGamers.stream().filter(g -> g.getId().equals(userIdPreviousMove)).findFirst().orElse(null);

		// zabranie jednej kości
		takeAwayOneCube(previousPlayer, playingGamers);
		
		if(playingGamers.size() == 1) {
			return "Koniec gry";
		}
		
		nextPlayerMove(game, playingGamers);

		// usunięcie poprzedniego ruchu (nowe rozdanie)
		game.setUserIdPreviousMove(null);
		gameRepository.save(game);
		
		// losowanie kości graczom
		drawCubes(playingGamers);

		return "";
	}

	private String currentPlayerLost(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {
		// bieżący gracz oddaje kość

		Integer userIdCurrentMove = game.getUserIdCurrentMove();
		// poprzedni gracz
		IvoriesOfLierPlayer currentPlayer = playingGamers.stream().filter(g -> g.getId().equals(userIdCurrentMove)).findFirst().orElse(null);

		// zabranie jednej kości
		takeAwayOneCube(currentPlayer, playingGamers);

		if(playingGamers.size() == 1) {
			return "Koniec gry";
		}
		
		nextPlayerMove(game, playingGamers);

		// usunięcie poprzedniego ruchu (nowe rozdanie)
		game.setUserIdPreviousMove(null);
		gameRepository.save(game);
		
		// losowanie kości graczom
		drawCubes(playingGamers);

		return "";
	}

	private String currentPlayerWon(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {
		// wszyscy oddają po jednej kości
		
		Integer userIdCurrentMove = game.getUserIdCurrentMove();
		// pozostali gracze
		List<IvoriesOfLierPlayer> otherPlayers = playingGamers.stream().filter(g -> !g.getId().equals(userIdCurrentMove))
				.collect(Collectors.toList());
		for(IvoriesOfLierPlayer otherPlayer : otherPlayers) {
			takeAwayOneCube(otherPlayer, playingGamers);
		}

		if(playingGamers.size() == 1) {
			return "Koniec gry";
		}

		// usunięcie poprzedniego ruchu (nowe rozdanie)
		game.setUserIdPreviousMove(null);
		gameRepository.save(game);
		
		// losowanie kości graczom
		drawCubes(playingGamers);

		return "";
	}

	private void takeAwayOneCube(IvoriesOfLierPlayer player, List<IvoriesOfLierPlayer> playingGamers) {
		
		IvoriesOfLierCube cubeToTake = player.getCubes().get(0);
		player.getCubes().remove(0);
		cubeRepository.delete(cubeToTake);
		
		if(player.getCubes().isEmpty()) {
			// gracz bez kości odpada z gry
			playingGamers.remove(player);
			player.setPlaying(false);
			gamerRepository.save(player);
		}
	}

	private boolean checkIfItsEndGame(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {
		// sprawdzenie czy zostało co najmniej dwóch graczy
		List<IvoriesOfLierPlayer> playingPlayers = playingGamers.stream().filter(g -> g.isPlaying())
				.collect(Collectors.toList());
		if(playingPlayers.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	private void drawCubes(List<IvoriesOfLierPlayer> playingGamers) {
		Random random = new Random();
		for(IvoriesOfLierPlayer gamer : playingGamers) {
			for(IvoriesOfLierCube cube : gamer.getCubes()) {
				cube.setValue(random.nextInt(5) + 1);
				cubeRepository.save(cube);
			}
		}
	}
}
