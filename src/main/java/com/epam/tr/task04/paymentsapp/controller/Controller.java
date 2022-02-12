package com.epam.tr.task04.paymentsapp.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class Controller extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String commandName = request.getParameter("command");
        Command command = provider.getCommand(commandName);
        command.execute(request, response);
    }
}
