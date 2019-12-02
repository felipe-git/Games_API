/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.listeners.
 * AuthenticationFailureListener (2 gru 2019)
 * 
 * AuthenticationFailureListener.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.listeners;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.felipe.gamesapp.authorization.service.ILoginAttemptService;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ILoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) {
		// final WebAuthenticationDetails auth = (WebAuthenticationDetails)
		// e.getAuthentication().getDetails();
		// if (auth != null) {
		// loginAttemptService.loginFailed(auth.getRemoteAddress());
		// }
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if(xfHeader == null) {
			loginAttemptService.loginFailed(request.getRemoteAddr());
		} else {
			loginAttemptService.loginFailed(xfHeader.split(",")[0]);
		}
	}

}
