package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.services.UserService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;

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

        String role;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        role = userService.authorisation(login, password);

        HttpSession session = request.getSession(true);


        if ("user".equals(role)) {
            session.setAttribute("role", role);
            session.setAttribute("login", login);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Зашел юзер");
        } else if ("admin".equals(role)) {
            session.setAttribute("role", role);
            //request.setAttribute("userName", "Alex");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Зашел админ");
        } else {
            session.setAttribute("role", role);
            //request.setAttribute("userName", "Alex");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginErrorPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Не зашел");


        }

    }
}
