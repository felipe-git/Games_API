/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.
 * OnRegistrationCompleteEvent (2 gru 2019)
 * 
 * OnRegistrationCompleteEvent.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.felipe.gamesapp.authorization.persistence.entity.User;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private final String appUrl;
	private final Locale locale;
	private final User user;

	public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
		super(user);
		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public User getUser() {
		return user;
	}
}
