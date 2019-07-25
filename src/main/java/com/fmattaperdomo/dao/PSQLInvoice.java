/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.dao;

import com.fmattaperdomo.exceptions.GeneralException;
import com.fmattaperdomo.models.Invoice_Detail;
import com.fmattaperdomo.models.Invoice_Header;
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
public class PSQLInvoice {
    private final String HEADER = "INSERT INTO invoices_header (full_name, address_main, phone_main) VALUES (?,?,?) RETURNING id_invoice, invoice_date, full_name, address_main, phone_main";
    private final String DETAIL = "INSERT INTO invoices_detail (id_invoice_header, id_product, category_id, value) VALUES (?,?,?,?)";
    
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet result;
    
    public Invoice_Header insertInvoice(String full_name, String address_main, String phone_main, List<Product> shoppingCart) throws GeneralException {
        Invoice_Header invoiceHeader = null;
        try{
            invoiceHeader = insertInvoiceHeader(full_name,address_main,phone_main);
            insertInvoiceDetail(shoppingCart, invoiceHeader);
        }catch(GeneralException | NullPointerException ge){
            throw new GeneralException("Error al insertar ventas " + ge.getMessage());
        }finally{
            closeResources();
        }
        return invoiceHeader; 
    }

    public Invoice_Header insertInvoiceHeader(String full_name, String address_main, String phone_main) throws GeneralException {
        Invoice_Header invoiceHeader = null;
        try{
            connection = new PSQLConnection().connect();
            pstatement = connection.prepareStatement(HEADER);
            pstatement.setString(1, full_name);
            pstatement.setString(2, address_main);
            pstatement.setString(3, phone_main);
            result = pstatement.executeQuery();
            if (result.next()){
                invoiceHeader = new Invoice_Header();
                invoiceHeader.setId_invoice(result.getInt("id_invoice"));
                //System.out.println("invoice_date");
                //invoiceHeader.setInvoice_date(result.getDate("invoice_date").toLocalDate());
                invoiceHeader.setFull_name(result.getString("full_name"));
                invoiceHeader.setAddress_main(result.getString("address_main"));
                invoiceHeader.setPhone_main(result.getString("phone_main"));
            }
        }catch(SQLException | NullPointerException sqle){
            throw new GeneralException("Error al insertar el encabezado de venta: " + sqle.getMessage());
        }finally{
            closeResources();
        }
        return invoiceHeader;    
    }
    
    private void insertInvoiceDetail(List<Product> shoppingCart, Invoice_Header invoiceHeader) throws GeneralException{
        try{
            connection = new PSQLConnection().connect();
            pstatement = connection.prepareStatement(DETAIL);
            for(Product product : shoppingCart){
                Invoice_Detail invoiceDetail = new Invoice_Detail();
                invoiceDetail.setId_invoice_header(invoiceHeader.getId_invoice());
                invoiceDetail.setProduct(product);
                pstatement.setInt(1, invoiceDetail.getId_invoice_header());
                pstatement.setInt(2, invoiceDetail.getProduct().getId_product());
                pstatement.setShort(3,invoiceDetail.getProduct().getCategory().getId_category());
                pstatement.setFloat(4, invoiceDetail.getProduct().getValue());
                pstatement.executeUpdate();
                invoiceHeader.addInvoice_detail(invoiceDetail);
            }
        }catch(SQLException sqle){
            throw new GeneralException("Error al insertar el detalle de venta: " + sqle.getMessage());
        }finally{
            closeResources();
        }
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
