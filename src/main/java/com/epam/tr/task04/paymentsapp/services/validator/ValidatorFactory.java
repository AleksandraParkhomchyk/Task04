package com.epam.tr.task04.paymentsapp.services.validator;

import com.epam.tr.task04.paymentsapp.services.validator.impl.PaymentValidatorImpl;
import com.epam.tr.task04.paymentsapp.services.validator.impl.UserValidatorImpl;

public class ValidatorFactory {

    private static final ValidatorFactory instance = new ValidatorFactory();

    private final static UserValidator userValidator = new UserValidatorImpl();
    private final static PaymentValidator paymentValidator = new PaymentValidatorImpl();

    public ValidatorFactory() {
    }

    public  static ValidatorFactory getInstance(){
        return instance;
    }

    public UserValidator getUserValidator() { return userValidator; }
    public PaymentValidator getPaymentValidator() { return paymentValidator; }

}
