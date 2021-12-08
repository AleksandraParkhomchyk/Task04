package com.epam.tr.task04.paymentsapp.controller.impl;

import com.epam.tr.task04.paymentsapp.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Registration");
        String name;
        String surname;

        name = request.getParameter("name");
        surname = request.getParameter("surname");

        boolean flag = true; //stub

        if(flag) {
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
