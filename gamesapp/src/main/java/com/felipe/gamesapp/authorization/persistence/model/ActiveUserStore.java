/*
 * com.felipe.gamesapp.authorization.persistence.model.ActiveUserStore (28 lis
 * 2019)
 * 
 * ActiveUserStore.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.persistence.model;

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
