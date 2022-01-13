package com.epam.tr.task04.paymentsapp;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.dao.impl.AccountDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.CardDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.OperationDAOImpl;
import com.epam.tr.task04.paymentsapp.dao.impl.UserDAOImpl;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.Card;
import com.epam.tr.task04.paymentsapp.entity.Operation;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws DAOException, SQLException, ConnectionPoolException {
       /* UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.saveUser(new User("an", "iv", "ai", "pass", "MP12"));*/

        /*AccountDAOImpl accountDAO = new AccountDAOImpl();
        accountDAO.createAccount(new Account("1213", 0.00));*/

       /* OperationDAOImpl operationDAO = new OperationDAOImpl();
        operationDAO.payment(new Operation(45.00));*/

       /* AccountDAOImpl accountDAO = new AccountDAOImpl();
        accountDAO.createAccount(new Account());*/

        /*UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.authorisation("admin", "password");*/

        CardDAOImpl cardDAO = new CardDAOImpl();
        cardDAO.createCard(new Account());

    }
}
