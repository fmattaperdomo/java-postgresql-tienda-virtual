/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.servlets;

import com.fmattaperdomo.utilities.Utility;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author USER
 */
@WebListener
public class MyListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        String host = sce.getServletContext().getInitParameter("host");
        String port = sce.getServletContext().getInitParameter("port");
        String database = sce.getServletContext().getInitParameter("database");
        String user = sce.getServletContext().getInitParameter("user");
        String password = sce.getServletContext().getInitParameter("password");
       
        Properties propertyConnection = new Properties();
        propertyConnection.setProperty("host", host);
        propertyConnection.setProperty("port", port);
        propertyConnection.setProperty("database", database);
        propertyConnection.setProperty("user", user);
        propertyConnection.setProperty("password", password);
        
        Utility.getInstanceDB().setConfigureDatabase(propertyConnection);

    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
