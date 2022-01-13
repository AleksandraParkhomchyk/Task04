package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.CardDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardDAOImpl implements CardDAO {
    private final String createCard = "INSERT INTO cards(c_cardnumber, c_issue_date, c_valid_thru_date, a_status, accouts_a_id, card_types_ct_id) VALUES( ?, ?, ?, ?, ?, ?)";
    Date date = new java.sql.Date(System.currentTimeMillis());
    final int max = 1000;
    int random_number = 1 + (int) (Math.random() * max);

    @Override
    public boolean createCard(Account account) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(createCard);

            preparedStatement.setString(1, String.valueOf(random_number));
            preparedStatement.setDate(2, date);
            preparedStatement.setDate(3, date);
            preparedStatement.setString(4, "1");
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, 1);

            preparedStatement.executeUpdate();

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

        return true;
    }

}
