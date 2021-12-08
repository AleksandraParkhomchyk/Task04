package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    final String creatingUser = "INSERT INTO users(id, name, surname, login, password, passport, birthdate) VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String selectAllUsers = "SELECT FROM users";

    @Override
    public int createUser(User user) throws DAOException {
        int result = 0;

        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(creatingUser);
        try {

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(4, user.getPassword());///todo: password, how to display
            preparedStatement.setString(5, user.getPassport());
            preparedStatement.setDate(5, user.getBirthdate());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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

        return result;
    }

    @Override
    public List<User> findAllUsers() throws DAOException {

        List<User> allUsersList = new ArrayList<User>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectAllUsers);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            User user = new User();
            while (resultSet.next()) {
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));

                allUsersList.add(user);

            }
        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DAOException();
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException();
            }
        }

        return allUsersList;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
