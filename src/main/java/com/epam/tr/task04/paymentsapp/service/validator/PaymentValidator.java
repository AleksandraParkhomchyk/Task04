package com.epam.tr.task04.paymentsapp.service.validator;

public interface PaymentValidator {

    void validate(String accountNumber, String amount) throws ValidatorException;

}
