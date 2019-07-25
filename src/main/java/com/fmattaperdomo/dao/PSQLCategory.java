/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.dao;

import com.fmattaperdomo.exceptions.GeneralException;
import com.fmattaperdomo.models.Category;
import com.fmattaperdomo.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class PSQLCategory {
    private final String GETBYID = "SELECT id_category, short_name, name FROM categories WHERE id_category = ?";
    private final String GETBYALL = "SELECT id_category, short_name, name FROM categories";
    
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet result;
    
    private Category convert(ResultSet rs) throws SQLException{
        Category category = new Category();
        category.setId_category(rs.getShort("id_category"));
        category.setShort_name(rs.getString("short_name"));
        category.setName(rs.getString("name"));
        return category;
    }
    
    public Category getByID(short id_category) throws GeneralException {
        Category category = null;
        try{
            connection = new PSQLConnection().connect();
            pstatement = connection.prepareStatement(GETBYID);
            pstatement.setShort(1, id_category);
            result = pstatement.executeQuery();
            if (result.next()){
                category = convert(result);
            }
        }catch(SQLException sqle){
            throw new GeneralException("Error al obtener el producto " + sqle.getMessage());
        }finally{
            closeResources();
        }
        return category; 
    }
    
    public List<Category> getByALL() throws GeneralException {
        List<Category> listCategories = new ArrayList<>();
        try{
            connection = new PSQLConnection().connect();
            pstatement = connection.prepareStatement(GETBYALL);
            result = pstatement.executeQuery();
            while (result.next()) {
                Category category = convert(result);
                listCategories.add(category);
            }
        }catch(SQLException sqle){
            throw new GeneralException("Error al obtener todos los productos " + sqle.getMessage());
        }finally{
            closeResources();
        }
        return listCategories;
    }
    
    private void closeResources(){
        try{
            if (result != null){
                result.close();
            }
            if (pstatement != null){
                pstatement.close();
            }
            if (connection != null){
                connection.close();
            }
            
        }catch(SQLException sqle){}
    }
    
}
