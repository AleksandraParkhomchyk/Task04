package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public interface AccountService {

    Account createAccount(User user) throws ServiceException;

    Account getAccountByUserId(Integer userId) throws ServiceException;

    boolean accountPayment(Account account, String accountNumber, Double amount, Integer userId) throws ServiceException;

    Integer getAccountIdByRequestId(Integer requestId) throws ServiceException;

    Account getAccountById(Integer accountId) throws ServiceException;
}
