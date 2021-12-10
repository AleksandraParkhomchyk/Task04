package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final String creatingUser = "INSERT INTO users(u_id, u_name, u_surname, u_login, u_password, u_passport, u_birth_date, roles_r_id, u_registration_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String selectAllUsers = "SELECT * FROM users";

    @Override
    public void saveUser(User user) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(creatingUser);

            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getPassport());
            preparedStatement.setDate(7, user.getBirthdate());
            preparedStatement.setInt(8, 1);
            preparedStatement.setDate(9, Date.valueOf("1980-04-09"));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
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
        System.out.println("все ок");
    }

    @Override
    public List<User> findAllUsers() throws DAOException, ConnectionPoolException, SQLException {

        /*List<User> allUsersList = new ArrayList<User>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectAllUsers);
        ResultSet resultSet = preparedStatement.executeQuery();

        try {
            User user = new User();
            while (resultSet.next()) {
                user.setLogin(resultSet.getString(3));
                user.setName(resultSet.getString(5));

                System.out.println(resultSet.getString(3));

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
        }*/

        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
