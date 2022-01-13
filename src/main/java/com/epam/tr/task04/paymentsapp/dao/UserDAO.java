package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

public interface UserDAO {

    void saveUser(User user) throws DAOException;

    User authorisation(String login, String password) throws DAOException;

}
