package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.util.List;

public interface UserDAO {

    public int createUser(User user) throws DAOException;

    public List<User> findAllUsers() throws DAOException;

    public User findById(int id) throws DAOException;

}
