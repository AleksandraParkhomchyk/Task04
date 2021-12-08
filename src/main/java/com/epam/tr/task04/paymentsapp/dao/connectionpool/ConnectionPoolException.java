package com.epam.tr.task04.paymentsapp.dao.connectionpool;

public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

}
