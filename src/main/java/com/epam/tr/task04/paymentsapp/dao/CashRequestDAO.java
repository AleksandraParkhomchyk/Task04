package com.epam.tr.task04.paymentsapp.dao;

import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;

public interface CashRequestDAO {

    boolean updateRequestStatusDeclined(Integer requestID) throws DAOException;
}
