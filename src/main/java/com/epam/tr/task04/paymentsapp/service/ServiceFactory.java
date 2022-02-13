package com.epam.tr.task04.paymentsapp.service;

import com.epam.tr.task04.paymentsapp.service.impl.AccountServiceImpl;
import com.epam.tr.task04.paymentsapp.service.impl.CashRequestServiceImpl;
import com.epam.tr.task04.paymentsapp.service.impl.TransactionServiceImpl;
import com.epam.tr.task04.paymentsapp.service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private final TransactionService transactionService = new TransactionServiceImpl();
    private final CashRequestService cashRequestService = new CashRequestServiceImpl();

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public CashRequestService getCashRequestService() {
        return cashRequestService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
