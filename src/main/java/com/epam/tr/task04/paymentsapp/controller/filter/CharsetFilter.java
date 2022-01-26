package com.epam.tr.task04.paymentsapp.controller.filter;


import com.epam.tr.task04.paymentsapp.controller.Controller;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CharsetFilter implements Filter {

    public static final Logger LOGGER = Logger.getLogger(CharsetFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        encoding = filterConfig.getInitParameter("requestEncoding");

        if (encoding == null) encoding = "utf-8";

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
