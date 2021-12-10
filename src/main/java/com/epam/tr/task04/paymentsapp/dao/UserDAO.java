package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    public void saveUser(User user) throws DAOException;

    public List<User> findAllUsers() throws DAOException, ConnectionPoolException, SQLException;

    public User findById(int id) throws DAOException;

}
