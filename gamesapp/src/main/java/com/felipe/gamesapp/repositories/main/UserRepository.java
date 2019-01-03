package com.felipe.gamesapp.repositories.main;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.main.User;

public interface UserRepository extends CrudRepository<User, Integer> { 

	public User findByName(String name);
}
