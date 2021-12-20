package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
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
    public boolean registration(String name, String surname, String login, String password, String passport){

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setPassport(passport);


        try {
            userDAO.saveUser(newUser);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
