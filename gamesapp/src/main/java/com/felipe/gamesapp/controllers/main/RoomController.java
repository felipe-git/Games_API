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
import com.felipe.gamesapp.services.main.IRoomService;

@Controller
@RequestMapping(path="/room")
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private IRoomService roomService;
	
	@GetMapping(path="/allrooms")
	public @ResponseBody Iterable<Room> getAllRooms() {
		return roomRepository.findAll();
	}
	
	@GetMapping(path="/getroom")
	public @ResponseBody Room getRoom(@RequestParam String roomName,
			@RequestParam int game) {
		return roomRepository.findByNameAndGame(roomName, game);
	}

	@PostMapping(path="/newroom")
	public @ResponseBody Room createRoom(@RequestParam String roomName,
			@RequestParam int ownerId, @RequestParam Games game) throws Exception {

		Room room = roomService.createRoomOrGetIfExist(roomName, ownerId, game);
		
		return room;
	}
}
