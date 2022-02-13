package com.epam.tr.task04.paymentsapp.controller.constant;

public final class PagePath {

    private PagePath() { }

    public static final String HOME_PAGE = "WEB-INF/jsp/homePage.jsp";
    public static final String USER_PAGE = "WEB-INF/jsp/userPage.jsp";
    public static final String ADMIN_PAGE = "WEB-INF/jsp/adminPage.jsp";
    public static final String ERROR_PAGE = "WEB-INF/jsp/errorPage.jsp";
    public static final String LOGINATION_PAGE = "WEB-INF/jsp/logination.jsp";
    public static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration.jsp";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String HISTORY_PAGE = "WEB-INF/jsp/historyPage.jsp";
    public static final String REQUESTS_PAGE = "WEB-INF/jsp/requestsPage.jsp";

    public static final String URL_LOGINATION = "/payments/controller?command=GO_TO_LOGINATION_PAGE";
    public static final String URL_USERS_PAGE = "/payments/controller?command=GO_TO_USERS_PAGE";
    public static final String URL_REQUESTS_PAGE = "/payments/controller?command=GET_USERS_REQUESTS";
}
