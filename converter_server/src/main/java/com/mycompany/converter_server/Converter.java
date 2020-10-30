/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter_server;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bebhin
 */
    /*
    Rates
    1 EURO  =   1.10 USD
    1 EURO  =   0.84 GBP
    1 USD   =   0.89 EURO
    1 USD   =   6.94 CNY
    */

public class Converter {
    private double amount;
    private String currency;
    private String convertTo;
    private String convertedInfo;
    
    private List<String> clientInput;
    
    public Converter(){
        //this.convertedInfo = convertedInfo;
    }
    
    public Converter(List<String> clientInput){
        this.clientInput = clientInput;
    }

    public void setConversion(List<String> clientInput) {
        this.clientInput = clientInput;
//        System.out.println("Test 1 from Converter: "+clientInput);//for testing
//        System.out.println("Test 2 from Converter: " +clientInput.get(0));
    }
    
    public void computeConversion (){
        double converted = 0;
        String amount = clientInput.get(0);
        int intAmount = (parseInt(amount));
        currency = clientInput.get(1);
        convertTo = clientInput.get(2);
//        System.out.println("Test 6 from Converter: "+ intAmount);
//        System.out.println("Test 7 from Converter: " +(intAmount+intAmount));

        try{
            
           if(currency.equalsIgnoreCase("EURO") && convertTo.equals("USD")){
                converted = intAmount*1.1;
            }

            //EURO TO GBP
            // 1 EURO  =   0.84 GBP
            if(currency.equalsIgnoreCase("EURO") && convertTo.equals("GBP")){
                converted = intAmount*0.84;
            }

            //USD TO EURO
            // 1 USD   =   0.89 EURO
            if(currency.equalsIgnoreCase("USD") && convertTo.equals("EURO")){
                converted = intAmount*0.89;
            }

            //USD TO CNY
            // 1 USD   =   6.94 CNY
            if(currency.equalsIgnoreCase("USD") && convertTo.equals("CNY")){
                converted = intAmount*6.94;
            }
//            if (!currency.equalsIgnoreCase("USD") ||
//               !currency.equalsIgnoreCase("EURO")){
//                throw new InputException();
//            }//need to work on this
            
            convertedInfo =  amount +" "+currency+ " = " +converted+ " " +convertTo;

//      }  catch (InputException e){
//            System.out.println(e.getInputExceptionMessage());
//            //try catch is working but response is still printing
        } catch (NullPointerException e){
            System.out.println("Null POinter Exception thrown");
        }// end of catch
            
    }//end of computeConversion
    
    public String getConversion(){
        System.out.println("Printing converted Info " +convertedInfo);
        return convertedInfo;
    }//end of get
}//end of class
