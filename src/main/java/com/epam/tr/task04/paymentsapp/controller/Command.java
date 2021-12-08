package com.epam.tr.task04.paymentsapp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
