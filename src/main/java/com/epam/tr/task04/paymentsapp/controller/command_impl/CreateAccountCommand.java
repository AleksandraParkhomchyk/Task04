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

        try {
            Account account = accountService.getAccountByUserId(id);
            Integer a_id = account.getId();
            if (a_id == null){
                accountService.createAccount(user);
                session.setAttribute("a_id", a_id);
            } else {
                System.out.println("Счет уже cуществует");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            e.printStackTrace();// todo: smth with exception
        }
    }
}
