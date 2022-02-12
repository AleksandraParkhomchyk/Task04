package com.epam.tr.task04.paymentsapp.services.validator.impl;

import com.epam.tr.task04.paymentsapp.services.validator.CashoutValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;

public class CashoutValidatorImpl implements CashoutValidator {
    private static final String AMOUNT_CHECK = "^(([0-9]{1,8})|([0-9]{1,8}[.][0-9]{2}))$";
    
    @Override
    public void validate(String amount) throws ValidatorException {

        if (!amount.matches(AMOUNT_CHECK)) {
            throw new ValidatorException("Invalid amount!");
        }
    }
}
