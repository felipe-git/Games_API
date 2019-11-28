package com.felipe.gamesapp.services.main;

import com.felipe.gamesapp.entities.main.User;

public interface IUserService {

	public User findUser(String userName);

	public User createUser(String userName);

	public User joinToRoom(int userId, int roomId) throws Exception;
}
