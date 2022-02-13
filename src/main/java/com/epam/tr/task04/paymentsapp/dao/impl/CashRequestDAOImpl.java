package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashRequestDAOImpl implements CashRequestDAO {

    private static final String GET_ALL_REQUEST_FROM_DB = "SELECT * FROM cash_requests";
    private static final String UPDATE_REQUEST_STATUS_DB = "UPDATE cash_requests SET cr_status = ? WHERE (cr_id = ?)";
    private static final String CREATE_CASHOUT_REQUEST = "INSERT INTO cash_requests(cr_date, cr_amount, cr_status, accounts_a_id) VALUES(?, ?, ?, ?)";
    private static final String AFTER_CASHOUT_BALANCE = "UPDATE accounts SET a_balance = ? WHERE (a_id = ?)";
    private static final String GET_AMOUNT_BY_ID = "SELECT cr_amount FROM cash_requests WHERE (cr_id = ?)";
    private static final String GET_ALL_REQUEST_BY_ACCOUNT_ID = "SELECT * FROM cash_requests WHERE accounts_a_id = ?";
    private static final String CASHOUT_TRANSACTION = "INSERT INTO transactions(t_date, t_amount, t_from_account, t_before_acc_balance, t_after_acc_balance, t_to_account, users_u_id, transaction_type_tt_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CANCEL_REQUEST = "DELETE FROM cash_requests WHERE (cr_id = ?)";

    private static final String CASHOUT = "cashout";

    @Override
    public CashoutRequest cashout(Account account, Double amount) throws DAOException {
        CashoutRequest cashoutRequest = new CashoutRequest();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CASHOUT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDouble(2, amount);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, account.getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cashoutRequest.setId(generatedKeys.getInt(1));
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return cashoutRequest;
    }

    @Override
    public List<CashoutRequest> getAllCashoutRequests() throws DAOException {
        List<CashoutRequest> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUEST_FROM_DB);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CashoutRequest cashoutRequest = new CashoutRequest();
                cashoutRequest.setId(resultSet.getInt(1));
                cashoutRequest.setDate(resultSet.getDate(2));
                cashoutRequest.setAmount(resultSet.getDouble(3));
                cashoutRequest.setStatus(resultSet.getString(4));
                cashoutRequest.setAccountId(resultSet.getInt(5));

                list.add(cashoutRequest);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount, Integer userId) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement updateStatus = connection.prepareStatement(UPDATE_REQUEST_STATUS_DB);
                 PreparedStatement updateBalance = connection.prepareStatement(AFTER_CASHOUT_BALANCE);
                 PreparedStatement writeTransaction = connection.prepareStatement(CASHOUT_TRANSACTION)) {

                updateStatus.setInt(1, 2);
                updateStatus.setInt(2, requestID);

                updateStatus.executeUpdate();

                double finalBalance = account.getBalance() - amount;
                updateBalance.setDouble(1, finalBalance);
                updateBalance.setInt(2, account.getId());

                updateBalance.executeUpdate();

                writeTransaction.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                writeTransaction.setDouble(2, amount);
                writeTransaction.setString(3, account.getAccountNumber());
                writeTransaction.setDouble(4, account.getBalance());
                writeTransaction.setDouble(5, finalBalance);
                writeTransaction.setString(6, CASHOUT);
                writeTransaction.setInt(7, userId);
                writeTransaction.setInt(8, 2);

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
    public boolean updateRequestStatusDeclined(Integer requestID) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS_DB)) {

            preparedStatement.setInt(1, 3);
            preparedStatement.setInt(2, requestID);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public Double getAmountByRequestId(Integer requestId) throws DAOException {
        Double amount = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AMOUNT_BY_ID)) {

            preparedStatement.setInt(1, requestId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    amount = resultSet.getDouble(1);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return amount;
    }

    @Override
    public List<CashoutRequest> getUsersRequests(Integer accountId) throws DAOException {
        List<CashoutRequest> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUEST_BY_ACCOUNT_ID)) {

            preparedStatement.setInt(1, accountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    CashoutRequest cashoutRequest = new CashoutRequest();
                    cashoutRequest.setId(resultSet.getInt(1));
                    cashoutRequest.setDate(resultSet.getDate(2));
                    cashoutRequest.setAmount(resultSet.getDouble(3));
                    cashoutRequest.setStatus(resultSet.getString(4));

                    list.add(cashoutRequest);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public void cancelCashRequest(Integer requestId) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_REQUEST)) {

            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
    }
}