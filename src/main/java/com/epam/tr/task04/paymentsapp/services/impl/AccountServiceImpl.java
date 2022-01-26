package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            Account account = accountDAO.createAccount(user);
            return account;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Account getAccountByUserId(Integer userId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            return accountDAO.getAccountByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean accountPayment(Account account, String accountNumber, Double amount) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            boolean result = accountDAO.accountPayment(account, accountNumber, amount);
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
