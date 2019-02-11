package com.felipe.gamesapp.controllers.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felipe.gamesapp.constants.Games;
import com.felipe.gamesapp.entities.main.Room;
import com.felipe.gamesapp.repositories.main.RoomRepository;

@Controller
@RequestMapping(path="/room")
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping(path="/allrooms")
	public @ResponseBody Iterable<Room> getAllRooms() {
		return roomRepository.findAll();
	}
	
	@GetMapping(path="/getroom")
	public @ResponseBody Room getRoom(@RequestParam String roomName,
			@RequestParam int gameId) {
		return roomRepository.findByNameAndGameId(roomName, gameId);
	}

	@PostMapping(path="/newroom")
	public @ResponseBody Room createRoom(@RequestParam String roomName,
			@RequestParam int ownerId, @RequestParam Games game) {
		
		Room roomExists = roomRepository.findByNameAndGameId(roomName, game.getValue());
		if(roomExists != null) {
			return roomExists;
		}
		
		Room room = new Room();
		room.setName(roomName);
		room.setOwnerId(ownerId);
		room.setGameId(game.getValue());
		roomRepository.save(room);
		
		return room;
	}
}
