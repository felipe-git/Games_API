/*
 * com.felipe.gamesapp.authorization.service.impl.LoginAttemptService (2 gru
 * 2019)
 * 
 * LoginAttemptService.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.service.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.felipe.gamesapp.authorization.service.ILoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@Service
public class LoginAttemptService implements ILoginAttemptService {

	private final int MAX_ATTEMPT = 10;
	private LoadingCache<String, Integer> attemptsCache;

	public LoginAttemptService() {
		super();
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(final String key) {
						return 0;
					}
				});
	}

	@Override
	public void loginSucceeded(final String key) {
		attemptsCache.invalidate(key);
	}

	@Override
	public void loginFailed(final String key) {
		int attempts = 0;
		try {
			attempts = attemptsCache.get(key);
		} catch(final ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		attemptsCache.put(key, attempts);
	}

	@Override
	public boolean isBlocked(final String key) {
		try {
			return attemptsCache.get(key) >= MAX_ATTEMPT;
		} catch(final ExecutionException e) {
			return false;
		}
	}
}
