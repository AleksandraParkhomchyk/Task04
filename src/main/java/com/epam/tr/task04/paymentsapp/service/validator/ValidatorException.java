package com.epam.tr.task04.paymentsapp.service.validator;

public class ValidatorException extends Exception {

    public ValidatorException() {
        super();
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(Exception e) {
        super(e);
    }

    public ValidatorException(String message, Exception e) {
        super(message, e);
    }

}
