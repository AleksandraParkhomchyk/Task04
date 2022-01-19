package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;

public interface AccountDAO {

    Account createAccount(User user) throws DAOException;

    String getAccountByUserId(Integer userId) throws DAOException;
}
