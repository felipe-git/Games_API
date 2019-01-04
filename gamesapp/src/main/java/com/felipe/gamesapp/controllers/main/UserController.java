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

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path="/allusers")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping(path="/newuser")
	public @ResponseBody String createUser (@RequestParam String userName) {
		
		User userExists = userRepository.findByName(userName);
		if(userExists != null) {
			return "User with name: " + userName + " already exists.";
		}
		
		User user = new User();
		user.setName(userName);
		userRepository.save(user);
		
		return "User with name: " + userName + " has been created.";
	}
}
