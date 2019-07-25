/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmattaperdomo.exceptions;

/**
 *
 * @author USER
 */
public class GeneralException extends RuntimeException{

    public GeneralException() {
    }
    
    public GeneralException(String message) {
        super(message);
    }
    
}
