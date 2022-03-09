package com.epam.tr.task04.paymentsapp.controller.constant;

public final class PagePath {

    private PagePath() {
    }

    public static final String HOME_PAGE = "jsp/homePage.jsp";
    public static final String USER_PAGE = "jsp/userPage.jsp";
    public static final String ADMIN_PAGE = "jsp/adminPage.jsp";
    public static final String ERROR_PAGE = "jsp/errorPage.jsp";
    public static final String REGISTRATION_PAGE = "jsp/registration.jsp";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String HISTORY_PAGE = "jsp/historyPage.jsp";
    public static final String REQUESTS_PAGE = "jsp/requestsPage.jsp";

    public static final String URL_USERS_PAGE = "/payments/controller?command=GO_TO_USERS_PAGE";
    public static final String URL_REQUESTS_PAGE = "/payments/controller?command=GET_USERS_REQUESTS";
}
