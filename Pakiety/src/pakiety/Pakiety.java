/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakiety;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class Pakiety {

    static String ip;
    static int port;
    
    public static void main(String[] args) {
        ip = "192.166.218.79";
        port = 26001;
        //System.out.println(sendStatus());
        System.out.println(sendStatus());
        sendPck("0000000000000000");
    }
    
    public static String sendStatus()
    {
       String message = "";
      try{
        InetAddress servAddr = InetAddress.getByName(ip);
        byte buf[] = "".getBytes();
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(2000);

        socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
        buf = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);  //odbieramy komende
        socket.close();
        message = new String(packet.getData());
        message = message.trim();
        //message = message.substring(22,message.length()-4);
        
     }catch(Exception ex)
        {
               System.out.print(".");     
        }
      return message;
    }
    
    static public byte[] sendPck(String str)
    {
       byte[] msg = DatatypeConverter.parseHexBinary(str);
       DatagramPacket packet = null;
      try{
        InetAddress servAddr = InetAddress.getByName(ip);
        byte buf[] = msg;
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(2000);
        
        
        
        socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
        buf = new byte[4096];
        
           packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);  //odbieramy komende
        socket.close();
        
     }catch(Exception ex)
        {
            System.out.println("pakiet "+ex );   
        }
        
      return packet.getData();
    }
    
}
