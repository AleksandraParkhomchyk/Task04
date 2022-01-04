package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Card;

import java.util.List;

public interface CardDAO {

    public int createCard(Card card) throws DAOException;

    public List<Card> findAllCards() throws DAOException;

    public Card findById(int id) throws DAOException;
}
