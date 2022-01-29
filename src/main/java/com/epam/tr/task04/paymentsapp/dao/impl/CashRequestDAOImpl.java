package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CashRequestDAOImpl implements CashRequestDAO {
    private final String updateRequestStatusDB = "UPDATE cash_requests SET cr_status = ? WHERE (cr_id = ?)";

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

