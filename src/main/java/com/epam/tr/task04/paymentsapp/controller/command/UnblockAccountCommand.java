package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.Account;
import com.epam.tr.task04.paymentsapp.service.AccountService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UnblockAccountCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final AccountService ACCOUNT_SERVICE = SERVICE_FACTORY.getAccountService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Account> list;

        String accountIDGot = request.getParameter(Utils.UNBLOCK);
        Integer accountID = Integer.parseInt(accountIDGot);

        try {
            ACCOUNT_SERVICE.unblockAccount(accountID);
            list = ACCOUNT_SERVICE.getAllBlockedAccounts();
            request.setAttribute(Utils.ALL_ACCOUNTS_BLOCKED, list);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ADMIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);//todo redirect
            dispatcher.forward(request, response);
        }
    }
}
