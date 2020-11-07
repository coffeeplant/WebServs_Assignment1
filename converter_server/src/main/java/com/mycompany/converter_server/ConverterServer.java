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

    //opening port
    System.out.println("Opening port...\n");
    try{
        dgramSocket = new DatagramSocket(PORT);
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
            String[] sp;
            List<String> clientInput= new ArrayList();
            Converter conv = new Converter();
//          *****NEEDS COMMENTING
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length); 
            dgramSocket.receive(inPacket);
            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();
            messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
            
            //convert string to String[] using split
//          String messageIn = new String("10 USD CNY");  //for testing 
            sp = messageIn.split("\\s");//regular expression for whitespace/comma as delimiter
            
            //convert split[] to ArrayList
            clientInput = Arrays.asList(sp);
//                for (int i = 0; i < clientInput.size(); i++) {
//                System.out.println("CS List Test 1: "+clientInput.get(i));
//                }//for testing
            //sending List to Convertor and computing
            conv.setConversion(clientInput);
            conv.computeConversion();
            String answer = conv.getConversion();
//          System.out.println("Printing answer " +answer);//for testing
//          String input = "10 Euro USD" //for testing
                
            //sending response back to client
//          String test = "foobar";//for testing
            
 //           messageOut = ("Test message out "+answer+ " and showing msg received: " + messageIn);
            messageOut = ("You made this request: " +messageIn+ "\n According to our rates: " +answer);
            //***NEEDS COMMENTING
            outPacket = new DatagramPacket(
                messageOut.getBytes(),
                messageOut.length(),
                clientAddress,
                clientPort);
            dgramSocket.send(outPacket);
            } catch(IOException e){
                System.out.println("IO Exception in Server");
//          *****Try to find an alternative response to this error, look into IOExceptions
            } finally { 
                System.out.println("\n* Closing connection... *");
                dgramSocket.close(); 
            }

    }//end of run

}//end of class


