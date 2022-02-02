package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        session.invalidate();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logination.jsp");
        dispatcher.forward(request, response);
    }
}
