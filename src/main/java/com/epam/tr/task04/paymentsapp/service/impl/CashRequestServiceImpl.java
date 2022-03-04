package com.epam.tr.task04.paymentsapp.service.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.service.CashRequestService;
import com.epam.tr.task04.paymentsapp.service.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.CashoutValidator;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CashRequestServiceImpl implements CashRequestService {
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final CashRequestDAO CASH_REQUEST_DAO = DAO_FACTORY.getCashRequestDAO();

    private static final ValidatorFactory VALIDATOR_FACTORY = ValidatorFactory.getInstance();
    private static final CashoutValidator CASHOUT_VALIDATOR = VALIDATOR_FACTORY.getCashoutValidator();

    private static final Logger LOG = LogManager.getLogger(CashRequestServiceImpl.class);

    @Override
    public CashoutRequest cashout(Account account, String amount) throws ServiceException, InsufficientFundsException, ValidatorException {

        try {
            CASHOUT_VALIDATOR.validate(amount);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate cashout details", e);
            throw new ValidatorException(e);
        }

        double amountParsed = Double.parseDouble(amount);

        if (account.getBalance() < amountParsed) {
            LOG.error("Insufficient funds of cashout");
            throw new InsufficientFundsException("Insufficient funds.");
        }

        try {
            return CASH_REQUEST_DAO.cashout(account, amountParsed);
        } catch (DAOException e) {
            LOG.error("Exception while writing cashout details to database", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashoutRequest> getAllCashoutRequests() throws ServiceException {
        List<CashoutRequest> list;
        try {
            list = CASH_REQUEST_DAO.getAllCashoutRequests();
        } catch (DAOException e) {
            LOG.error("Exception while getting request list from database", e);
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount, Integer userId) throws ServiceException {
        boolean result;
        try {
            result = CASH_REQUEST_DAO.updateRequestStatusApproved(account, requestID, amount, userId);
            return result;
        } catch (DAOException e) {
            LOG.error("Exception while updating request status to approved", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateRequestStatusDeclined(Integer requestID) throws ServiceException {
        boolean result;
        try {
            result = CASH_REQUEST_DAO.updateRequestStatusDeclined(requestID);
            return result;
        } catch (DAOException e) {
            LOG.error("Exception while updating request status to declined", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public Double getAmountByRequestId(Integer requestId) throws ServiceException {
        Double amount;
        try {
            amount = CASH_REQUEST_DAO.getAmountByRequestId(requestId);
            return amount;
        } catch (DAOException e) {
            LOG.error("Exception while getting amount by request id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashoutRequest> getUsersRequests(Integer accountId) throws ServiceException {
        List<CashoutRequest> list;
        try {
            list = CASH_REQUEST_DAO.getUsersRequests(accountId);
        } catch (DAOException e) {
            LOG.error("Exception while getting user's request list from database", e);

            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public void cancelCashRequest(Integer requestId) throws ServiceException {

        try {
            CASH_REQUEST_DAO.cancelCashRequest(requestId);
        } catch (DAOException e) {
            LOG.error("Exception while deleting request from database", e);

            throw new ServiceException(e);
        }
    }
}
