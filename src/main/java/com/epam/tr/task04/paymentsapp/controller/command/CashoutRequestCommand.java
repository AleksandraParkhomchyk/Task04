package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.CashRequestService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CashoutRequestCommand implements Command {
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final AccountService accountService = serviceFactory.getAccountService();
    private static final CashRequestService cashRequestService = serviceFactory.getCashRequestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String amount = request.getParameter(Utils.CASHOUT_AMOUNT);
        HttpSession session = request.getSession(true);
        Integer userId = (Integer) session.getAttribute(Utils.ID);

        try {
            Account account = accountService.getAccountByUserId(userId);
            cashRequestService.cashout(account, amount);
            session.setAttribute(Message.MESSAGE_TO_USER, Message.SUCCESS_CASHOUT);
            response.sendRedirect(PagePath.URL_USERS_PAGE);

        } catch (InsufficientFundsException e) {
            session.setAttribute(Message.MESSAGE_TO_USER, Message.FAILURE_CASHOUT);
            response.sendRedirect(PagePath.URL_USERS_PAGE);
        } catch (ValidatorException e) {
            session.setAttribute(Message.MESSAGE_TO_USER, Message.INVALID_AMOUNT);
            response.sendRedirect(PagePath.URL_USERS_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
