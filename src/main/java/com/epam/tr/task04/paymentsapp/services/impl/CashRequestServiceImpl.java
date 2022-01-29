package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.CashRequestDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public class CashRequestServiceImpl implements CashRequestService {
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
