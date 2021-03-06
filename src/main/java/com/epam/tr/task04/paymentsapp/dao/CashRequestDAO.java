package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;

import java.util.List;

public interface CashRequestDAO {

    CashoutRequest cashout(Account account, Double amount) throws DAOException;

    List<CashoutRequest> getAllCashoutRequests() throws DAOException;

    boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount, Integer userId) throws DAOException;

    boolean updateRequestStatusDeclined(Integer requestID) throws DAOException;

    Double getAmountByRequestId(Integer requestId) throws DAOException;

    List<CashoutRequest> getUsersRequests(Integer accountId) throws DAOException;

    void cancelCashRequest(Integer requestId) throws DAOException;
}
