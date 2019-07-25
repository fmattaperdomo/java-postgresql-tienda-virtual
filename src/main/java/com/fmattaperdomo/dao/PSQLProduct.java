/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.dao;

import com.fmattaperdomo.exceptions.GeneralException;
import com.fmattaperdomo.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class PSQLProduct {
    private final String GETBYID = "SELECT id_product, name, description, value, image FROM products WHERE id_product = ?";
    private final String GETBYALL = "SELECT id_product, name, description, value, image FROM products";
    
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet result;
    
    private Product convert(ResultSet rs) throws SQLException{
        Product product = new Product();
        product.setId_product(rs.getInt("id_product"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setValue(rs.getFloat("value"));
        product.setImage(rs.getString("image"));
        return product;
    }
    
    public Product getByID(int id_product) throws GeneralException {
        Product product = null;
        try{
            connection = new PSQLConnection().connect();
            pstatement = connection.prepareStatement(GETBYID);
            pstatement.setInt(1, id_product);
            result = pstatement.executeQuery();
            if (result.next()){
                product = convert(result);
            }
        }catch(SQLException sqle){
            throw new GeneralException("Error al obtener el producto " + sqle.getMessage());
        }finally{
            closeResources();
        }
        return product; 
    }
    
    public List<Product> getByALL() throws GeneralException {
        List<Product> listProducts = new ArrayList<>();
        try{
            connection = new PSQLConnection().connect();
            pstatement = connection.prepareStatement(GETBYALL);
            result = pstatement.executeQuery();
            while (result.next()) {
                Product product = convert(result);
                listProducts.add(product);
            }
        }catch(SQLException | NullPointerException sqle){
            throw new GeneralException("Error al obtener todos los productos " + sqle.getMessage());
        }finally{
            closeResources();
        }
        return listProducts;
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
