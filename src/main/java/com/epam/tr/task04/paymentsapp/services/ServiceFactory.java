package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.services.impl.AccountServiceImpl;
import com.epam.tr.task04.paymentsapp.services.impl.CardServiceImpl;
import com.epam.tr.task04.paymentsapp.services.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private final CardService cardService = new CardServiceImpl();
    //
    //
    //
    //

    private ServiceFactory() {}

    public UserService getUserService() {
        return userService;
    }
    public AccountService getAccountService() { return accountService;  }
    public CardService getCardService() { return cardService;  }

    public static ServiceFactory getInstance(){
        return instance;
    }
}
