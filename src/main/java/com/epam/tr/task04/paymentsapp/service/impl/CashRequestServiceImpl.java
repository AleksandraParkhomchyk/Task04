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

    private static final Logger LOG = LogManager.getLogger(CashRequestServiceImpl.class);

    @Override
    public CashoutRequest cashout(Account account, String amount) throws ServiceException, InsufficientFundsException, ValidatorException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();

        CashoutValidator cashoutValidator = ValidatorFactory.getInstance().getCashoutValidator();

        try {
            cashoutValidator.validate(amount);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate cashout details", e);

            throw new ValidatorException(e);
        }
        Double amountParsed = Double.parseDouble(amount);

        if (account.getBalance() < amountParsed) {
            LOG.error("Insufficient funds of cashout");
            throw new InsufficientFundsException("Insufficient funds.");
        }

        try {
            return cashRequestDAO.cashout(account, amountParsed);
        } catch (DAOException e) {
            LOG.error("Exception while writing cashout details to database", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashoutRequest> getAllCashoutRequests() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();
        List<CashoutRequest> list;
        try {
            list = cashRequestDAO.getAllCashoutRequests();
        } catch (DAOException e) {
            LOG.error("Exception while getting request list from database", e);

            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public boolean updateRequestStatusApproved(Account account, Integer requestID, Double amount, Integer userId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();
        boolean result;
        try {
            result = cashRequestDAO.updateRequestStatusApproved(account, requestID, amount, userId);
            return result;
        } catch (DAOException e) {
            LOG.error("Exception while updating request status to approved", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateRequestStatusDeclined(Integer requestID) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();
        boolean result;
        try {
            result = cashRequestDAO.updateRequestStatusDeclined(requestID);
            return result;
        } catch (DAOException e) {
            LOG.error("Exception while updating request status to declined", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public Double getAmountByRequestId(Integer requestId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();
        Double amount;
        try {
            amount = cashRequestDAO.getAmountByRequestId(requestId);
            return amount;
        } catch (DAOException e) {
            LOG.error("Exception while getting amount by request id", e);

            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashoutRequest> getUsersRequests(Integer accountId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();
        List<CashoutRequest> list;
        try {
            list = cashRequestDAO.getUsersRequests(accountId);
        } catch (DAOException e) {
            LOG.error("Exception while getting user's request list from database", e);

            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public void cancelCashRequest(Integer requestId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();

        try {
            cashRequestDAO.cancelCashRequest(requestId);
        } catch (DAOException e) {
            LOG.error("Exception while deleting request from database", e);

            throw new ServiceException(e);
        }
    }
}