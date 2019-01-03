package com.felipe.gamesapp.controllers.games.ivoriesOfLier;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGamer;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGamer.DecisionType;
import com.felipe.gamesapp.entities.main.Room;
import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierCubeRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierGameRepository;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierGamerRepository;
import com.felipe.gamesapp.repositories.main.RoomRepository;
import com.felipe.gamesapp.repositories.main.UserRepository;

@Controller
@RequestMapping(path="/games/ivoriesoflier")
public class IvoriesOfLierController {

	@Autowired
	private IvoriesOfLierGameRepository gameRepository;
	@Autowired
	private IvoriesOfLierCubeRepository cubeRepository;
	@Autowired
	private IvoriesOfLierGamerRepository gamerRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;

	@PutMapping(path="/start")
	public @ResponseBody String startGame(@RequestParam int roomId) {
		
		// TODO: do sprawdzenia
		// 1) czy pokój aktywny
		// 2) czy conajmniej 2 graczy
		// 3) czy pokój ma aktywną grę
		Optional<Room> room = roomRepository.findById(roomId);
		if(!room.isPresent()) {
			
		}
		
		// utworzenie gry
		IvoriesOfLierGame game = new IvoriesOfLierGame();
		game.setRoomId(roomId);
		gameRepository.save(game);
		

		Random random = new Random();
		// utworzenie graczy
		// pobranie ich z pokoju
		Iterable<User> users = userRepository.findAll();
		int order = 1;
		for(User user : users) {
			IvoriesOfLierGamer gamer = new IvoriesOfLierGamer();
			gamer.setUserId(user.getId());
			gamer.setIvoriesOfLierGameId(game.getId());
			gamer.setOrder(order);
			gamerRepository.save(gamer);
			order++;
			// tworzenie kości dla gracza
			for(int i = 0; i <= 5; i++) {
				IvoriesOfLierCube cube = new IvoriesOfLierCube();
				cube.setIvoriesOfLierGameId(game.getId());
				cube.setUserId(gamer.getId());
				cube.setNumber(random.nextInt(5) + 1);
				cubeRepository.save(cube);
			}
		}
		
		// następny ruch
		game.setLastMoveUserId(users.iterator().next().getId());
		gameRepository.save(game);
		
		return "Game started!";
	}

	@PutMapping(path="/move")
	public @ResponseBody String move(@RequestParam int gamerId,
			@RequestParam int gameId,
			@RequestParam DecisionType decisionType,
			@RequestParam int decisionNo,
			@RequestParam int decisionVal) {
		
		// pobranie gry
		Optional<IvoriesOfLierGame> game = gameRepository.findById(gameId);
		// pobranie poprzedniego ruchu
		
		// pobranie kości danego numeru
		List<IvoriesOfLierCube> cubes = cubeRepository.findByUserIdAndIvoriesOfLierGameIdAndNumber(gamerId, gameId, decisionVal);
		switch(decisionType) {
			case AT_LEAST:
				return nextPlayerMove(game.get());
			case LESS_THAN:
				// sprawdzenie czy jest więcej
				if(cubes.size() < decisionNo) {
					return previousPlayerLost(game.get());
				} else {
					return currentPlayerLost(game.get());
				}
			case EXACTLY:
				// sprawdzenie czy jest dokładnie tyle
				if(cubes.size() == decisionNo) {
					return currentPlayerWon(game.get());
				} else {
					return currentPlayerLost(game.get());
				}
		}
		
		
		
		
		return "";
	}

	private String nextPlayerMove(IvoriesOfLierGame game) {
		// ruch następnego gracza

		return "";
	}

	private String previousPlayerLost(IvoriesOfLierGame game) {
		// poprzedni gracz oddaje kość

		return "";
	}

	private String currentPlayerLost(IvoriesOfLierGame game) {
		// bieżący gracz oddaje kość

		return "";
	}

	private String currentPlayerWon(IvoriesOfLierGame game) {
		// wszyscy oddają po jednej kości

		return "";
	}

}
