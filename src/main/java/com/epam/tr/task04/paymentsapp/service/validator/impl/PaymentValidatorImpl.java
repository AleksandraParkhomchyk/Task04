package com.epam.tr.task04.paymentsapp.service.validator.impl;

import com.epam.tr.task04.paymentsapp.service.validator.PaymentValidator;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;

public class PaymentValidatorImpl implements PaymentValidator {
    private static final String ACC_NUMBER_CHECK = "^[0-9]{1,4}$";
    private static final String AMOUNT_CHECK = "^(([0-9]{1,8})|([0-9]{1,8}[.][0-9]{2}))$";

    @Override
    public void validate(String accountNumber, String amount) throws ValidatorException {

        if (!accountNumber.matches(ACC_NUMBER_CHECK)) {
            throw new ValidatorException("Invalid account number!");
        }
        if (!amount.matches(AMOUNT_CHECK)) {
            throw new ValidatorException("Invalid amount!");
        }
    }
}
