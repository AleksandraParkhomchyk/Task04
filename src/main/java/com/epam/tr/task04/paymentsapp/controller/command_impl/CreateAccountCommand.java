package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateAccountCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();

        User user = new User();
        HttpSession session = request.getSession();

        Integer role = (Integer) session.getAttribute("role");
        Integer id = (Integer) session.getAttribute("id");

        user.setRole(role);
        user.setId(id);

        Account account = null;
        try {
            account = accountService.createAccount(user);

        } catch (ServiceException e) {
            e.printStackTrace();// todo: smth with exception
        }

        Integer a_id = account.getId();

        if (account == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Счет не создан");
        } else {
            session.setAttribute("a_id", a_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/successPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Счет создан");
        }
    }
}
