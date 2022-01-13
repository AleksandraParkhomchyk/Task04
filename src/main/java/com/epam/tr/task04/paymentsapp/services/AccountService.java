package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public interface AccountService {

    boolean createAccount(User user) throws ServiceException;
}
