package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.controller.impl.CommandProvider;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger Log = Logger.getLogger(Controller.class);

    public void doLog(){
        BasicConfigurator.configure();
        Log.debug("Test log");
    }

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
