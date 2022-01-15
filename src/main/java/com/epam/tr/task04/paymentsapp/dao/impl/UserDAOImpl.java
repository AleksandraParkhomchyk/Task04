package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final String creatingUser = "INSERT INTO users(u_name, u_surname, u_login, u_password, u_passport, roles_r_id) VALUES( ?, ?, ?, ?, ?, ?)";
    private final String getLoginPasswordRole = "SELECT u_id, u_login, u_password, roles_r_id  FROM users";


    @Override
    public void saveUser(User user) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(creatingUser);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPassport());
            preparedStatement.setInt(6, 1);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

    }

    @Override
    public Optional<User> authorisation(String login, String password) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(getLoginPasswordRole);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                try {
                    String loginFromDB = resultSet.getString(2);
                    String passwordFromDB = resultSet.getString(3);
                    if ((login.equals(loginFromDB)) && (password.equals(passwordFromDB))) {
                        System.out.println("authorisation is OK");
                        user = new User();
                        user.setId(resultSet.getInt(1));
                        user.setLogin(loginFromDB);
                        user.setRole(resultSet.getInt(4));
                    }
                } catch (Exception exp) {
                    throw new DAOException();
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return Optional.ofNullable(user);
    }
}









