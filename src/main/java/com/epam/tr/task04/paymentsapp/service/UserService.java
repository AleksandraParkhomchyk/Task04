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
     * Register new user
     *
     * @param user new User
     * @return if user successfully registered
     * @throws ServiceException when dao exception occurs
     */
    boolean registration(User user) throws ServiceException;

}
