/*
 * com.felipe.gamesapp.authorization.eventsAndListeners.model.ActiveUserStore (2
 * gru 2019)
 * 
 * ActiveUserStore.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public class ActiveUserStore {

	public List<String> users;

	public ActiveUserStore() {
		users = new ArrayList<String>();
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
