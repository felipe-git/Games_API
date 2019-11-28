/*
 * com.felipe.gamesapp.authorization.persistence.repository.RoleRepository (28
 * lis 2019)
 * 
 * RoleRepository.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.gamesapp.authorization.persistence.entity.Role;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

	@Override
	void delete(Role role);
}
