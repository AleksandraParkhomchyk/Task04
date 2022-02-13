package com.epam.tr.task04.paymentsapp.service.exception;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(Exception e) {
        super(e);
    }

    public InsufficientFundsException(String message, Exception e) {
        super(message, e);
    }
}
