package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.TransactionDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Transaction;
import com.epam.tr.task04.paymentsapp.services.TransactionService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final static Logger LOG = LogManager.getLogger(TransactionServiceImpl.class);

    @Override
    public List<Transaction> getAllTransactions(Integer userId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        TransactionDAO transactionDAO = factory.getTransactionDAO();
        List<Transaction> list;
        try {
            list = transactionDAO.getAllTransactions(userId);

        } catch (DAOException e) {
            LOG.error("Exception while getting all transaction list", e);

            throw new ServiceException(e);
        }
        return list;
    }
}