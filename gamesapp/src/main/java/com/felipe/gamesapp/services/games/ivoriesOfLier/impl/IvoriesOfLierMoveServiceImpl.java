package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierCubeRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierGameRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierMoveRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierPlayerRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierCubeService;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierMoveService;

@Service
public class IvoriesOfLierMoveServiceImpl implements IIvoriesOfLierMoveService {

	@Autowired
	private IvoriesOfLierGameRepository gameRepository;
	@Autowired
	private IvoriesOfLierMoveRepository moveRepository;
	@Autowired
	private IvoriesOfLierCubeRepository cubeRepository;
	@Autowired
	private IvoriesOfLierPlayerRepository playerRepository;

	@Autowired
	private IIvoriesOfLierCubeService cubeService;
	
	@Override
	@Transactional
	public String makeMove(int playerId, int gameId, IvoriesOfLierMove move) throws Exception {
		IvoriesOfLierGame game = gameRepository.findById(gameId).get();
		
		// TODO: sprawdzić czy to rzeczywiście tego gracza kolej
		// jeśli nie, to sprawdzić czy jest jeszcze w pokoju
		// jeśli nie, to ustawić następnego w kolejności

		invalidateMove(move, game);

		IvoriesOfLierPlayer currentPlayer = game.getPlayers().stream()
				.filter(g -> g.getId().equals(playerId)).findFirst().orElse(null);

		move.setGame(game);
		move.setPlayer(currentPlayer);
		moveRepository.save(move);
		
		return makeMove(move, game);
	}

	private String makeMove(IvoriesOfLierMove move, IvoriesOfLierGame game) throws Exception {
		List<IvoriesOfLierPlayer> activePlayers = game.getPlayers();

		List<IvoriesOfLierCube> cubes = cubeService.getPlayerCubesByValue(activePlayers, move.getDecisionCubeValue());
		switch(move.getDecisionType()) {
			case AT_LEAST:
				return nextPlayerMove(game, activePlayers);
			case LESS_THAN:
				if(game.getPlayerIdPreviousMove() == null) {
					throw new Exception("First move in game!");
				}
				if(cubes.size() < move.getDecisionNumberOfCubes()) {
					return previousPlayerLost(game, activePlayers);
				} else {
					return currentPlayerLost(game, activePlayers);
				}
			case EXACTLY:
				if(cubes.size() == move.getDecisionNumberOfCubes()) {
					return currentPlayerWon(game, activePlayers);
				} else {
					return currentPlayerLost(game, activePlayers);
				}
		}
		return "Nothing happened!";
	}

	private String nextPlayerMove(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> activePlayers) {

		final int previousPlayerOrder;
		IvoriesOfLierPlayer currentPlayer = activePlayers.stream().filter(g -> g.getId().equals(game.getPlayerIdNextMove())).findFirst().orElse(null);
		if(currentPlayer == null) {
			// gracz przed chwilą odpadł z gry
			IvoriesOfLierPlayer previousPlayer = activePlayers.stream().filter(g -> g.getId().equals(game.getPlayerIdPreviousMove())).findFirst().orElse(null);
			previousPlayerOrder = previousPlayer.getOrderValue();
		} else {
			Integer userIdCurrentMove = game.getPlayerIdNextMove();
			// przypisanie bieżącego gracza do poprzedniego ruchu
			game.setPlayerIdPreviousMove(userIdCurrentMove);
			previousPlayerOrder = currentPlayer.getOrderValue();
		}

		IvoriesOfLierPlayer nextPlayer = activePlayers.stream().filter(g -> g.getOrderValue() > previousPlayerOrder)
				.findFirst().orElse(activePlayers.get(0));
		game.setPlayerIdNextMove(nextPlayer.getId());
		
		gameRepository.save(game);

		return "";
	}

