package com.mycompany.converter_server;

import java.util.List;

public class Converter {
    private double amount;
    private String currency;
    private String convertTo;
    private String convertedInfo;
    
    private List<String> clientInput;
    
    public Converter(){
        
    }
    
    public Converter(List<String> clientInput){
        this.clientInput = clientInput;
    }

    public void setConversion(List<String> clientInput) {
        this.clientInput = clientInput;
//        System.out.println("Test 1 from Converter: "+clientInput);//for testing
    }
    
    public void computeConversion  (){
        double converted = 0;
        String amount = clientInput.get(0);
//        System.out.println("Test 2 from Converter: " +clientInput.get(0));
        double numAmount = Double.parseDouble(amount);
        currency = clientInput.get(1);
        convertTo = clientInput.get(2);
//        System.out.println("Test 6 from Converter: "+ numAmount);
//        System.out.println("Test 7 from Converter: " +(numAmount+numAmount));

        try{
           //EURO TO USD
           // 1 EURO  =   1.10 USD
           if(currency.equalsIgnoreCase("EURO") && convertTo.equals("USD")){
                converted = numAmount*1.1;
            }

            //EURO TO GBP
            // 1 EURO  =   0.84 GBP
            if(currency.equalsIgnoreCase("EURO") && convertTo.equals("GBP")){
                converted = numAmount*0.84;
            }

            //USD TO EURO
            // 1 USD   =   0.89 EURO
            if(currency.equalsIgnoreCase("USD") && convertTo.equals("EURO")){
                converted = numAmount*0.89;
            }

            //USD TO CNY
            // 1 USD   =   6.94 CNY
            if(currency.equalsIgnoreCase("USD") && convertTo.equals("CNY")){
                converted = numAmount*6.94;
            }
            
            if (converted == 0){
                throw new RatesException();
            }
            
            convertedInfo =  amount +" "+currency+ " = " +converted+ " " +convertTo;
            
        } catch (NullPointerException e){
            System.out.println("A Null Pointer Exception was thrown. There is no information for the convertor to convert.");
        }
          catch (RatesException e){
              convertedInfo = (e.getRatesExceptionMessage());
        }
            
    }//end of computeConversion
    
    public String getConversion(){
//        System.out.println("Printing converted Info " +convertedInfo);
        return convertedInfo;
    }//end of get
}//end of class
