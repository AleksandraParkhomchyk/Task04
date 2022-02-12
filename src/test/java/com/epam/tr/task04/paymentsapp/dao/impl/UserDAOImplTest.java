package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class UserDAOImplTest {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static User user;
    //private static final String insertRoleUser = "INSERT INTO roles(r_title) value('user')";
    private static final String deleteUser = "DELETE FROM users WHERE u_name = ?";


    @Test
    void saveUser() throws DAOException {
        /*try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRoleUser)) {
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }*/


        user = new User();
        user.setLogin("alex");
        user.setPassword("alex");
        user.setName("Alex");
        user.setSurname("aLEX");
        user.setPassport("MP1234567");

        try {
            userDAO.saveUser(user);
        } catch (DAOException e) {
            System.out.println("DaoException");
        }

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUser)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }

    }
}