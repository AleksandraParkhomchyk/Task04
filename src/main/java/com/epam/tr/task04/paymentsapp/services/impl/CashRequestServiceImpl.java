package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.services.validator.CashoutValidator;
import com.epam.tr.task04.paymentsapp.services.validator.PaymentValidator;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorFactory;

import java.util.List;

public class CashRequestServiceImpl implements CashRequestService {

    @Override
    public CashoutRequest cashout(Account account, Double amount) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();

        CashoutValidator cashoutValidator = ValidatorFactory.getInstance().getCashoutValidator();

        try {
            cashoutValidator.validate(amount);
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        }

        try {
            CashoutRequest cashoutRequest = cashRequestDAO.cashout(account, amount);
            return cashoutRequest;
        } catch (DAOException e) {
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
            throw new ServiceException(e);
        }
    }
}
