package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Transaction;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.TransactionService;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsersTransactionsCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TransactionService transactionService = serviceFactory.getTransactionService();

        List<Transaction> list;

        try {
            list = transactionService.getAllTransactions();
            request.setAttribute("AllTransactions", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}