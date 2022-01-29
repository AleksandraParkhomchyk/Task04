package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import java.util.List;

public class CashRequestServiceImpl implements CashRequestService {


    @Override
    public CashoutRequest cashout(Account account, Double amount) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();

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
    public boolean updateRequestStatusApproved(Integer requestID) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CashRequestDAO cashRequestDAO = factory.getCashRequestDAO();
        boolean result;
        try {
            result = cashRequestDAO.updateRequestStatusApproved(requestID);
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
}
