package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.impl.AccountDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final AccountDAO accountDAO = new AccountDAOImpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
