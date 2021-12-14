package com.epam.tr.task04.paymentsapp.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final Logger LOGGER = Logger.getLogger(Controller.class);

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        process(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String commandName = request.getParameter("command");

        Command command = provider.getCommand(commandName);

        command.execute(request, response);
    }

}
