/*
 * com.felipe.gamesapp.authorization.persistence.repository.UserRepository (28
 * lis 2019)
 * 
 * UserRepository.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.gamesapp.authorization.persistence.entity.User;

/**
 * 
 * 
 * @author <a href="mailto:felipegamesapp@gmail.com">Patryk Filipek</a>
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Override
	void delete(User user);

}
