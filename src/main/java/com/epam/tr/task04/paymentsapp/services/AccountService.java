package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;

import java.util.List;

public interface AccountService {

    Account createAccount(User user) throws ServiceException;

    Account getAccountByUserId(Integer userId) throws ServiceException;

    boolean accountPayment(Account account, String accountNumber, String amount, Integer userId) throws ServiceException, InsufficientFundsException, ValidatorException;

    Integer getAccountIdByRequestId(Integer requestId) throws ServiceException;

    Account getAccountById(Integer accountId) throws ServiceException;

    void blockAccount(Integer userId) throws ServiceException;

    void unblockAccount(Integer accountId) throws ServiceException;

    List<Account> getAllBlockedAccounts() throws ServiceException;
}
