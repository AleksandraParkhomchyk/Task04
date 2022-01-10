package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private final String creatingUser = "INSERT INTO users(u_name, u_surname, u_login, u_password, u_passport, roles_r_id) VALUES( ?, ?, ?, ?, ?, ?)";
    private final String getLoginPasswordRole = "SELECT u_login, u_password, roles_r_id  FROM users";


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
    public String authorisation(String login, String password) {

        String role = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(getLoginPasswordRole);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if ((login.equals(resultSet.getString(1))) && (password.equals(resultSet.getString(2))) && ("1").equals(resultSet.getString(3))) {
                    role = "user";
                    System.out.println("authorisation is OK");
                    break;
                } else if ((login.equals(resultSet.getString(1))) && (password.equals(resultSet.getString(2))) && ("2").equals(resultSet.getString(3))) {
                    role = "admin";
                    break;
                } else {
                  // todo:  throw new Exception() 400 Bad Request
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return role;

    }
}




