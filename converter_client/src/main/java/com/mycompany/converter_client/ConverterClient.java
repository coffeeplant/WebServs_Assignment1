/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter_client;

import java.io.*;
import java.net.*;

public class ConverterClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    
    public static void main (String[] args){
        try {
            host = InetAddress.getLocalHost();
        } catch(UnknownHostException e){
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        run();
    } 
    
private static void run(){
    String amount;
    String currency;
    String convertTo;

    try{
        dgramSocket = new DatagramSocket();
        //Set up stream for user entry
        BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
        String message;
        String response = null;
        do{
            do{
            //user message input**********
                System.out.println("Enter amount to be converted: ");
                amount = userEntry.readLine();
                try{
                    double number = Double.parseDouble(amount);
                    }
                     catch (NumberFormatException e){
                         System.out.println("The amount needs to be numeric, please try again");
                    }
            }while(amount.equals(null));
            
            System.out.println("Enter currency to be converted: ");
            currency = userEntry.readLine();
            System.out.println("What currency to convert to? ");
            convertTo = userEntry.readLine();
        } while (amount.equals(null) && currency.equals(null) && convertTo.equals(null));
//          System.out.println(message); for testing 
//System.out.println(message);
            message = amount+" "+currency+" "+convertTo;
 
    //while{message.equals(null)};
        //sending user msg and receiving response
            outPacket = new DatagramPacket(
                message.getBytes(),
                message.length(),
                host,
                PORT); 
            dgramSocket.send(outPacket);
            buffer = new byte[256]; 
            inPacket = new DatagramPacket(
                buffer,
                buffer.length); 
            dgramSocket.receive(inPacket); 
            response = new String(
                inPacket.getData(),
                0,
                inPacket.getLength()); 
            System.out.println("\nSERVER> " +response);      

    }  catch(BindException e){
        System.out.println("Address already in use");
        System.out.println("Make sure the server and client programs have been stopped in your IDE"); 
    } catch(IOException e){
        System.out.println("Unspecified IO Exception in Client, see StackTrace");
        e.printStackTrace();
    }
      finally {
        System.out.println("\n* Closing connection... *");
        dgramSocket.close();
    }
}
}
