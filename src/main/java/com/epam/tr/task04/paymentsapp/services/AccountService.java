package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;

public interface AccountService {

    Account createAccount(User user) throws ServiceException;

    Account getAccountByUserId(Integer userId) throws ServiceException;

    boolean accountPayment(Account account, String accountNumber, Double amount) throws ServiceException;

    CashoutRequest cashout(Account account, Double amount) throws ServiceException;

    List<CashoutRequest> getAllCashoutRequests() throws ServiceException;
}
