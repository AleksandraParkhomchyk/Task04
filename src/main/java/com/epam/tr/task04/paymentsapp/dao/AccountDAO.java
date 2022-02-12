package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.util.List;

public interface AccountDAO {

    Account createAccount(User user) throws DAOException;

    Account getAccountByUserId(Integer userId) throws DAOException;

    boolean accountPayment(Account account, String accountNumber, Double amount, Integer userId) throws DAOException;

    Integer getAccountIdByRequestId(Integer requestId) throws DAOException;

    Account getAccountById(Integer accountId) throws DAOException;

    void blockAccount(Integer userId) throws DAOException;

    void unblockAccount(Integer userId) throws DAOException;

    List<Account> getAllBlockedAccounts() throws DAOException;
}
