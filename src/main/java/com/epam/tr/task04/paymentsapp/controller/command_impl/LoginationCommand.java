package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute("url", "/payments_app_war_exploded/controller?command=GO_TO_USERS_PAGE");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<CashoutRequest> list;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        AccountService accountService = serviceFactory.getAccountService();
        CashRequestService cashRequestService = serviceFactory.getCashRequestService();

        try {
            User user = userService.authorisation(login, password);
            Integer role = user.getRole();
            Integer id = user.getId();

            if (user.getRole() == null) {
                throw new ServiceException();
            }

            session.setAttribute("id", id);

            if (role == 1) {
                Account account = accountService.getAccountByUserId(id);
                Integer account_id = account.getId();
                session.setAttribute("account_id", account_id);

                if (account.getId() == null) {
                    request.setAttribute("message", "Please, create an account.");

                } else {
                    session.setAttribute("accountNumber", account);
                    session.setAttribute("accountN", account.getAccountNumber());
                    session.setAttribute("balance", account.getBalance());
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
                dispatcher.forward(request, response);
            } else if (role == 2) {
                list = cashRequestService.getAllCashoutRequests();
                request.setAttribute("AllRequests", list);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NotAuthorizedException e) {
            session.setAttribute("wrong", "Wrong login or password");
            response.sendRedirect("/payments_app_war_exploded/controller?command=GO_TO_LOGINATION_PAGE");
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
