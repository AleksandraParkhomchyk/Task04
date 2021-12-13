package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.services.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String authorisation(String login, String password) {
        //1. validation

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        String role;

        role = userDAO.authorisation(login, password);


        return role;
    }

    @Override
    public boolean registration(String name, String surname) {

        return false;
    }
}
