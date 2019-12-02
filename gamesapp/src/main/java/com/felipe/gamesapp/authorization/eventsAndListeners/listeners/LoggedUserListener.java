/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.listeners.
 * LoggedUserListener (2 gru 2019)
 * 
 * LoggedUserListener.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.eventsAndListeners.listeners;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

import com.felipe.gamesapp.authorization.domain.model.ActiveUserStore;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
@Component
public class LoggedUserListener implements HttpSessionBindingListener {

	private String username;
	private ActiveUserStore activeUserStore;

	public LoggedUserListener(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }

	public LoggedUserListener() {
    }

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUserListener user = (LoggedUserListener)event.getValue();
		if(!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUserListener user = (LoggedUserListener)event.getValue();
		if(users.contains(user.getUsername())) {
			users.remove(user.getUsername());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
