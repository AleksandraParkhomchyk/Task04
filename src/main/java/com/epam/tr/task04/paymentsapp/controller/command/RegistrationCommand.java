package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.UserService;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final UserService USER_SERVICE = SERVICE_FACTORY.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter(Utils.NAME);
        String surname = request.getParameter(Utils.SURNAME);
        String login = request.getParameter(Utils.LOGIN);
        String password = request.getParameter(Utils.PASSWORD);
        String passport = request.getParameter(Utils.PASSPORT);

        try {
            USER_SERVICE.registration(name, surname, login, password, passport);
            response.sendRedirect(PagePath.HOME_PAGE);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
