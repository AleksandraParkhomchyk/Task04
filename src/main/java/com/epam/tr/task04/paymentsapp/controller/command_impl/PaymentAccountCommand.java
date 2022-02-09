package com.epam.tr.task04.paymentsapp.controller.command_impl;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.services.AccountService;
import com.epam.tr.task04.paymentsapp.services.ServiceFactory;
import com.epam.tr.task04.paymentsapp.services.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.services.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentAccountCommand implements Command {
    private final String URL_REDIRECT = "/payments/controller?command=GO_TO_USERS_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String targetAccount = request.getParameter(Utils.TARGET_ACCOUNT);
        Double amount = Double.parseDouble(request.getParameter(Utils.PAYMENT_AMOUNT));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AccountService accountService = serviceFactory.getAccountService();

        HttpSession session = request.getSession(true);
        Integer userId = (Integer) session.getAttribute(Utils.ID);

        try {
            Account account = accountService.getAccountByUserId(userId);

            boolean result = accountService.accountPayment(account, targetAccount, amount, userId);

            Account accountUPD = accountService.getAccountByUserId(userId);
            if (result) {
                session.setAttribute("success", "Payment made successful");//todo messages
                session.setAttribute(Utils.BALANCE, accountUPD.getBalance());
                response.sendRedirect(URL_REDIRECT);

            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
                dispatcher.forward(request, response);
            }
        } catch (InsufficientFundsException e) {
            session.setAttribute("wrong", "  You have insufficient funds");//todo messages
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e){
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
