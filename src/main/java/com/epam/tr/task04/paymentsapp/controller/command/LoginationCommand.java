package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Redirect;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.CashRequestService;
import com.epam.tr.task04.paymentsapp.service.UserService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.NotAuthorizedException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginationCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final UserService USER_SERVICE = SERVICE_FACTORY.getUserService();
    private static final AccountService ACCOUNT_SERVICE = SERVICE_FACTORY.getAccountService();
    private static final CashRequestService CASH_REQUEST_SERVICE = SERVICE_FACTORY.getCashRequestService();

    private static final String URL_NAME = "/payments/controller?command=LOGINATION";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute(Utils.URL, URL_NAME);

        String login = request.getParameter(Utils.LOGIN);
        String password = request.getParameter(Utils.PASSWORD);

        List<CashoutRequest> cashoutRequestList;
        List<Account> blockedAccountList;

        try {
            User user = USER_SERVICE.authorisation(login, password);
            Integer role = user.getRole();
            if (user.getRole() == null) {
                throw new ServiceException();
            }

            session.setAttribute(Utils.ROLE, role);
            Integer id = user.getId();
            session.setAttribute(Utils.ID, id);

            if (role == 1) {
                Account account = ACCOUNT_SERVICE.getAccountByUserId(id);

                if (account.getId() == null) {
                    session.setAttribute(Message.MESSAGE_TO_USER, Message.CREATE_ACCOUNT);
                } else {
                    session.setAttribute(Utils.ACCOUNT_ID, account.getId());
                    session.setAttribute(Utils.ACCOUNT_NUMBER, account.getAccountNumber());
                    session.setAttribute(Utils.BALANCE, account.getBalance());
                    session.setAttribute(Utils.STATUS, account.getStatus());
                    if (account.getStatus() == 2) {
                        session.setAttribute(Message.MESSAGE_TO_USER, Message.ACCOUNT_BLOCKED);
                    }
                }
                response.sendRedirect(Redirect.TO_USER_PAGE);

            } else if (role == 2) {
                cashoutRequestList = CASH_REQUEST_SERVICE.getAllCashoutRequests();
                request.setAttribute(Utils.ALL_REQUESTS, cashoutRequestList);
                blockedAccountList = ACCOUNT_SERVICE.getAllBlockedAccounts();
                request.setAttribute(Utils.ALL_ACCOUNTS_BLOCKED, blockedAccountList);
                response.sendRedirect(Redirect.TO_ADMIN_PAGE);
            }
        } catch (NotAuthorizedException e) {
            session.setAttribute(Utils.MESSAGE, Message.WRONG_LOGIN_PASSWORD);
            response.sendRedirect(Redirect.TO_HOME_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
