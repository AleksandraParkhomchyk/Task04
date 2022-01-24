package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
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

public class LoginationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;

        login = request.getParameter("login");
        password = request.getParameter("password");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        AccountService accountService = serviceFactory.getAccountService();
        HttpSession session = request.getSession(true);


        try {
            User user = userService.authorisation(login, password);
            Integer role = user.getRole();
            Integer id = user.getId();
            Account account = accountService.getAccountByUserId(id);
            //String cardNumber = cardService.getCardByLogin(user);

            if (user.getRole() == null) {
                throw new ServiceException();
            }

            if (role == 1) {
                request.setAttribute("message", "Hello, " + login + "!" + " Account Number " + account.getAccountNumber() + ". Balance " + account.getBalance());
                session.setAttribute("id", id);
                session.setAttribute("role", role);
                session.setAttribute("login", login);
                session.setAttribute("accountNumber", account);
                //session.setAttribute("cardNumber", cardNumber);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
                dispatcher.forward(request, response);
                System.out.println("Зашел юзер");
            } else if (role == 2) {
                session.setAttribute("role", role);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp");
                dispatcher.forward(request, response);
                System.out.println("Зашел админ");
            }
        } catch (NotAuthorizedException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logination.jsp");
            dispatcher.forward(request, response); //todo message
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
            dispatcher.forward(request, response);
        }

    }
}
