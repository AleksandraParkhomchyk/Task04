package com.epam.tr.task04.paymentsapp.service;

import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

/**
 * User service interface
 */
public interface UserService {

    /**
     * Login user
     *
     * @param login user login
     * @param password user password
     * @return user entity if successfully logged in
     * @throws ServiceException when dao exception occurs
     */
    User authorisation(String login, String password) throws ServiceException;

    /**
     * Regiger new user
     *
     * @param name user first name
     * @param surname user surname
     * @param login user login
     * @param password user password
     * @param passport user passport
     * @return if user successfully registered
     * @throws ServiceException when dao exception occurs
     */
    boolean registration(String name, String surname, String login, String password, String passport) throws ServiceException;

}
