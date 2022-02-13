package com.epam.tr.task04.paymentsapp.service.validator;

import com.epam.tr.task04.paymentsapp.service.validator.impl.CashoutValidatorImpl;
import com.epam.tr.task04.paymentsapp.service.validator.impl.PaymentValidatorImpl;
import com.epam.tr.task04.paymentsapp.service.validator.impl.UserValidatorImpl;

public class ValidatorFactory {

    private static final ValidatorFactory instance = new ValidatorFactory();

    private static final UserValidator userValidator = new UserValidatorImpl();
    private static final PaymentValidator paymentValidator = new PaymentValidatorImpl();
    private static final CashoutValidator cashoutValidator = new CashoutValidatorImpl();

    public ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public PaymentValidator getPaymentValidator() {
        return paymentValidator;
    }

    public CashoutValidator getCashoutValidator() {
        return cashoutValidator;
    }

}
