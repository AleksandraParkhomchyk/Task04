package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private final String createAccount = "INSERT INTO accounts(a_number, a_balance, a_openning_date, a_status, users_u_id) VALUES(?, ?, ?, ?, ?)";
    private final String getAccountNumberByUserId = "SELECT a_id, a_number, a_balance FROM accounts WHERE (users_u_id = ?)";
    private final String afterPaymentBalance = "UPDATE accounts SET a_balance = ? WHERE (a_id = ?)";
    private final String paymentTransaction = "INSERT INTO transactions(t_date, t_amount, t_from_account, t_before_acc_balance, t_after_acc_balance, t_to_account, users_u_id, transaction_type_tt_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String createCashoutRequest = "INSERT INTO cash_requests(cr_date, cr_amount, cr_status, accounts_a_id) VALUES(?, ?, ?, ?)";
    private final String getAllRequestFromDB = "SELECT * FROM cash_requests";
    private final String updateRequestStatusDB = "UPDATE cash_requests SET cr_status = ? WHERE (cr_id = ?)";


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
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, user.getId());

            preparedStatement.executeUpdate();

            account = new Account();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                    return account;
                } else {
                    return account;
                }
            }

        } catch (SQLException e) {
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
    public Account getAccountByUserId(Integer userId) throws DAOException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Account account = new Account();

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(getAccountNumberByUserId);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
                account.setAccountNumber(resultSet.getString(2));
                account.setBalance(resultSet.getDouble(3));

                return account;
            } else {
                return account;
            }
        } catch (SQLException e) {
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
        Connection connection;
        PreparedStatement writeNewBalance = null;
        PreparedStatement writeTransaction = null;

        boolean result = true;


        connection = ConnectionPool.getInstance().takeConnection();


        try {
            writeNewBalance = connection.prepareStatement(afterPaymentBalance);
            writeTransaction = connection.prepareStatement(paymentTransaction);

            connection.setAutoCommit(false);

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
            writeTransaction.setInt(7, 1);
            writeTransaction.setInt(8, 1);

            writeTransaction.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
                result = false;
            } catch (SQLException exception) {
                throw new DAOException(e);
            }
        } finally {
            try {
                if (writeNewBalance != null) {
                    writeNewBalance.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (writeTransaction != null) {
                    writeTransaction.close();
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
        return result;
    }

    @Override
    public CashoutRequest cashout(Account account, Double amount) throws DAOException {
        CashoutRequest cashoutRequest;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(createCashoutRequest, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setDate(1, date);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, account.getId());

            preparedStatement.executeUpdate();

            cashoutRequest = new CashoutRequest();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cashoutRequest.setId(generatedKeys.getInt(1));
                    return cashoutRequest;
                } else {
                    return cashoutRequest;
                }
            }
        } catch (SQLException e) {
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
    public List<CashoutRequest> getAllCashoutRequests() throws DAOException {
        List<CashoutRequest> list = new ArrayList<CashoutRequest>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(getAllRequestFromDB);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CashoutRequest cashoutRequest = new CashoutRequest();
                cashoutRequest.setId(resultSet.getInt(1));
                cashoutRequest.setDate(resultSet.getDate(2));
                cashoutRequest.setAmount(resultSet.getDouble(3));
                cashoutRequest.setStatus(resultSet.getString(4));

                list.add(cashoutRequest);

            }
            return list;
        } catch (SQLException e) {
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
    public boolean updateRequestStatusApproved(Integer requestID) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(updateRequestStatusDB);

            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, requestID);

            preparedStatement.executeUpdate();

            return true;


        } catch (SQLException e) {
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
}
