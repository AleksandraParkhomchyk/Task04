package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.UserService;
import com.epam.tr.task04.paymentsapp.services.exception.NotAuthorizedException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    @Override
    public User authorisation(String login, String password) throws ServiceException {
        //1. validation

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {

            Optional<User> userOptional = userDAO.authorisation(login, password);

            if (!userOptional.isPresent()) {
                //logger
                throw new NotAuthorizedException();
                // построить строку с сообщением и логгером

            } else {
                return userOptional.get();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public boolean registration(String name, String surname, String login, String password, String passport) {

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

    @Override
    public List<User> getAllUsers() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        List<User> list;
        try {
            list = userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return list;
    }
}

