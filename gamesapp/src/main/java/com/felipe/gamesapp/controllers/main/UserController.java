package com.felipe.gamesapp.controllers.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.main.UserRepository;
import com.felipe.gamesapp.services.main.IUserService;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IUserService userService;
	
	@GetMapping(path="/allusers")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/getuser")
	public @ResponseBody User getUser(@RequestParam String userName) {
		return userRepository.findByName(userName);
	}

	@PostMapping(path="/newuser")
	public @ResponseBody User createUser(@RequestParam String userName) {

		User user = userService.createUser(userName);
		
		return user;
	}

	@PostMapping(path="/jointoroom")
	public @ResponseBody User joinToRoom(@RequestParam int userId, @RequestParam int roomId) throws Exception {
		
		User user = userService.joinToRoom(userId, roomId);
		
		return user;
	}
}
