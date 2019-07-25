/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.dao;


import com.fmattaperdomo.utilities.Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author USER
 */
public class PSQLConnection {
    Connection connect(){
        Connection connection = null;
        Properties property = Utility.getInstanceDB().getConfigureDatabase();
        String url = "jdbc:postgresql://" + 
                property.getProperty("host") + 
                ":" + property.getProperty("port") + 
                "/" + property.getProperty("database");
        String user = property.getProperty("user");
        String password = property.getProperty("password");
        try {
            try {
            Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
            }
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return connection;
    }
}  
