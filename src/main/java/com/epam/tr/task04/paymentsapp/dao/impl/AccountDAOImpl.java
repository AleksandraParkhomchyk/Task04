package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO {

    private final String createAccount = "INSERT INTO accounts(a_number, a_balance, a_openning_date, users_u_id) VALUES( ?, ?, ?, ?)";
    private final String getAccountNumberByUserId = "SELECT a_id, a_number, a_balance FROM accounts WHERE (users_u_id = ?)";
    private final String afterPaymentBalance = "UPDATE accounts SET a_balance = ? WHERE (a_id = ?)";


    Date date = new java.sql.Date(System.currentTimeMillis());
    final int max = 1000;
    int random_number = 1 + (int) (Math.random() * max);

    @Override
    public Account createAccount(User user) throws DAOException {
        Account account = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(createAccount, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, String.valueOf(random_number));
            preparedStatement.setDouble(2, 0.00);
            preparedStatement.setDate(3, date);
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();

            account = new Account();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
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

        return account;
    }

    @Override
    public Account getAccountByUserId(Integer userId) throws DAOException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(getAccountNumberByUserId);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt(1));
                account.setAccountNumber(resultSet.getString(2));
                account.setBalance(resultSet.getDouble(3));
                return account;
            } else {
                throw new DAOException("There is no account");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
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
    public boolean accountPayment(Account account, String accountNumber, Double amount) throws DAOException {


        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(afterPaymentBalance);
            double finalBalance = account.getBalance() - amount;
            preparedStatement.setDouble(1, finalBalance);
            preparedStatement.setInt(2, account.getId());

            int result = preparedStatement.executeUpdate();
            System.out.println(result);

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
        } return true;
    }
}