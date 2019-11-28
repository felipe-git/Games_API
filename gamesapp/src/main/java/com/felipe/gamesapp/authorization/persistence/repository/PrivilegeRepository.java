/*
 * com.felipe.gamesapp.authorization.persistence.repository.PrivilegeRepository
 * (28 lis 2019)
 * 
 * PrivilegeRepository.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.gamesapp.authorization.persistence.entity.Privilege;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

	@Override
	void delete(Privilege privilege);
}
