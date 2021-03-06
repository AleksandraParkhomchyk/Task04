package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.Message;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.InsufficientFundsException;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;
import com.epam.tr.task04.paymentsapp.service.validator.ValidatorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final AccountService ACCOUNT_SERVICE = SERVICE_FACTORY.getAccountService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String targetAccount = request.getParameter(Utils.TARGET_ACCOUNT);
        String amount = request.getParameter(Utils.PAYMENT_AMOUNT);

        HttpSession session = request.getSession(true);
        Integer userId = (Integer) session.getAttribute(Utils.ID);

        try {
            Account account = ACCOUNT_SERVICE.getAccountByUserId(userId);
            boolean result = ACCOUNT_SERVICE.accountPayment(account, targetAccount, amount, userId);
            Account accountUPD = ACCOUNT_SERVICE.getAccountByUserId(userId);
            if (result) {
                session.setAttribute(Utils.MESSAGE, Message.SUCCESS_PAYMENT);
                session.setAttribute(Utils.BALANCE, accountUPD.getBalance());
                response.sendRedirect(PagePath.TO_USER_PAGE);
            }
        } catch (InsufficientFundsException e) {
            session.setAttribute(Utils.MESSAGE, Message.INSUFFICIENT_FUNDS);
            response.sendRedirect(PagePath.TO_USER_PAGE);
        } catch (ValidatorException e) {
            session.setAttribute(Utils.MESSAGE, Message.INVALID_DETAILS);
            response.sendRedirect(PagePath.TO_USER_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
