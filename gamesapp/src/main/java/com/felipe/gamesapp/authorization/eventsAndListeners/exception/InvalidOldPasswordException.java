/*
 * com.felipe.gamesapp.authorization.web.exception.InvalidOldPasswordException
 * (2 gru 2019)
 * 
 * InvalidOldPasswordException.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.exception;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public final class InvalidOldPasswordException extends RuntimeException {

	private static final long serialVersionUID = 2501493653295412679L;

	public InvalidOldPasswordException() {
		super();
	}

	public InvalidOldPasswordException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidOldPasswordException(final String message) {
		super(message);
	}

	public InvalidOldPasswordException(final Throwable cause) {
		super(cause);
	}
}
