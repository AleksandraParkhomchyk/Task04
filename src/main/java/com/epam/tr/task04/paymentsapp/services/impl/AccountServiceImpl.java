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
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    private final static Logger LOG = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    public Account createAccount(User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            Account account = accountDAO.createAccount(user);
            return account;
        } catch (DAOException e) {
            LOG.error("Exception while creating account", e);

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
            LOG.error("Exception while getting account by user id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public boolean accountPayment(Account account, String accountNumber, String amount, Integer userId) throws ServiceException, InsufficientFundsException, ValidatorException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        PaymentValidator paymentValidator = ValidatorFactory.getInstance().getPaymentValidator();

        try {
            paymentValidator.validate(accountNumber, amount);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate payment details", e);

            throw new ValidatorException(e);
        }

        Double amountParsed = Double.parseDouble(amount);

        if (account.getBalance() < amountParsed) {
            LOG.error("Insufficient funds of payment");
            throw new InsufficientFundsException("Insufficient funds.");
        }
        try {
            boolean result = accountDAO.accountPayment(account, accountNumber, amountParsed, userId);
            return result;

        } catch (DAOException e) {
            LOG.error("Exception while writing payment details to database", e);

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
            LOG.error("Exception while getting account id by request id", e);

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
            LOG.error("Exception while getting account by id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public void blockAccount(Integer userId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            accountDAO.blockAccount(userId);

        } catch (DAOException e) {
            LOG.error("Exception while blocking account", e);

            throw new ServiceException(e);
        }
    }
    @Override
    public void unblockAccount(Integer accountId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {
            accountDAO.unblockAccount(accountId);

        } catch (DAOException e) {
            LOG.error("Exception while unblocking account", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getAllBlockedAccounts() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();
        List<Account> list;

        try {
            list = accountDAO.getAllBlockedAccounts();

        } catch (DAOException e) {
            LOG.error("Exception while getting all blocked accounts list", e);

            throw new ServiceException(e);
        }
        return list;
    }
}
