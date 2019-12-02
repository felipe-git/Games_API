/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.exception.
 * UserNotFoundException (2 gru 2019)
 * 
 * UserNotFoundException.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.exception;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public final class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7966098161435920641L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(final String message) {
		super(message);
	}

	public UserNotFoundException(final Throwable cause) {
		super(cause);
	}
}
