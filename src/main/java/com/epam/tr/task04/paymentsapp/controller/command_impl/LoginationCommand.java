package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.UserService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.NotAuthorizedException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginationCommand implements Command {

    private final String URL_NAME = "/payments/controller?command=GO_TO_USERS_PAGE";
    private final String URL_REDIRECT = "/payments/controller?command=GO_TO_LOGINATION_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute(Utils.URL, URL_NAME);

        String login = request.getParameter(Utils.LOGIN);
        String password = request.getParameter(Utils.PASSWORD);

        List<CashoutRequest> list;
        List<Account> list1;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        AccountService accountService = serviceFactory.getAccountService();
        CashRequestService cashRequestService = serviceFactory.getCashRequestService();

        try {
            User user = userService.authorisation(login, password);
            Integer role = user.getRole();

            if (user.getRole() == null) {
                throw new ServiceException(); //todo check
            }

            Integer id = user.getId();
            session.setAttribute(Utils.ID, id);

            if (role == 1) {
                Account account = accountService.getAccountByUserId(id);

                if (account.getId() == null) {
                    session.setAttribute(Message.MESSAGE, Message.CREATE_ACCOUNT);

                } else {
                    if (account.getStatus() == 1) {

                        session.setAttribute(Utils.ACCOUNT_ID, account.getId());
                        session.setAttribute(Utils.ACCOUNT_NUMBER, account.getAccountNumber());
                        session.setAttribute(Utils.BALANCE, account.getBalance());
                        session.setAttribute(Utils.STATUS, account.getStatus());

                    } else if (account.getStatus() == 2) {
                        session.setAttribute(Utils.ACCOUNT_ID, account.getId());
                        session.setAttribute(Utils.ACCOUNT_NUMBER, account.getAccountNumber());
                        session.setAttribute(Utils.BALANCE, account.getBalance());
                        session.setAttribute(Utils.STATUS, account.getStatus());
                        session.setAttribute(Message.MESSAGE, Message.ACCOUNT_BLOCKED);

                    }
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_PAGE);
                dispatcher.forward(request, response);

            } else if (role == 2) {
                list = cashRequestService.getAllCashoutRequests();
                request.setAttribute(Utils.ALL_REQUESTS, list);
                list1 = accountService.getAllBlockedAccounts();
                request.setAttribute(Utils.ALL_ACCOUNTS_BLOCKED, list1);
                RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ADMIN_PAGE);
                dispatcher.forward(request, response);
            }
        } catch (NotAuthorizedException e) {
            session.setAttribute(Message.MESSAGE, Message.WRONG_LOGIN_PASSWORD);
            response.sendRedirect(URL_REDIRECT);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
