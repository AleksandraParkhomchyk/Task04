package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashRequestDAOImpl implements CashRequestDAO {

    private final String getAllRequestFromDB = "SELECT * FROM cash_requests";
    private final String updateRequestStatusDB = "UPDATE cash_requests SET cr_status = ? WHERE (cr_id = ?)";
    private final String createCashoutRequest = "INSERT INTO cash_requests(cr_date, cr_amount, cr_status, accounts_a_id) VALUES(?, ?, ?, ?)";

    Date date = new java.sql.Date(System.currentTimeMillis());

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

    @Override
    public boolean updateRequestStatusDeclined(Integer requestID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(updateRequestStatusDB);

            preparedStatement.setInt(1, 3);
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

