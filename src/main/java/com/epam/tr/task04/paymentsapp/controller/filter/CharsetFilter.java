package com.epam.tr.task04.paymentsapp.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

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
