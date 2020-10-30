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
public class Converter {
    private double amount;
    private String currency;
    private String convertTo;
    private String convertedInfo;
    private double Euro;
    private double USD;
    private double GBP;
    private double CNY;
    
    /*
    Rates
    1 EURO  =   1.10 USD
    1 EURO  =   0.84 GBP
    1 USD   =   0.89 EURO
    1 USD   =   6.94 CNY
    
    */
    
    Converter(){
        this.convertedInfo = convertedInfo;
    }
    
    Converter(int amount, String currency, String convertTo){
        this.amount = amount;
        this.currency = currency;
        this.convertTo = convertTo;
    }
    
    public void setConversion (int amount, String currency, String convertTo){
        this.amount = amount;
        this.currency = currency;
        this.convertTo = convertTo;
    }
    
    public void computeConversion (){
        double converted = 0;
        InputException inexp = new InputException();
    //THIS WILL NEED TRY CATCH FOR INPUT OUT OF BOUNDS    
        //EURO to USD
        // 1 EURO  =   1.10 USD
        try{
            
            if (!currency.equalsIgnoreCase("USD") ||
               !currency.equalsIgnoreCase("EURO")){
                throw new InputException();
            }
            
           if(currency.equalsIgnoreCase("EURO") && convertTo.equals("USD")){
                converted = amount*1.1;
            }

            //EURO TO GBP
            // 1 EURO  =   0.84 GBP
            if(currency.equalsIgnoreCase("EURO") && convertTo.equals("GBP")){
                converted = amount*0.84;
            }

            //USD TO EURO
            // 1 USD   =   0.89 EURO
            if(currency.equalsIgnoreCase("USD") && convertTo.equals("EURO")){
                converted = amount*0.89;
            }

            //USD TO CNY
            // 1 USD   =   6.94 CNY
            if(currency.equalsIgnoreCase("USD") && convertTo.equals("CNY")){
                converted = amount*6.94;
            }


        } catch (InputException e){
            System.out.println(inexp.getInputExceptionMessage());
            //try catch is working but response is still printing
        }
        
            convertedInfo =  amount +" "+currency+ " = " +converted+ " " +convertTo;
    }
    
    public String getConversion(){
        return convertedInfo;
    }
}
