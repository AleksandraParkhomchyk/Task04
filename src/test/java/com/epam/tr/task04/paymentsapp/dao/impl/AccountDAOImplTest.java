package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {
    private static final AccountDAO accountDAO = new AccountDAOImpl();
    private static User user;
    private static Account account;


    @Test
    void createAccount() {
        user = new User();
        user.setId(5);

        try {
            account = accountDAO.createAccount(user);
            Account accountFromDB = accountDAO.getAccountByUserId(user.getId());

          //  assertEquals(account, accountFromDB);
        } catch (DAOException e) {
            System.out.println("DaoException");
        }
    }
}