package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        ConnectionPool.getInstance().dispose();
    }
}
