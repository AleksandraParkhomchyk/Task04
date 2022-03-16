/*package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;*/

/*
class UserDAOImplTest {
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final UserDAO USER_DAO = DAO_FACTORY.getUserDAO();
    private static final String GET_USER_FROM_DB = "SELECT * FROM users WHERE u_login=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE u_login=?";

    @Test
    void saveUser() {
        User user = new User();

        user.setName("Emma");
        user.setSurname("Green");
        user.setLogin("emma");
        user.setPassport("MP3456789");
        user.setRole(1);

        try {
            USER_DAO.saveUser(user);

            User userFromDB = new User();
            try (Connection connection = ConnectionPool.getInstance().takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FROM_DB)) {
                preparedStatement.setString(1, user.getLogin());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        userFromDB.setLogin(resultSet.getString(2));
                        userFromDB.setName(resultSet.getString(4));
                        userFromDB.setSurname(resultSet.getString(5));
                        userFromDB.setPassport(resultSet.getString(6));
                        userFromDB.setRole(resultSet.getInt(7));
                    }
                }

            } catch (SQLException | ConnectionPoolException e) {
                e.printStackTrace();
            }
            assertEquals(user, userFromDB);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(1, user.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}*/
