/*
 * com.felipe.gamesapp.authorization.web.exception.UserAlreadyExistException (28
 * lis 2019)
 * 
 * UserAlreadyExistException.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.exception;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public final class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 4457028685495689252L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistException(final String message) {
		super(message);
	}

	public UserAlreadyExistException(final Throwable cause) {
		super(cause);
	}

}
