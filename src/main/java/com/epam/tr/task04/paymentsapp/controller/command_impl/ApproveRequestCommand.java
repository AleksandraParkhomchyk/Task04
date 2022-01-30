package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApproveRequestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CashRequestService cashRequestService = serviceFactory.getCashRequestService();
        AccountService accountService = serviceFactory.getAccountService();
        Double amount;

        List<CashoutRequest> list;

        String requestIDGot = request.getParameter("approve");
        Integer requestID = Integer.parseInt(requestIDGot);
        amount = cashRequestService.getAmountByRequestId(requestID);
        Integer accountId = accountService.getAccountIdByRequestId(requestID);
        Account account = accountService.getAccountById(accountId);


        try {
            cashRequestService.updateRequestStatusApproved(account, requestID, amount);
            list = cashRequestService.getAllCashoutRequests();
            request.setAttribute("AllRequests", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
