package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.services.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {}


    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance(){
        return instance;
    }
}
