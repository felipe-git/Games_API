/* com.felipe.gamesapp.authorization.validation.PasswordMatchesValidator (28 lis 2019)
 * 
 * PasswordMatchesValidator.java
 * 
 * Copyright 2019 EXPERT SOLUTIONS Sp. z o. o.
 */
package com.felipe.gamesapp.authorization.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.felipe.gamesapp.authorization.web.viewModel.UserView;


/**
 * 
 * 
 * @author <a href="mailto:pfilipek@exso.pl">Patryk Filipek</a>
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserView user = (UserView) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}