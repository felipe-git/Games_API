/*
 * com.felipe.gamesapp.authorization.persistence.repository.
 * VerificationTokenRepository (28 lis 2019)
 * 
 * VerificationTokenRepository.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.persistence.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.felipe.gamesapp.authorization.persistence.entity.User;
import com.felipe.gamesapp.authorization.persistence.entity.VerificationToken;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);

	Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);

	void deleteByExpiryDateLessThan(Date now);

	@Modifying
	@Query("delete from VerificationToken t where t.expiryDate <= ?1")
	void deleteAllExpiredSince(Date now);
}
