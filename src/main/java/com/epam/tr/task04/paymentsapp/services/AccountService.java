package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public interface AccountService {

    Account createAccount(User user) throws ServiceException;
    String getAccountByUserId(Integer userId) throws ServiceException;
}
