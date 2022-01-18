package com.epam.tr.task04.paymentsapp.dao.impl;

import com.epam.tr.task04.paymentsapp.dao.CardDAO;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.Card;

import java.sql.*;

public class CardDAOImpl implements CardDAO {
    private final String createCard = "INSERT INTO cards(c_cardnumber, c_issue_date, c_valid_thru_date, a_status, accouts_a_id, card_types_ct_id) VALUES( ?, ?, ?, ?, ?, ?)";
    Date date = new java.sql.Date(System.currentTimeMillis());
    final int max = 1000;
    int random_number = 1 + (int) (Math.random() * max);

    @Override
    public Card createCard(Account account) throws DAOException {
        Card card = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(createCard, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, String.valueOf(random_number));
            preparedStatement.setDate(2, date);
            preparedStatement.setDate(3, date);
            preparedStatement.setString(4, "1");
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, 1);

            preparedStatement.executeUpdate();

            card = new Card();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    card.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating card failed, no ID obtained.");
                }
            }

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

        return card;
    }

}
