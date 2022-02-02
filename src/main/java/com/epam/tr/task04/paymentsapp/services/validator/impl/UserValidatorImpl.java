package com.epam.tr.task04.paymentsapp.services.validator.impl;

import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.validator.UserValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;

public class UserValidatorImpl implements UserValidator {
    private static final String NAME_CHECK = "^[a-zA-Zа-яА-Я]{2,20}$";
    private static final String SURNAME_CHECK = "^[a-zA-Zа-яА-Я]{2,20}$";
    private static final String LOGIN_CHECK = "^[a-zA-Z0-9]{2,20}$";
    private static final String PASSWORD_CHECK = "^.{3,15}$";
    private static final String PASSPORT_CHECK = "^[A-Z]{2}[0-9]{7}$";

    @Override
    public void validate(User user) throws ValidatorException {
        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String password = user.getPassword();
        String passport = user.getPassport();

        if (!name.matches(NAME_CHECK)) {
            throw new ValidatorException("Invalid name!");
        }
        if (!surname.matches(SURNAME_CHECK)) {
            throw new ValidatorException("Invalid surname!");
        }
        if (!login.matches(LOGIN_CHECK)) {
            throw new ValidatorException("Invalid login!");
        }
        if (!password.matches(PASSWORD_CHECK)) {
            throw new ValidatorException("Invalid password!");
        }
        if (!passport.matches(PASSPORT_CHECK)) {
            throw new ValidatorException("Invalid passport number!");
        }
    }
}
