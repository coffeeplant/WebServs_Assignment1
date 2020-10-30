package com.mycompany.converter_server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConverterServer {
    private static final int PORT = 1234;
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    
    public static void main(String[] args){
    
    //declare variables
//    double amount;
//    String currency;
//    String convertTo;
//    String convertedInfo;
//    double Euro;
//    double USD;
//    double GBP;
//    double CNY;

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

//        
//// //       do{//won't need the do while for a once off
            buffer = new byte[256]; //Step 2
            inPacket = new DatagramPacket(buffer, buffer.length); 
            dgramSocket.receive(inPacket);
            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();
            messageIn = new String(inPacket.getData(), 0, inPacket.getLength()); //Step 6
            
//            String messageIn = new String("10 USD CNY");  //for testing 
            //convert string to String[] using split
            String sp[]= messageIn.split("\\s");//regular expression for whitespace/comma as delimiter
            //convert split[] to List
            List<String> clientInput= new ArrayList();
            clientInput = Arrays.asList(sp);
//                for (int i = 0; i < clientInput.size(); i++) {
//                System.out.println("CS List Test 1: "+clientInput.get(i));
//                }//for testing
            //sending List to Convertor
            Converter conv = new Converter();
            conv.setConversion(clientInput);
            conv.computeConversion();
            String answer = conv.getConversion();
//            System.out.println("Printing answer " +answer);//for testing
//            **this is breaking the code bc not set-up in Convertor class

//            answer=calc.getConversion();
//            System.out.println(answer);
            
            //  This class is discouraged in new code - using split method of String instead   
//          StringTokenizer st = new StringTokenizer("this is a test");
//          while (st.hasMoreTokens()){
//            System.out.println(st.nextToken());
//          }
            
            //String input = "10 Euro USD" //for testing
                
//          sending response back to client
//            String test = "foobar";//for testing
            System.out.println("Message received");
            numMessages++;
            messageOut = ("Test message out "+answer+ " and showing msg received: " + messageIn);
            outPacket = new DatagramPacket(
                messageOut.getBytes(),
                messageOut.length(),
                clientAddress,
                clientPort);//Step 7
            dgramSocket.send(outPacket); //Step 8
////            
////////        } while(true);
            } catch(IOException e){
                e.printStackTrace();
//        //Try to find an alternative response to this error, look into IOExceptions
            } finally { //If exception thrown, close connection.
                System.out.println("\n* Closing connection... *");
                dgramSocket.close(); //Step 9
            }

}//end of run()

}//end of class


