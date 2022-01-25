package com.epam.tr.task04.paymentsapp.services;


import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;

public interface UserService {

    User authorisation(String login, String password) throws ServiceException;

    boolean registration(String name, String surname, String login, String password, String  passport);

    List<User> getAllUsers() throws ServiceException;
}
