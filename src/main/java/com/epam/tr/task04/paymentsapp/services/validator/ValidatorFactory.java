package com.epam.tr.task04.paymentsapp.services.validator;

import com.epam.tr.task04.paymentsapp.services.validator.impl.UserValidatorImpl;

public class ValidatorFactory {

    private static final ValidatorFactory instance = new ValidatorFactory();

    private final static UserValidator userValidator = new UserValidatorImpl();

    public ValidatorFactory() {
    }

    public  static ValidatorFactory getInstance(){
        return instance;
    }

    public UserValidator getUserValidator() { return userValidator; }

}
