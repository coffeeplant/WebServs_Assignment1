/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter_server;

/**
 *
 * @author Bebhin
 */
public class InputException extends Exception {
    
    protected String msg;
    
    public InputException(){
        this.msg="Conversion rates not available!";
    }
    
    public InputException (String msg){
        super (msg);
        this.msg=msg;
    }
    
    public String getInputExceptionMessage(){
        return this.msg;
    }
    
}


