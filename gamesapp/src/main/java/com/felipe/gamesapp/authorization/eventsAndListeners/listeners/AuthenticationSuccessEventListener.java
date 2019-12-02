/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.listeners.
 * AuthenticationSuccessEventListener (2 gru 2019)
 * 
 * AuthenticationSuccessEventListener.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.listeners;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.felipe.gamesapp.authorization.service.impl.LoginAttemptService;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(final AuthenticationSuccessEvent e) {
		// final WebAuthenticationDetails auth = (WebAuthenticationDetails)
		// e.getAuthentication().getDetails();
		// if (auth != null) {
		// loginAttemptService.loginSucceeded(auth.getRemoteAddress());
		// }
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if(xfHeader == null) {
			loginAttemptService.loginSucceeded(request.getRemoteAddr());
		} else {
			loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
		}
	}

}
