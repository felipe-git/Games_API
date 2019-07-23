package com.felipe.gamesapp.services.main.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.main.Room;
import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.main.RoomRepository;
import com.felipe.gamesapp.repositories.main.UserRepository;
import com.felipe.gamesapp.services.main.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Override
	@Transactional
	public User createUser(String userName) {
		User userExists = userRepository.findByName(userName);
		if(userExists != null) {
			return userExists;
		}
		
		User user = new User();
		user.setName(userName);
		userRepository.save(user);
		
		return user;
	}

	@Override
	@Transactional
	public User joinToRoom(int userId, int roomId) throws Exception {
		Optional<User> userExists = userRepository.findById(userId);
		if(!userExists.isPresent()) {
			throw new Exception("There is no user with id: " + userId);
		}
		User user = userExists.get();

		Optional<Room> roomExists = roomRepository.findById(roomId);
		if(!roomExists.isPresent()) {
			throw new Exception("There is no room with id: " + roomId);
		}
		Room room = roomExists.get();
		room.getUsersInRoom().add(user);
		roomRepository.save(room);
		
		user = userRepository.findById(userId).get();
		
		return user;
	}
}
