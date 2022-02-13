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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CashRequestService cashRequestService = serviceFactory.getCashRequestService();
        AccountService accountService = serviceFactory.getAccountService();
        Double amount;

        List<CashoutRequest> list;

        try {
            String requestIDGot = request.getParameter(Utils.APPROVE);
            Integer requestID = Integer.parseInt(requestIDGot);
            amount = cashRequestService.getAmountByRequestId(requestID);
            Integer accountId = accountService.getAccountIdByRequestId(requestID);
            Account account = accountService.getAccountById(accountId);
            Integer userId = account.getOwnerId();


            cashRequestService.updateRequestStatusApproved(account, requestID, amount, userId);
            list = cashRequestService.getAllCashoutRequests();
            request.setAttribute(Utils.ALL_REQUESTS, list);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ADMIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
