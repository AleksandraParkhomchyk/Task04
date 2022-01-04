package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.OperationDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.Operation;

import java.sql.*;

public class OperationDAOImpl implements OperationDAO{

    private final String makePayment = "INSERT INTO operations(o_id, o_date, o_amount, accouts_a_id, operaton_types_ot_id) VALUES (?, ?, ?, ?, ?)";

    Date date = new java.sql.Date(System.currentTimeMillis());

    @Override
    public boolean payment(Operation operation) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(makePayment);

            preparedStatement.setInt(1, 4);//todo:autoincrement in db
            preparedStatement.setDate(2, date);
            preparedStatement.setDouble(3, operation.getAmount());
            preparedStatement.setInt(4, 4);
            preparedStatement.setInt(5, 1);

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
        return false;
    }
}
