package com.epam.tr.task04.paymentsapp.service.validator;

public interface CashoutValidator {

    void validate(String amount) throws ValidatorException;

}
