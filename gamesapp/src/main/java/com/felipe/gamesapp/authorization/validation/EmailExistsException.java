/*
 * com.felipe.gamesapp.authorization.validation.EmailExistsException (28 lis
 * 2019)
 * 
 * EmailExistsException.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.validation;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

	public EmailExistsException(final String message) {
		super(message);
	}
}
