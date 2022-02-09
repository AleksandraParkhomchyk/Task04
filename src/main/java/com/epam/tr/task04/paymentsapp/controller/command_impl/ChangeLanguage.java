package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String locale = request.getParameter(Utils.LOCALE);
        request.getSession().setAttribute(Utils.LOCALE, locale);

        String url = (String) request.getSession().getAttribute(Utils.URL);

        response.sendRedirect(url);
    }
}
