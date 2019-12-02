/* com.felipe.gamesapp.authorization.service.ISecurityUserService (2 gru 2019)
 * 
 * ISecurityUserService.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.service;

/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public interface ISecurityUserService {

	String validatePasswordResetToken(long id, String token);
}
