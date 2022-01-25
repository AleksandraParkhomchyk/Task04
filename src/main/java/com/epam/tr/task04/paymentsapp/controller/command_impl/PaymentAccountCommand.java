package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentAccountCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String accountNumber;
        Double amount;

        accountNumber = request.getParameter("account");
        amount = Double.parseDouble(request.getParameter("amount"));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();
        HttpSession session = request.getSession(true);

        Integer userId = (Integer) session.getAttribute("id");

        try {
            Account account = accountService.getAccountByUserId(userId);
            accountService.accountPayment(account, accountNumber, amount);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountCreated.jsp");
            dispatcher.forward(request, response);


        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }

    }
}
