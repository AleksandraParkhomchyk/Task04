package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.CashRequestService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApproveRequestCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final CashRequestService CASH_REQUEST_SERVICE = SERVICE_FACTORY.getCashRequestService();
    private static final AccountService ACCOUNT_SERVICE = SERVICE_FACTORY.getAccountService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Double amount;

        List<CashoutRequest> list;

        try {
            String requestIDGot = request.getParameter(Utils.APPROVE);
            Integer requestID = Integer.parseInt(requestIDGot);
            amount = CASH_REQUEST_SERVICE.getAmountByRequestId(requestID);
            Integer accountId = ACCOUNT_SERVICE.getAccountIdByRequestId(requestID);
            Account account = ACCOUNT_SERVICE.getAccountById(accountId);
            Integer userId = account.getOwnerId();


            CASH_REQUEST_SERVICE.updateRequestStatusApproved(account, requestID, amount, userId);
            list = CASH_REQUEST_SERVICE.getAllCashoutRequests();
            request.setAttribute(Utils.ALL_REQUESTS, list);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ADMIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);// todo redirect
            dispatcher.forward(request, response);
        }
    }
}
