/*
 * com.felipe.gamesapp.authorization.service.impl.UserSecurityService (2 gru
 * 2019)
 * 
 * UserSecurityService.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.service.impl;

import java.util.Arrays;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.felipe.gamesapp.authorization.persistence.entity.PasswordResetToken;
import com.felipe.gamesapp.authorization.persistence.entity.User;
import com.felipe.gamesapp.authorization.persistence.repository.PasswordResetTokenRepository;
import com.felipe.gamesapp.authorization.service.ISecurityUserService;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@Service
@Transactional
public class UserSecurityService implements ISecurityUserService {

	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	// API

	@Override
	public String validatePasswordResetToken(long id, String token) {
		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		if((passToken == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}

		final Calendar cal = Calendar.getInstance();
		if((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "expired";
		}

		final User user = passToken.getUser();
		final Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

}
