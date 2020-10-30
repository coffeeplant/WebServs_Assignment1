/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter_server;

import java.io.*;
import java.net.*;
//import java.util.StringTokenizer;

/**
 *
 * @author Bebhin
 */
public class ConverterServer {
    private static final int PORT = 1234;
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    
    

    
    public static void main(String[] args){
    
    //declare variables
    double amount;
    String currency;
    String convertTo;
    String convertedInfo;
    double Euro;
    double USD;
    double GBP;
    double CNY;

        
    //opening port
    System.out.println("Opening port...\n");
    try{
        dgramSocket = new DatagramSocket(PORT);//Step1
    } catch(SocketException e){
        System.out.println("Unable to bind to port!");
        System.exit(1);
    }
    run();
       

    }//end of main
    
        //send and receive msg
private static void run(){
    try{
        String messageIn,messageOut;
        int numMessages = 0;
        String answer;
        Converter calc = new Converter();
        
 //       do{//won't need the do while for a once off
            buffer = new byte[256]; //Step 2
            inPacket = new DatagramPacket(buffer, buffer.length); //Step 3
            dgramSocket.receive(inPacket);//STep 4
            InetAddress clientAddress = inPacket.getAddress();//Step 5
            int clientPort = inPacket.getPort();//Step 5
            messageIn = new String(inPacket.getData(), 0, inPacket.getLength()); //Step 6
            
            //convert string to String[] using split
            
            String[] result = messageIn.split("\\s");
                for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
                }
            //***need to cast amount variable to int 
            calc.setConversion(100, "Bleh", "USD");
            calc.computeConversion();
            answer=calc.getConversion();
            System.out.println(answer);
            
            messageOut= "Test message out";
            //  This class is discouraged in new code - using split method of String instead   
//          StringTokenizer st = new StringTokenizer("this is a test");
//    
//          while (st.hasMoreTokens()){
//            System.out.println(st.nextToken());
//          }
            
            //call Converter method here to convert compute response
                //String input = "10 Euro USD"
            String test = "foobar";
            System.out.println("Message received");
            numMessages++;
            messageOut = ("Test message out "+test+ " and showing msg received: " + messageIn);
            outPacket = new DatagramPacket(
                messageOut.getBytes(),
                messageOut.length(),
                clientAddress,
                clientPort);//Step 7
            dgramSocket.send(outPacket); //Step 8
            
//        } while(true);
    } catch(IOException e){
        e.printStackTrace();
        //Try to find an alternative response to this error, look into IOExceptions
    } finally { //If exception thrown, close connection.
        System.out.println("\n* Closing connection... *");
        dgramSocket.close(); //Step 9
    }




    //TESTING INUT CODE -- 

    

}

}
