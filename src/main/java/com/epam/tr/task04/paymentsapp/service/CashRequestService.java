package com.epam.tr.task04.paymentsapp.service;

import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.service.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;

import java.util.List;

/**
 * Cashrequest service interface
 */
public interface CashRequestService {

    /**
     * Create cashoutrequest
     *
     * @param account account
     * @param amount  cashoutrequest amount
     * @return cashoutrequest entity if successfully created
     * @throws ServiceException           when dao exception occurs
     * @throws InsufficientFundsException when  insufficient funds
     * @throws ValidatorException         when amount data is invalid
     */
    CashoutRequest cashout(Account account, String amount) throws ServiceException, InsufficientFundsException, ValidatorException;

    /**
     * Get all pending cashoutrequests
     *
     * @return list of pending cashoutrequests
     * @throws ServiceException when dao exception occurs
     */
    List<CashoutRequest> getAllCashoutRequests() throws ServiceException;

    /**
     * Approve cashoutrequest
     *
     * @param account   account
     * @param requestID cashoutrequest id
     * @param amount    cashoutrequest amount
     * @param userId    user id
     * @return if updated successfully
     * @throws ServiceException when dao exception occurs
     */
    boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount, Integer userId) throws ServiceException;

    /**
     * Decline cashoutrequest
     *
     * @param requestID cashoutrequest id
     * @return if updated successfully
     * @throws ServiceException when dao exception occurs
     */
    boolean updateRequestStatusDeclined(Integer requestID) throws ServiceException;

    /**
     * Get amount by cashoutrequest id
     *
     * @param requestId cashoutrequest id
     * @return amount of cashoutrequest
     * @throws ServiceException when dao exception occurs
     */
    Double getAmountByRequestId(Integer requestId) throws ServiceException;

    /**
     * Get users cashoutrequests
     *
     * @param accountId account id
     * @return list of users cashoutrequests
     * @throws ServiceException when dao exception occurs
     */
    List<CashoutRequest> getUsersRequests(Integer accountId) throws ServiceException;

    /**
     * Cancel request
     *
     * @param requestId cashoutrequest id
     * @throws ServiceException when dao exception occurs
     */
    void cancelCashRequest(Integer requestId) throws ServiceException;
}
