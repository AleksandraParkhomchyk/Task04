package com.epam.tr.task04.paymentsapp.service.exception;

public class NotAuthorizedException extends ServiceException {

    public NotAuthorizedException() {
        super();
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(Exception e) {
        super(e);
    }

    public NotAuthorizedException(String message, Exception e) {
        super(message, e);
    }
}
