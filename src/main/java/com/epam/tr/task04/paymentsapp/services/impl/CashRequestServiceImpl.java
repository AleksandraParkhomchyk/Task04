package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.services.validator.CashoutValidator;
import com.epam.tr.task04.paymentsapp.services.validator.PaymentValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CashRequestServiceImpl implements CashRequestService {

    private final static Logger LOG = LogManager.getLogger(CashRequestServiceImpl.class);

    @Override
    public CashoutRequest cashout(Account account, String amount) throws ServiceException, InsufficientFundsException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();

        CashoutValidator cashoutValidator = ValidatorFactory.getInstance().getCashoutValidator();

        try {
            cashoutValidator.validate(amount);
        } catch (ValidatorException e) {
            LOG.error("Unable to validate cashout details", e);

            throw new ServiceException(e);
        }
        Double amount1 = Double.parseDouble(amount);
        if (account.getBalance() < amount1) {
            throw new InsufficientFundsException("insufficient funds.");
        }

        try {
            CashoutRequest cashoutRequest = cashRequestDAO.cashout(account, amount1);
            return cashoutRequest;
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
