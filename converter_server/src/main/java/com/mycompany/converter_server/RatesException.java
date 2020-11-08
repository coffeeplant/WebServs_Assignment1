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
public class RatesException extends Exception {
    
    protected String msg;
    
    public RatesException(){
        this.msg="Sorry, conversion rates are not available for this exchange";
    }
    
    public RatesException (String msg){
        super (msg);
        this.msg=msg;
    }
    
    public String getRatesExceptionMessage(){
        return this.msg;
    }
    
}


