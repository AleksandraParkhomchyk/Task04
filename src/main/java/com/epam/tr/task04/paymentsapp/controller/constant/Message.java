package com.epam.tr.task04.paymentsapp.controller.constant;

public final class Message {

    private Message() {
    }

    public static final String MESSAGE_TO_USER = "message";

    public static final String SUCCESS_PAYMENT = "payment.success";

    public static final String WRONG_LOGIN_PASSWORD = "wrong.login.or.password";
    public static final String FAILURE_PAYMENT = "You have insufficient funds";
    public static final String SUCCESS_CASHOUT = "Cashout request was made successful";
    public static final String FAILURE_CASHOUT = "You have insufficient funds";
    public static final String CREATE_ACCOUNT = "Please, create an account.";
    public static final String ACCOUNT_CREATED = "Your account created successful";
    public static final String ACCOUNT_EXIST = "You have an account already";
    public static final String ACCOUNT_BLOCKED = "Your account is blocked. Please contact your admin";
    public static final String INVALID_ACCOUNT_OR_AMOUNT = "Invalid target account number or amount";
    public static final String INVALID_AMOUNT = "Invalid amount";
}
