package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    public static final Logger LOGGER = Logger.getLogger(ContextListener.class);


    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().initPoolData();
            System.out.println("Server started");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Unable to initialize connection pool.", e);
            throw new RuntimeException("Unable to initialize connection pool.", e);
        }

    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Server stopped");
    }

}
