package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> getAllTransactions(Integer userId) throws DAOException;

}
