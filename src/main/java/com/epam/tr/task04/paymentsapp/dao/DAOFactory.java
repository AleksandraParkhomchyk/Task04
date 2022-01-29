package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.impl.AccountDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.CashRequestDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.TransactionDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final AccountDAO accountDAO = new AccountDAOImpl();
    private final TransactionDAO transactionDAO = new TransactionDAOImpl();
    private final CashRequestDAO cashRequestDAO = new CashRequestDAOImpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public AccountDAO getAccountDAO() {
        return accountDAO;
    }
    public TransactionDAO getTransactionDAO() { return transactionDAO; }
    public CashRequestDAO getCashRequestDAO() { return cashRequestDAO; }

    public static DAOFactory getInstance() {
        return instance;
    }
}
