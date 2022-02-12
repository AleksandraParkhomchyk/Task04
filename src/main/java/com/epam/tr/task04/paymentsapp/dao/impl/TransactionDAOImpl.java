package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.TransactionDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    private static final String GET_USERS_TRANSACTIONS_FROM_DB = "SELECT * FROM transactions WHERE users_u_id = ?";

    @Override
    public List<Transaction> getAllTransactions(Integer userId) throws DAOException {
        List<Transaction> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_TRANSACTIONS_FROM_DB)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setId(resultSet.getInt(1));
                    transaction.setDate(resultSet.getDate(2));
                    transaction.setAmount(resultSet.getDouble(3));
                    transaction.setOutAccount(resultSet.getString(4));
                    transaction.setStartBalance(resultSet.getDouble(5));
                    transaction.setEndBalance(resultSet.getDouble(6));
                    transaction.setInAccount(resultSet.getString(7));
                    list.add(transaction);
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