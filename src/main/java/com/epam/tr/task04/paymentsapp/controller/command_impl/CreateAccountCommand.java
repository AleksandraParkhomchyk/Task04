package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateAccountCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();

        User user = new User();
        HttpSession session = request.getSession(true);
        Integer id = (Integer) session.getAttribute("id");
        user.setId(id);

        if (session.getAttribute("account_id") == null) {
            try {
                accountService.createAccount(user);

                Account account = accountService.getAccountByUserId(id);
                Integer account_id = account.getId();
                session.setAttribute("account_id", account_id);
                session.setAttribute("success", "Your account created");
                session.setAttribute("accountN", account.getAccountNumber());
                session.setAttribute("balance", account.getBalance());
                response.sendRedirect("/payments/controller?command=GO_TO_USERS_PAGE");

            } catch (ServiceException e) {
                 //todo: exception
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}

