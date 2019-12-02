/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.exception.
 * ReCaptchaInvalidException (2 gru 2019)
 * 
 * ReCaptchaInvalidException.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.exception;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public final class ReCaptchaInvalidException extends RuntimeException {

	private static final long serialVersionUID = 6982216847897327713L;

	public ReCaptchaInvalidException() {
		super();
	}

	public ReCaptchaInvalidException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ReCaptchaInvalidException(final String message) {
		super(message);
	}

	public ReCaptchaInvalidException(final Throwable cause) {
		super(cause);
	}
}
