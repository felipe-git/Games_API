/*
 * com.felipe.gamesapp.authorization.service.ILoginAttemptService (2 gru 2019)
 * 
 * ILoginAttemptService.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.service;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public interface ILoginAttemptService {

	public void loginSucceeded(final String key);

	public void loginFailed(final String key);

	public boolean isBlocked(final String key);
}
