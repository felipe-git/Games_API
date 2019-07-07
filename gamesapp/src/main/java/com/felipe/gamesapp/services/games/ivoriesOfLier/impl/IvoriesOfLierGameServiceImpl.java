package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.main.Room;
import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierGameRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierGameService;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierPlayerService;
import com.felipe.gamesapp.services.main.IRoomService;

public class IvoriesOfLierGameServiceImpl implements IIvoriesOfLierGameService {

	@Autowired
	private IvoriesOfLierGameRepository gameRepository;

	@Autowired
	private IRoomService roomService;
	@Autowired
	private IIvoriesOfLierPlayerService playerService;


	@Override
	@Transactional
	public IvoriesOfLierGame startGame(int roomId) throws Exception {

		Room room = roomService.getRoom(roomId);
		
		IvoriesOfLierGame game = createNewGame(room);
		
		createPlayers(room, game);
		
		// ustawienie pierwszego gracza z listy do ruchu
		game.setPlayerIdNextMove(game.getPlayers().get(0).getId());
		game = gameRepository.save(game);
		
		return game;
	}

	private IvoriesOfLierGame createNewGame(Room room) {
		IvoriesOfLierGame game = new IvoriesOfLierGame();
		game.setRoom(room);
		game = gameRepository.save(game);

		return game;
	}

	private void createPlayers(Room room, IvoriesOfLierGame game) throws Exception {
		Set<User> usersInRoom = room.getUsersInRoom();
		if(usersInRoom == null || usersInRoom.size() < 2) {
			throw new Exception("Not enough users in room!");
		}
		
		int order = 1;
		for(User user : usersInRoom) {
			playerService.createPlayer(user, game, order);
			order++;
		}
	}
}
