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
            //look up exception and try to get better way to deal wtih it
        }
        run();
    } 
    
private static void run(){
    String amount;
    String currency;
    String convertTo;
    double Euro;
    double USD;
    double GBP;
    double CNY;

    try{
        dgramSocket = new DatagramSocket(); //Step 1
        //Set up stream for keyboard entry
        BufferedReader userEntry = new BufferedReader(
            new InputStreamReader(System.in));
        String message = null;
        String response = null;
//        do{//do while not used for once off
        //user message input**********
            System.out.println("Enter amount to be converted: ");
            amount = userEntry.readLine();
            System.out.println("Enter currency to be converted: ");
            currency = userEntry.readLine();
            System.out.println("What currency to convert to? ");
            convertTo = userEntry.readLine();
            
            message = amount+" "+currency+" "+convertTo;
            System.out.println(message);
            
//            if (!message.equals("***CLOSE***")){//removing this bc close not rqstd

        //sending user msg and receiving response
            outPacket = new DatagramPacket(
                message.getBytes(),
                message.length(),
                host,
                PORT); //STEP 2
            dgramSocket.send(outPacket); //Step 3
            buffer = new byte[256]; //Step 4
            inPacket = new DatagramPacket(
                buffer,
                buffer.length); //Step 5
            dgramSocket.receive(inPacket); //Step 6
            response = new String(
                inPacket.getData(),
                0,
                inPacket.getLength()); //Step 7
            System.out.println("\nSERVER> " +response);      
 //           }
 //       } while (!message.equals("***CLOSE***"));
    } catch(IOException e){
        e.printStackTrace();
        //can leave this as exception handling but he receommedns looking into it more
    } finally {
        System.out.println("\n* Closing connection... *");
        dgramSocket.close(); //Step 8
    }
}
}
