/*
 * com.felipe.gamesapp.authorization.web.viewModel.PasswordView (28 lis 2019)
 * 
 * PasswordView.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.web.viewModel;

import com.felipe.gamesapp.authorization.validation.ValidPassword;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public class PasswordView {

	private String oldPassword;

	@ValidPassword
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
