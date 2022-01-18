package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.Card;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public interface CardService {

    Card createCard(Account account) throws ServiceException;
}
