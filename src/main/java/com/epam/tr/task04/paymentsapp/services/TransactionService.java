package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.entity.Transaction;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions(Integer userId) throws ServiceException;
}
