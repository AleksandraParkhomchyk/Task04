package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.CashRequestService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.services.validator.ValidatorException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CashoutRequestCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String amount = request.getParameter(Utils.CASHOUT_AMOUNT);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();
        CashRequestService cashRequestService = serviceFactory.getCashRequestService();
        HttpSession session = request.getSession(true);

        Integer userId = (Integer) session.getAttribute(Utils.ID);

        try {
            Account account = accountService.getAccountByUserId(userId);
            cashRequestService.cashout(account, amount);
            session.setAttribute(Message.MESSAGE_TO_USER, Message.SUCCESS_CASHOUT);
            response.sendRedirect(PagePath.URL_USERS_PAGE);

        } catch (InsufficientFundsException e) {
            session.setAttribute(Message.MESSAGE_TO_USER, Message.FAILURE_CASHOUT);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ValidatorException e) {
            session.setAttribute(Message.MESSAGE_TO_USER, Message.INVALID_AMOUNT);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
