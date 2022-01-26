package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.TransactionDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Transaction;
import com.epam.tr.task04.paymentsapp.services.TransactionService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public List<Transaction> getAllTransactions() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        TransactionDAO transactionDAO = factory.getTransactionDAO();
        List<Transaction> list;
        try {
            list = transactionDAO.getAllTransactions();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return list;
    }
}