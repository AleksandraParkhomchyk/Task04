package com.epam.tr.task04.paymentsapp.controller.command;

import com.epam.tr.task04.paymentsapp.controller.Command;
import com.epam.tr.task04.paymentsapp.controller.constant.PagePath;
import com.epam.tr.task04.paymentsapp.controller.constant.Utils;
import com.epam.tr.task04.paymentsapp.entity.CashoutRequest;
import com.epam.tr.task04.paymentsapp.service.CashRequestService;
import com.epam.tr.task04.paymentsapp.service.ServiceFactory;
import com.epam.tr.task04.paymentsapp.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CancelRequestCommand implements Command {
    private static final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private static final CashRequestService CASH_REQUEST_SERVICE = SERVICE_FACTORY.getCashRequestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CashoutRequest> list;
        HttpSession session = request.getSession(true);
        Integer accountId = (Integer) session.getAttribute(Utils.ACCOUNT_ID);

        try {
            String requestIDGot = request.getParameter(Utils.CANCEL);
            Integer requestID = Integer.parseInt(requestIDGot);
            CASH_REQUEST_SERVICE.cancelCashRequest(requestID);
            list = CASH_REQUEST_SERVICE.getUsersRequests(accountId);
            request.setAttribute(Utils.USER_REQUESTS, list);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.REQUESTS_PAGE);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);//todo redirect
            dispatcher.forward(request, response);
        }
    }
}
