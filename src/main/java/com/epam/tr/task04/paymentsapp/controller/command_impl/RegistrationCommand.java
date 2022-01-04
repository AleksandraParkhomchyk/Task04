package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name;
        String surname;
        String login;
        String password;
        String passport;

        name = request.getParameter("name");
        surname = request.getParameter("surname");
        login = request.getParameter("login");
        password = request.getParameter("password");
        passport = request.getParameter("passport");

        boolean flag;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        flag = userService.registration(name, surname, login, password, passport);


        if (flag) {
            request.setAttribute("registrationInfo", "All ok");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "smth wrong");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}
