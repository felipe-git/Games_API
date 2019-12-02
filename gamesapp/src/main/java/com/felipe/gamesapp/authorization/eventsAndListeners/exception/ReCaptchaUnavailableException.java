/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.exception.
 * ReCaptchaUnavailableException (2 gru 2019)
 * 
 * ReCaptchaUnavailableException.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.exception;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public final class ReCaptchaUnavailableException extends RuntimeException {

	private static final long serialVersionUID = -3492039961161741692L;

	public ReCaptchaUnavailableException() {
		super();
	}

	public ReCaptchaUnavailableException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ReCaptchaUnavailableException(final String message) {
		super(message);
	}

	public ReCaptchaUnavailableException(final Throwable cause) {
		super(cause);
	}
}
