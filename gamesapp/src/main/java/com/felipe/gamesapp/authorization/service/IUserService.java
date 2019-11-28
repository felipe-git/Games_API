/*
 * com.felipe.gamesapp.authorization.service.IUserService (28 lis 2019)
 * 
 * IUserService.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import com.felipe.gamesapp.authorization.persistence.entity.PasswordResetToken;
import com.felipe.gamesapp.authorization.persistence.entity.User;
import com.felipe.gamesapp.authorization.persistence.entity.VerificationToken;
import com.felipe.gamesapp.authorization.web.exception.UserAlreadyExistException;
import com.felipe.gamesapp.authorization.web.viewModel.UserView;

/**
 * 
 * 
 * @author <a href="mailto:felipegamesapp@gmail.com">Patryk Filipek</a>
 */
public interface IUserService {

	User registerNewUserAccount(UserView accountDto) throws UserAlreadyExistException;

	User getUser(String verificationToken);

	void saveRegisteredUser(User user);

	void deleteUser(User user);

	void createVerificationTokenForUser(User user, String token);

	VerificationToken getVerificationToken(String VerificationToken);

	VerificationToken generateNewVerificationToken(String token);

	void createPasswordResetTokenForUser(User user, String token);

	User findUserByEmail(String email);

	PasswordResetToken getPasswordResetToken(String token);

	User getUserByPasswordResetToken(String token);

	Optional<User> getUserByID(long id);

	void changeUserPassword(User user, String password);

	boolean checkIfValidOldPassword(User user, String password);

	String validateVerificationToken(String token);

	String generateQRUrl(User user) throws UnsupportedEncodingException;

	User updateUser2FA(boolean use2FA);

	List<String> getUsersFromSessionRegistry();
}
