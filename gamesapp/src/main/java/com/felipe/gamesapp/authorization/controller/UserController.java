/*
 * com.felipe.gamesapp.authorization.controller.UserController (28 lis 2019)
 * 
 * UserController.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.felipe.gamesapp.authorization.domain.model.ActiveUserStore;
import com.felipe.gamesapp.authorization.service.IUserService;

/**
 * 
 * 
 * @author <a href="mailto:felipegamesapp@gmail.com">Patryk Filipek</a>
 */
@Controller
public class UserController {

	@Autowired
	ActiveUserStore activeUserStore;

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/loggedUsers", method = RequestMethod.GET)
	public String getLoggedUsers(final Locale locale, final Model model) {
		model.addAttribute("users", activeUserStore.getUsers());
		return "users";
	}

	@RequestMapping(value = "/loggedUsersFromSessionRegistry", method = RequestMethod.GET)
	public String getLoggedUsersFromSessionRegistry(final Locale locale, final Model model) {
		model.addAttribute("users", userService.getUsersFromSessionRegistry());
		return "users";
	}
}
