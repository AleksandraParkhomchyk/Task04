package com.epam.tr.task04.paymentsapp.services.validator;

public interface PaymentValidator {
    void validate(String accountNumber, Double amount) throws ValidatorException;
}
