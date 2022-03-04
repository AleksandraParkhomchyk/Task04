package com.epam.tr.task04.paymentsapp.service.impl;

import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.TransactionDAO;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Transaction;
import com.epam.tr.task04.paymentsapp.service.TransactionService;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final TransactionDAO TRANSACTION_DAO = DAO_FACTORY.getTransactionDAO();

    private static final Logger LOG = LogManager.getLogger(TransactionServiceImpl.class);

    @Override
    public List<Transaction> getAllTransactions(Integer userId) throws ServiceException {
        List<Transaction> list;

        try {
            list = TRANSACTION_DAO.getAllTransactions(userId);

        } catch (DAOException e) {
            LOG.error("Exception while getting all transaction list", e);

            throw new ServiceException(e);
        }
        return list;
    }
}