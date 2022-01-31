package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;

public interface CashRequestService {
    CashoutRequest cashout(Account account, Double amount) throws ServiceException;

    List<CashoutRequest> getAllCashoutRequests() throws ServiceException;

    boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount) throws ServiceException;

    boolean updateRequestStatusDeclined(Integer requestID) throws ServiceException;

    Double getAmountByRequestId(Integer requestId) throws ServiceException;

    List<CashoutRequest> getUsersRequests(Integer accountId) throws ServiceException;

}
