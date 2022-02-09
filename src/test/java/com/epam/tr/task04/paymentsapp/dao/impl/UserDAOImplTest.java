package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static User user;


    @Test
    void saveUser() throws DAOException {
        user = new User();
        user.setId(1);
        user.setLogin("alex");
        user.setPassword("alex");
        user.setName("Alex");
        user.setSurname("aLEX");
        user.setPassport("MP1234567");

        try {
            userDAO.saveUser(user);
        } catch (DAOException e){
            System.out.println("DaoException");
        }
    }
}