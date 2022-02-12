package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.UserService;
import com.epam.tr.task04.paymentsapp.services.exception.NotAuthorizedException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.services.validator.UserValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final static Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User authorisation(String login, String password) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {
            Optional<User> userOptional = userDAO.authorisation(login, password);

            if (!userOptional.isPresent()) {
                throw new NotAuthorizedException();
            } else {
                return userOptional.get();
            }
        } catch (DAOException e) {
            LOG.error("Exception while authorization", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(String name, String surname, String login, String password, String passport) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();

        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setPassport(passport);

        try {
            userValidator.validate(newUser);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate user registration data", e);

            throw new ServiceException(e);
        }
        try {
            userDAO.saveUser(newUser);
        } catch (DAOException e) {
            LOG.error("Exception while saving new user", e);
            throw new ServiceException(e);
        }
        return true;
    }
}

