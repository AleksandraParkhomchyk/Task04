package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
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
    private final String URL_REDIRECT = "/payments/controller?command=GO_TO_USERS_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();

        User user = new User();
        HttpSession session = request.getSession(true);
        Integer id = (Integer) session.getAttribute(Utils.ID);
        user.setId(id);

        if (session.getAttribute(Utils.ACCOUNT_ID) == null) {
            try {
                accountService.createAccount(user);
                Account account = accountService.getAccountByUserId(id);
                Integer account_id = account.getId();
                session.setAttribute(Utils.ACCOUNT_ID, account_id);
                session.setAttribute(Utils.ACCOUNT_NUMBER, account.getAccountNumber());
                session.setAttribute(Utils.BALANCE, account.getBalance());
                session.setAttribute(Utils.STATUS, account.getStatus());
                session.setAttribute(Message.MESSAGE, Message.ACCOUNT_CREATED);
                response.sendRedirect(URL_REDIRECT);


            } catch (ServiceException e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
                dispatcher.forward(request, response);
            }
        } else {
            session.setAttribute(Message.MESSAGE, Message.ACCOUNT_EXIST);
            response.sendRedirect(URL_REDIRECT);
        }
    }
}

