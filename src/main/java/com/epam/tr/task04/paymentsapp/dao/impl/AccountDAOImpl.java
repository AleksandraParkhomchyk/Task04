package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountDAOImpl implements AccountDAO {

    private static final String CREATE_ACCOUNT = "INSERT INTO accounts(a_number, a_balance, a_openning_date, a_status, users_u_id) VALUES(?, ?, ?, ?, ?)";
    private static final String GET_ACCOUNT_BY_USER_ID = "SELECT a_id, a_number, a_balance, a_status, a_openning_date FROM accounts WHERE (users_u_id = ?)";
    private static final String AFTER_PAYMENT_BALANCE = "UPDATE accounts SET a_balance = ? WHERE (a_id = ?)";
    private static final String PAYMENT_TRANSACTION = "INSERT INTO transactions(t_date, t_amount, t_from_account, t_before_acc_balance, t_after_acc_balance, t_to_account, users_u_id, transaction_type_tt_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ACCOUNT_BY_REQUEST_ID = "SELECT accounts_a_id FROM cash_requests WHERE cr_id = ?";
    private static final String GET_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE a_id = ?";
    private static final String BLOCK_ACCOUNT = "UPDATE accounts SET a_status = ? WHERE (users_u_id = ?)";
    private static final String UNBLOCK_ACCOUNT = "UPDATE accounts SET a_status = ? WHERE (a_id = ?)";
    private static final String GET_ALL_BLOCKED_ACCOUNTS = "SELECT * FROM accounts WHERE a_status = ?";

    Random random = new Random();

    @Override
    public Account createAccount(User user) throws DAOException {
        Account account = new Account();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, String.valueOf(random.nextInt()));
            preparedStatement.setDouble(2, 1000.00);
            preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, user.getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
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
    public Account getAccountByUserId(Integer userId) throws DAOException {
        Account account = new Account();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    account.setId(resultSet.getInt(1));
                    account.setAccountNumber(resultSet.getString(2));
                    account.setBalance(resultSet.getDouble(3));
                    account.setStatus(resultSet.getInt(4));
                    account.setAccountOpeningDate(resultSet.getDate(5));
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

            try (PreparedStatement writeNewBalance = connection.prepareStatement(AFTER_PAYMENT_BALANCE);
                 PreparedStatement writeTransaction = connection.prepareStatement(PAYMENT_TRANSACTION)) {

                double finalBalance = account.getBalance() - amount;
                writeNewBalance.setDouble(1, finalBalance);
                writeNewBalance.setInt(2, account.getId());

                writeNewBalance.executeUpdate();

                writeTransaction.setDate(1, new java.sql.Date(System.currentTimeMillis()));
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_REQUEST_ID)) {

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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_ID)) {

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

    @Override
    public void blockAccount(Integer userId) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_ACCOUNT)) {

            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void unblockAccount(Integer accountId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_ACCOUNT)) {

            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, accountId);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Account> getAllBlockedAccounts() throws DAOException {
        List<Account> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BLOCKED_ACCOUNTS)) {

            preparedStatement.setInt(1, 2);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Account account = new Account();
                    account.setId(resultSet.getInt(1));
                    account.setAccountNumber(resultSet.getString(2));
                    account.setBalance(resultSet.getDouble(3));
                    account.setStatus(resultSet.getInt(5));
                    account.setOwnerId(resultSet.getInt(6));
                    list.add(account);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }
}