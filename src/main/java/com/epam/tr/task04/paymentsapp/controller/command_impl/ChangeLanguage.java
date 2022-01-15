package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);

        String url = (String) request.getSession().getAttribute("url");

        response.sendRedirect(url);
    }
}
