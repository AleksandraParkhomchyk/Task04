package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO {

    private final String createAccount = "INSERT INTO accounts(a_number, a_balance, a_openning_date, a_status, users_u_id) VALUES(?, ?, ?, ?, ?)";
    private final String getAccountNumberByUserId = "SELECT a_id, a_number, a_balance FROM accounts WHERE (users_u_id = ?)";
    private final String afterPaymentBalance = "UPDATE accounts SET a_balance = ? WHERE (a_id = ?)";
    private final String paymentTransaction = "INSERT INTO transactions(t_date, t_amount, t_from_account, t_before_acc_balance, t_after_acc_balance, t_to_account, users_u_id, transaction_type_tt_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String getAccountByRequestId = "SELECT accounts_a_id FROM cash_requests WHERE cr_id = ?";
    private final String getAccountById = "SELECT * FROM accounts WHERE a_id = ?";

    Date date = new java.sql.Date(System.currentTimeMillis());
    final int max = 1000;
    int random_number = 1 + (int) (Math.random() * max);

    @Override
    public Account createAccount(User user) throws DAOException {
        Account account = new Account();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createAccount, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, String.valueOf(random_number));
            preparedStatement.setDouble(2, 0.00);
            preparedStatement.setDate(3, date);
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, user.getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                }
            } catch (SQLException e){
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return account;
    }

    @Override
    public Account getAccountByUserId(Integer userId) throws DAOException {
        Account account = new Account();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAccountNumberByUserId)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    account.setId(resultSet.getInt(1));
                    account.setAccountNumber(resultSet.getString(2));
                    account.setBalance(resultSet.getDouble(3));
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return account;
    }


    @Override
    public boolean accountPayment(Account account, String accountNumber, Double amount, Integer userId) throws
            DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement writeNewBalance = connection.prepareStatement(afterPaymentBalance);
                 PreparedStatement writeTransaction = connection.prepareStatement(paymentTransaction)) {

                double finalBalance = account.getBalance() - amount;
                writeNewBalance.setDouble(1, finalBalance);
                writeNewBalance.setInt(2, account.getId());

                writeNewBalance.executeUpdate();

                writeTransaction.setDate(1, date);
                writeTransaction.setDouble(2, amount);
                writeTransaction.setString(3, account.getAccountNumber());
                writeTransaction.setDouble(4, account.getBalance());
                writeTransaction.setDouble(5, finalBalance);
                writeTransaction.setString(6, accountNumber);
                writeTransaction.setInt(7, userId);
                writeTransaction.setInt(8, 1);

                writeTransaction.executeUpdate();

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }


    @Override
    public Integer getAccountIdByRequestId(Integer requestId) throws DAOException {
        Integer accountId = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAccountByRequestId)) {

            preparedStatement.setInt(1, requestId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    accountId = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return accountId;
    }

    @Override
    public Account getAccountById(Integer accountId) throws DAOException {
        Account account = new Account();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAccountById)) {

            preparedStatement.setInt(1, accountId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account.setId(resultSet.getInt(1));
                    account.setAccountNumber(resultSet.getString(2));
                    account.setBalance(resultSet.getDouble(3));
                    account.setOwnerId(resultSet.getInt(6));
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return account;
    }
}