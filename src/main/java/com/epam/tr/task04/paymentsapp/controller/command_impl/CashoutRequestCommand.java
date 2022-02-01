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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CashoutRequestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        Double cAmount;

        cAmount = Double.parseDouble(request.getParameter("cashout_amount"));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();
        CashRequestService cashRequestService = serviceFactory.getCashRequestService();
        HttpSession session = request.getSession(true);

        Integer userId = (Integer) session.getAttribute("id");

        try {
            Account account = accountService.getAccountByUserId(userId);
            CashoutRequest cashoutRequest = cashRequestService.cashout(account, cAmount);
            Integer requestId = cashoutRequest.getId();
            session.setAttribute("requestId", requestId);
            Account accountUPD = accountService.getAccountByUserId(userId);
            session.setAttribute("message1", "Your account number is " + accountUPD.getAccountNumber() + ". Balance " + accountUPD.getBalance());
            session.setAttribute("success", "Cashout request was made successful");
            response.sendRedirect("/payments_app_war_exploded/controller?command=GO_TO_USERS_PAGE");

        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
