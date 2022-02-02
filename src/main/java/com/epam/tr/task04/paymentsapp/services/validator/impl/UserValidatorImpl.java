package com.epam.tr.task04.paymentsapp.services.validator.impl;

import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.validator.UserValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;

public class UserValidatorImpl implements UserValidator {
    private static final String NAME_CHECK = "^[a-zA-Zа-яА-Я]{2,20}$";
    private static final String SURNAME_CHECK = "^[a-zA-Zа-яА-Я]{2,20}$";

    @Override
    public void validate(User user) throws ValidatorException {
        String name = user.getName();
        String surname = user.getSurname();

        if (!name.matches(NAME_CHECK)) {
            throw new ValidatorException("Invalid name!");
        }
        if (!surname.matches(SURNAME_CHECK)) {
            throw new ValidatorException("Invalid surname!");
        }

    }
}
