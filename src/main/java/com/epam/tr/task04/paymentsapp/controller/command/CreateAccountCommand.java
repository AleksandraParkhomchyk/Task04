package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateAccountCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final AccountService ACCOUNT_SERVICE = SERVICE_FACTORY.getAccountService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        HttpSession session = request.getSession(true);
        Integer id = (Integer) session.getAttribute(Utils.ID);
        user.setId(id);

        if (session.getAttribute(Utils.ACCOUNT_ID) == null) {
            try {
                ACCOUNT_SERVICE.createAccount(user);
                Account account = ACCOUNT_SERVICE.getAccountByUserId(id);
                Integer accountId = account.getId();
                session.setAttribute(Utils.ACCOUNT_ID, accountId);
                session.setAttribute(Utils.ACCOUNT_NUMBER, account.getAccountNumber());
                session.setAttribute(Utils.BALANCE, account.getBalance());
                session.setAttribute(Utils.STATUS, account.getStatus());
                session.setAttribute(Message.MESSAGE_TO_USER, Message.ACCOUNT_CREATED);
                response.sendRedirect(PagePath.URL_USERS_PAGE);


            } catch (ServiceException e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
                dispatcher.forward(request, response);
            }
        } else {
            session.setAttribute(Message.MESSAGE_TO_USER, Message.ACCOUNT_EXIST);
            response.sendRedirect(PagePath.URL_USERS_PAGE);
        }
    }
}

