package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;

public interface CashRequestDAO {

    boolean updateRequestStatusApproved(Integer requestID) throws DAOException;

    boolean updateRequestStatusDeclined(Integer requestID) throws DAOException;
}
