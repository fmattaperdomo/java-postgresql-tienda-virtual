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
public class Category {
    private short id_category;
    private String short_name;
    private String name;

    public Category(short id_category, String short_name, String name) {
        this.id_category = id_category;
        this.short_name = short_name;
        this.name = name;
    }

    public Category() {
    }

    public short getId_category() {
        return id_category;
    }

    public void setId_category(short id_category) {
        this.id_category = id_category;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

