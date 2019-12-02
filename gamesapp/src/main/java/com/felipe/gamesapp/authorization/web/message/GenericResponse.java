/*
 * com.felipe.gamesapp.authorization.web.message.GenericResponse (2 gru 2019)
 * 
 * GenericResponse.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.web.message;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public class GenericResponse {
	private String message;
	private String error;

	public GenericResponse(final String message) {
		super();
		this.message = message;
	}

	public GenericResponse(final String message, final String error) {
		super();
		this.message = message;
		this.error = error;
	}

	public GenericResponse(List<ObjectError> allErrors, String error) {
		this.error = error;
		String temp = allErrors.stream().map(e -> {
			if(e instanceof FieldError) {
				return "{\"field\":\"" + ((FieldError)e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage()
						+ "\"}";
			} else {
				return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage()
						+ "\"}";
			}
		}).collect(Collectors.joining(","));
		this.message = "[" + temp + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(final String error) {
		this.error = error;
	}
}
