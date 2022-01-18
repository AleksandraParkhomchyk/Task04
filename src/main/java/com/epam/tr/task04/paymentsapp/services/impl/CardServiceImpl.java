package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.CardDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.Card;
import com.epam.tr.task04.paymentsapp.services.CardService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public class CardServiceImpl implements CardService {
    @Override
    public Card createCard(Account account) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CardDAO cardDAO = factory.getCardDAO();

        try {
            Card card = cardDAO.createCard(account);
            return card;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