	private String previousPlayerLost(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {

		Integer userIdPreviousMove = game.getPlayerIdPreviousMove();
		IvoriesOfLierPlayer previousPlayer = playingGamers.stream().filter(g -> g.getId().equals(userIdPreviousMove)).findFirst().orElse(null);

		takeAwayOneCube(previousPlayer, playingGamers);
		
		if(playingGamers.size() == 1) {
			return "Game over! Player " + playingGamers.get(0).getUser().getName() + " won!";
		}
		
		nextPlayerMove(game, playingGamers);

		game.setPlayerIdPreviousMove(null);
		gameRepository.save(game);

		drawCubes(playingGamers);

		return "Player " + previousPlayer.getUser().getName() + " lost one cube!";
	}

	private String currentPlayerLost(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {

		Integer userIdCurrentMove = game.getPlayerIdNextMove();
		IvoriesOfLierPlayer currentPlayer = playingGamers.stream().filter(g -> g.getId().equals(userIdCurrentMove)).findFirst().orElse(null);

		takeAwayOneCube(currentPlayer, playingGamers);

		if(playingGamers.size() == 1) {
			return "Game over! Player " + playingGamers.get(0).getUser().getName() + " won!";
		}
		
		nextPlayerMove(game, playingGamers);

		game.setPlayerIdPreviousMove(null);
		gameRepository.save(game);

		drawCubes(playingGamers);

		return "Player " + currentPlayer.getUser().getName() + " lost one cube!";
	}

	private String currentPlayerWon(IvoriesOfLierGame game, List<IvoriesOfLierPlayer> playingGamers) {

		Integer userIdCurrentMove = game.getPlayerIdNextMove();

		List<IvoriesOfLierPlayer> otherPlayers = playingGamers.stream().filter(g -> !g.getId().equals(userIdCurrentMove))
				.collect(Collectors.toList());
		for(IvoriesOfLierPlayer otherPlayer : otherPlayers) {
			takeAwayOneCube(otherPlayer, playingGamers);
		}

		if(playingGamers.size() == 1) {
			return "Game over! Player " + playingGamers.get(0).getUser().getName() + " won!";
		}

		game.setPlayerIdPreviousMove(null);
		gameRepository.save(game);

		drawCubes(playingGamers);

		IvoriesOfLierPlayer wonPlayer = playingGamers.stream().filter(g -> g.getId().equals(userIdCurrentMove)).findFirst().orElse(null);
		return "Player " + wonPlayer.getUser().getName() + " won! Others lost one cube.";
	}

	private void takeAwayOneCube(IvoriesOfLierPlayer player, List<IvoriesOfLierPlayer> playingGamers) {
		
		IvoriesOfLierCube cubeToTake = player.getCubes().get(0);
		player.getCubes().remove(0);
		cubeRepository.delete(cubeToTake);
		
		if(player.getCubes().isEmpty()) {
			// gracz bez kości odpada z gry
			playingGamers.remove(player);
			player.setPlaying(false);
			playerRepository.save(player);
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

	private void invalidateMove(IvoriesOfLierMove move, IvoriesOfLierGame game) throws Exception {
		if(game.getPlayerIdPreviousMove() != null) {
			IvoriesOfLierMove lastMove = moveRepository.findFirstByPlayerIdAndGameIdOrderByIdDesc(game.getPlayerIdPreviousMove(), game.getId());
			switch(move.getDecisionType()) {
				case AT_LEAST:
					if(move.getDecisionNumberOfCubes() < lastMove.getDecisionNumberOfCubes()) {
						throw new Exception("Number of cubes must be greater!");
					}
					if(move.getDecisionNumberOfCubes().equals(lastMove.getDecisionNumberOfCubes())
							&& move.getDecisionCubeValue() <= lastMove.getDecisionCubeValue()) {
						throw new Exception("Cube value must be greater!");
					}
					break;
				case EXACTLY:
					if(move.getDecisionNumberOfCubes() < lastMove.getDecisionNumberOfCubes()) {
						throw new Exception("Number of cubes must be greater!");
					}
					if(move.getDecisionNumberOfCubes().equals(lastMove.getDecisionNumberOfCubes())
							&& move.getDecisionCubeValue() < lastMove.getDecisionCubeValue()) {
						throw new Exception("Cube value must be greater!");
					}
					break;
				default:
					break;
			}
		}
	}
}
