/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter_client;

/**
 *
 * @author Bebhin
 */
public class UserInputException extends Exception {
    
    protected String msg;
    
    public UserInputException(){
        this.msg="This should be a numerical amount!";
    }
    
    public UserInputException (String msg){
        super (msg);
        this.msg=msg;
    }
    
    public String getUserInputException(){
        return this.msg;
    }
    
}