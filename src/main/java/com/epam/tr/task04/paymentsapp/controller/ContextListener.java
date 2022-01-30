package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    public static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.debug("Server initialized");

        ConnectionPool.getInstance();
        System.out.println("Server started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        ConnectionPool.getInstance().dispose();
        System.out.println("Server stopped");
    }

}
