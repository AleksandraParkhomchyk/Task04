package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

public interface UserDAO {

    String authorisation(String login, String password);

    void saveUser(User user) throws DAOException;

}
