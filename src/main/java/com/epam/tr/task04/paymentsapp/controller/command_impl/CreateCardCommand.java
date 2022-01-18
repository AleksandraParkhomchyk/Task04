package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.entity.Card;
import com.epam.tr.task04.paymentsapp.entity.User;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.CardService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateCardCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CardService cardService = serviceFactory.getCardService();

        User user = new User();
        Account account = new Account();
        HttpSession session = request.getSession();

        Integer role = (Integer) session.getAttribute("role");
        Integer id = (Integer) session.getAttribute("id");
        Integer a_id = (Integer) session.getAttribute("a_id");

        user.setRole(role);
        user.setId(id);

        Card card = null;
        try {
            card = cardService.createCard(account);
        } catch (ServiceException e) {
            e.printStackTrace();// todo: smth with exception
        }

        Integer c_id = card.getId();

        if (card == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Карта не создана");
        } else {
            session.setAttribute("с_id", c_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountCreated.jsp");
            dispatcher.forward(request, response);
            System.out.println("Карта создана");
        }
    }
}

