/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Invoice_Header {
    private int id_invoice;
    private LocalDate invoice_date;
    private String full_name;
    private String address_main;
    private String phone_main;
    private List<Invoice_Detail> invoice_detail = new ArrayList<>();

    public Invoice_Header() {
    }

    public Invoice_Header(int id_invoice, LocalDate invoice_date, String full_name, String address_main, String phone_main) {
        this.id_invoice = id_invoice;
        this.invoice_date = invoice_date;
        this.full_name = full_name;
        this.address_main = address_main;
        this.phone_main = phone_main;
    }

    public int getId_invoice() {
        return id_invoice;
    }

    public void setId_invoice(int id_invoice) {
        this.id_invoice = id_invoice;
    }

    public LocalDate getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDate invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress_main() {
        return address_main;
    }

    public void setAddress_main(String address_main) {
        this.address_main = address_main;
    }

    public String getPhone_main() {
        return phone_main;
    }

    public void setPhone_main(String phone_main) {
        this.phone_main = phone_main;
    }

    public List<Invoice_Detail> getInvoice_detail() {
        return invoice_detail;
    }

    public void setInvoice_detail(List<Invoice_Detail> invoice_detail) {
        this.invoice_detail = invoice_detail;
    }
    
    public void addInvoice_detail(Invoice_Detail item){
        invoice_detail.add(item);
    }
    
    
}
