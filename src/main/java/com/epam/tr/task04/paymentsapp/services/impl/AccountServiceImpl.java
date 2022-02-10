package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.services.validator.PaymentValidator;
import com.epam.tr.task04.paymentsapp.services.validator.UserValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorFactory;


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
    public boolean accountPayment(Account account, String accountNumber, String amount, Integer userId) throws ServiceException, InsufficientFundsException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        PaymentValidator paymentValidator = ValidatorFactory.getInstance().getPaymentValidator();

        try {
            paymentValidator.validate(accountNumber, amount);
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        }
        Double amount1 = Double.parseDouble(amount);

        if (account.getBalance() < amount1) {
            throw new InsufficientFundsException("insufficient funds.");
        }



        try {
            boolean result = accountDAO.accountPayment(account, accountNumber, amount1, userId);
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getAccountIdByRequestId(Integer requestId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();
        try {
            Integer accountId = accountDAO.getAccountIdByRequestId(requestId);
            return accountId;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Account getAccountById(Integer accountId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            Account account = accountDAO.getAccountById(accountId);
            return account;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
