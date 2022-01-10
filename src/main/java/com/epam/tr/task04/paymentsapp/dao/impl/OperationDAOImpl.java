package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.OperationDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Operation;

import java.sql.*;

public class OperationDAOImpl implements OperationDAO {

    private final String makePayment = "INSERT INTO operations(o_date, o_amount, accouts_a_id, operaton_types_ot_id) VALUES (?, ?, ?, ?)";
    private final String paymentTransaction = "INSERT INTO transactions(t_amount, t_date, users_u_id, users_u_id1) values (?, ?, ?, ?)";

    Date date = new java.sql.Date(System.currentTimeMillis());

    @Override
    public boolean payment(Operation operation) throws DAOException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }


        try (PreparedStatement writePayment = connection.prepareStatement(makePayment);
             PreparedStatement writeTransaction = connection.prepareStatement(paymentTransaction)) {

            connection.setAutoCommit(false);

            writePayment.setDate(1, date);
            writePayment.setDouble(2, operation.getAmount());
            writePayment.setInt(3, 1);
            writePayment.setInt(4, 1);

            writePayment.executeUpdate();

            writeTransaction.setDouble(1, operation.getAmount());
            writeTransaction.setDate(2, date);
            writeTransaction.setInt(3, 1);
            writeTransaction.setInt(4, 1);

            writeTransaction.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DAOException(e);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return true;
    }
}
