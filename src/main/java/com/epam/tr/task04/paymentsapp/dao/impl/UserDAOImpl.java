package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.builder.BuilderFactory;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.UserDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final String SAVE_USER = "INSERT INTO users(u_name, u_surname, u_login, u_password, u_passport, roles_r_id) VALUES( ?, ?, ?, ?, ?, ?)";
    private static final String AUTHORISATION = "SELECT * FROM users WHERE u_login = ?";

    @Override
    public void saveUser(User user) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {

            String passwordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
            user.setPassword(null);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, passwordHash);
            preparedStatement.setString(5, user.getPassport());
            preparedStatement.setInt(6, 1);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<User> authorisation(String login, String password) throws DAOException {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AUTHORISATION)) {

            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String passwordFromDB = resultSet.getString(3);

                    if (BCrypt.checkpw(password, passwordFromDB)) {
                        user = BuilderFactory.getUserBuilder().build(resultSet);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }
}