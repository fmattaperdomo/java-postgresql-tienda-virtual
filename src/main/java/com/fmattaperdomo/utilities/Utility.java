/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.utilities;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public final class Utility {
    private static final Utility instanceDB = new Utility();
    private Properties propertyDB;

    public Utility() {
    }
    
    public static Utility getInstanceDB(){
        return instanceDB;
    }
    
    public void setConfigureDatabase(Properties propertyDB){
        this.propertyDB = propertyDB;
    }
    
    public Properties getConfigureDatabase(){
        return this.propertyDB;
    }
    
    public static void gotoPage(HttpServletRequest request, HttpServletResponse response, ServletContext context, String address) throws ServletException, IOException, IllegalStateException{
        RequestDispatcher dispatcher = context.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
    
    
    
    
}
