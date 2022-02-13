package com.epam.tr.task04.paymentsapp.service;

import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.service.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;

import java.util.List;

public interface CashRequestService {

    CashoutRequest cashout(Account account, String amount) throws ServiceException, InsufficientFundsException, ValidatorException;

    List<CashoutRequest> getAllCashoutRequests() throws ServiceException;

    boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount, Integer userId) throws ServiceException;

    boolean updateRequestStatusDeclined(Integer requestID) throws ServiceException;

    Double getAmountByRequestId(Integer requestId) throws ServiceException;

    List<CashoutRequest> getUsersRequests(Integer accountId) throws ServiceException;

    void cancelCashRequest(Integer requestId) throws ServiceException;
}
