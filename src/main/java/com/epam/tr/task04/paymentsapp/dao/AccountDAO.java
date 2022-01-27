package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.util.List;

public interface AccountDAO {

    Account createAccount(User user) throws DAOException;

    Account getAccountByUserId(Integer userId) throws DAOException;

    boolean accountPayment(Account account, String accountNumber, Double amount) throws DAOException;

    CashoutRequest cashout(Account account, Double amount) throws DAOException;

    List<CashoutRequest> getAllCashoutRequests() throws DAOException;
}
