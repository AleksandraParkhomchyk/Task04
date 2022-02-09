package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToHomePageCommand implements Command {

    private final String URL_NAME = "/payments/controller?command=GO_TO_HOME_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        session.setAttribute("url", URL_NAME);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE);
        dispatcher.forward(request, response);
    }
}
