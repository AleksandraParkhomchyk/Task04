package com.epam.tr.task04.paymentsapp.controller.impl;

import com.epam.tr.task04.paymentsapp.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Logination");
        String name;
        String surname;

        name = request.getParameter("name");
        surname = request.getParameter("password");

        boolean flag = true; //stub

        if(flag) {
            request.setAttribute("userName", "Anna");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
            dispatcher.forward(request, response);
        }

    }
}
