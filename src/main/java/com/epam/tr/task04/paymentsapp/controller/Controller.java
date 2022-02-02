package com.epam.tr.task04.paymentsapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class Controller extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(Controller.class);

    private final CommandProvider provider = new CommandProvider();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(String.format("requested url: %s", request.getRequestURI()));

        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(String.format("requested url: %s", request.getRequestURI()));

        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", "en");
        }

        String commandName = request.getParameter("command");
        Command command = provider.getCommand(commandName);
        command.execute(request, response);
    }
}
