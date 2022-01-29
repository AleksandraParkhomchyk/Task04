package com.epam.tr.task04.paymentsapp.services;

import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public interface CashRequestService {

    boolean updateRequestStatusDeclined(Integer requestID) throws ServiceException;
}
