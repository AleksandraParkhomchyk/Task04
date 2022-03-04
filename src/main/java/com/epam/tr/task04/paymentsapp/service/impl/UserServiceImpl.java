package com.epam.tr.task04.paymentsapp.service.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.service.UserService;
import com.epam.tr.task04.paymentsapp.service.exception.NotAuthorizedException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.UserValidator;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final UserDAO USER_DAO = DAO_FACTORY.getUserDAO();

    private static final ValidatorFactory VALIDATOR_FACTORY = ValidatorFactory.getInstance();
    private static final UserValidator USER_VALIDATOR = VALIDATOR_FACTORY.getUserValidator();

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User authorisation(String login, String password) throws ServiceException {

        try {
            Optional<User> userOptional = USER_DAO.authorisation(login, password);

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

        User newUser = new User();//todo builder
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setPassport(passport);

        try {
            USER_VALIDATOR.validate(newUser);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate user registration data", e);

            throw new ServiceException(e);
        }
        try {
            USER_DAO.saveUser(newUser);
        } catch (DAOException e) {
            LOG.error("Exception while saving new user", e);
            throw new ServiceException(e);
        }
        return true;
    }
}

