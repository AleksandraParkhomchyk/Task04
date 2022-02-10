package com.epam.tr.task04.paymentsapp.services.validator;

public interface CashoutValidator {
    void validate(String amount) throws ValidatorException;
}
