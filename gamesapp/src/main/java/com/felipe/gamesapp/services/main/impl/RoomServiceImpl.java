package com.felipe.gamesapp.services.main.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.constants.Games;
import com.felipe.gamesapp.entities.main.Room;
import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.main.RoomRepository;
import com.felipe.gamesapp.services.main.IRoomService;

public class RoomServiceImpl implements IRoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	@Transactional
	public Room createRoomOrGetIfExist(String name, int ownerId, Games game) {

		Room roomExists = roomRepository.findByNameAndGameId(name, game.getValue());
		if(roomExists != null) {
			return roomExists;
		}
		
		Room room = new Room();
		room.setName(name);
		room.setOwnerId(ownerId);
		room.setGame(game.getValue());
		roomRepository.save(room);
		
		return room;
	}

	@Override
	@Transactional
	public Room getRoom(int roomId) throws Exception {
		Optional<Room> roomOpt = roomRepository.findById(roomId);
		if(roomOpt == null || !roomOpt.isPresent()) {
			throw new Exception("Room with id = " + roomId + " does not exist!");
		}
		Room room = roomOpt.get();
		if(room.isActiveGame()) {
			throw new Exception("Game is being played!");
		}
		
		Set<User> usersInRoom = room.getUsersInRoom();
		if(usersInRoom == null || usersInRoom.size() < 2) {
			throw new Exception("Not enough users in room!");
		}
		
		return room;
	}
}
