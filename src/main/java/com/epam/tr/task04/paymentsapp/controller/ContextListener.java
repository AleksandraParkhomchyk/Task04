package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.dao.connectionpool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private final static Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.debug("Server initialized");

        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOG.debug("Server stopped");

        ConnectionPool.getInstance().dispose();
    }
}
