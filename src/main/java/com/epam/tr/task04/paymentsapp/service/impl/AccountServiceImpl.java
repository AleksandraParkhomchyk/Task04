package com.epam.tr.task04.paymentsapp.service.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.PaymentValidator;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class AccountServiceImpl implements AccountService {
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final AccountDAO ACCOUNT_DAO = DAO_FACTORY.getAccountDAO();

    private static final ValidatorFactory VALIDATOR_FACTORY = ValidatorFactory.getInstance();
    private static final PaymentValidator PAYMENT_VALIDATOR = VALIDATOR_FACTORY.getPaymentValidator();

    private static final Logger LOG = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    public void createAccount(User user) throws ServiceException {

        try {
            ACCOUNT_DAO.createAccount(user);
        } catch (DAOException e) {
            LOG.error("Exception while creating account", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Account getAccountByUserId(Integer userId) throws ServiceException {

        try {
            return ACCOUNT_DAO.getAccountByUserId(userId);
        } catch (DAOException e) {
            LOG.error("Exception while getting account by user id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public boolean accountPayment(Account account, String accountNumber, String amount, Integer userId) throws ServiceException, InsufficientFundsException, ValidatorException {

        try {
            PAYMENT_VALIDATOR.validate(accountNumber, amount);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate payment details", e);

            throw new ValidatorException(e);
        }

        double amountParsed = Double.parseDouble(amount);

        if (account.getBalance() < amountParsed) {
            LOG.error("Insufficient funds of payment");

            throw new InsufficientFundsException("Insufficient funds.");
        }
        try {
            return ACCOUNT_DAO.accountPayment(account, accountNumber, amountParsed, userId);

        } catch (DAOException e) {
            LOG.error("Exception while writing payment details to database", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getAccountIdByRequestId(Integer requestId) throws ServiceException {

        try {
            return ACCOUNT_DAO.getAccountIdByRequestId(requestId);

        } catch (DAOException e) {
            LOG.error("Exception while getting account id by request id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public Account getAccountById(Integer accountId) throws ServiceException {

        try {
            return ACCOUNT_DAO.getAccountById(accountId);
        } catch (DAOException e) {
            LOG.error("Exception while getting account by id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public void blockAccount(Integer userId) throws ServiceException {

        try {
            ACCOUNT_DAO.blockAccount(userId);

        } catch (DAOException e) {
            LOG.error("Exception while blocking account", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public void unblockAccount(Integer accountId) throws ServiceException {

        try {
            ACCOUNT_DAO.unblockAccount(accountId);

        } catch (DAOException e) {
            LOG.error("Exception while unblocking account", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getAllBlockedAccounts() throws ServiceException {
        List<Account> list;
        try {
            list = ACCOUNT_DAO.getAllBlockedAccounts();

        } catch (DAOException e) {
            LOG.error("Exception while getting all blocked accounts list", e);

            throw new ServiceException(e);
        }
        return list;
    }
}
