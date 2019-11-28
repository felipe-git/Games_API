/*
 * com.felipe.gamesapp.authorization.persistence.repository.
 * PasswordResetTokenRepository (28 lis 2019)
 * 
 * PasswordResetTokenRepository.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.persistence.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.felipe.gamesapp.authorization.persistence.entity.PasswordResetToken;
import com.felipe.gamesapp.authorization.persistence.entity.User;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

	PasswordResetToken findByUser(User user);

	Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

	void deleteByExpiryDateLessThan(Date now);

	@Modifying
	@Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
	void deleteAllExpiredSince(Date now);
}
