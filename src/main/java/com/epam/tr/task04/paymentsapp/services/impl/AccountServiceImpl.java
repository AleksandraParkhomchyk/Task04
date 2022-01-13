package com.epam.tr.task04.paymentsapp.services.impl;

import com.epam.tr.task04.paymentsapp.dao.AccountDAO;
import com.epam.tr.task04.paymentsapp.dao.DAOFactory;
import com.epam.tr.task04.paymentsapp.dao.exception.DAOException;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

public class AccountServiceImpl implements AccountService {
    @Override
    public boolean createAccount(User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        try {


            boolean result = accountDAO.createAccount(user);
        }catch (DAOException e) {
            throw new ServiceException(e);
        }


        return true;
    }
}
