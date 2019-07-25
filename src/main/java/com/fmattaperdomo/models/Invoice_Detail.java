/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.models;

/**
 *
 * @author USER
 */
public class Invoice_Detail {
    private int id_invoice_detail;
    private int id_invoice_header;
    private Product product;
    private Category category;

    public Invoice_Detail() {
    }

    public Invoice_Detail(int id_invoice_detail, int id_invoice_header, Product product, Category category) {
        this.id_invoice_detail = id_invoice_detail;
        this.id_invoice_header = id_invoice_header;
        this.product = product;
        this.category = category;
    }

    public int getId_invoice_detail() {
        return id_invoice_detail;
    }

    public void setId_invoice_detail(int id_invoice_detail) {
        this.id_invoice_detail = id_invoice_detail;
    }

    public int getId_invoice_header() {
        return id_invoice_header;
    }

    public void setId_invoice_header(int id_invoice_header) {
        this.id_invoice_header = id_invoice_header;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
