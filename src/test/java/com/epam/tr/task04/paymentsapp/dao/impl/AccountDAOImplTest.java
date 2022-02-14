package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final AccountDAO ACCOUNT_DAO = DAO_FACTORY.getAccountDAO();


    @Test
    void getAccountById() {

        Account account = new Account();
        account.setId(7);
        account.setAccountNumber("1052085658");
        account.setBalance(1000);
        account.setStatus(1);
        account.setOwnerId(5);


        try {
            Account accountFromDB = ACCOUNT_DAO.getAccountById(account.getId());
            assertEquals(account, accountFromDB);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
