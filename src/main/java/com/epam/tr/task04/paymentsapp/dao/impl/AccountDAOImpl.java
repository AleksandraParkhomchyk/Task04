package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAO {

    private final String createAccount = "INSERT INTO accounts(a_number, a_balance, a_openning_date, users_u_id) VALUES( ?, ?, ?, ?)";

    @Override
    public String createAccount(Account account) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(createAccount);

            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setDouble(2, 0.00);
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            preparedStatement.setDate(3, date);
            preparedStatement.setInt(4, 2);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
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

        return null;
    }
}
