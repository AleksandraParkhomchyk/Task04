package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToUsersPageCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final AccountService ACCOUNT_SERVICE = SERVICE_FACTORY.getAccountService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        Integer id = (Integer) session.getAttribute(Utils.ID);

        try {
            ACCOUNT_SERVICE.getAccountByUserId(id);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);//todo redirect
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_PAGE);
        dispatcher.forward(request, response);
    }
}