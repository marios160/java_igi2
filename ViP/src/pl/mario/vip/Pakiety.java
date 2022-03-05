
package pl.mario.vip;

import java.io.IOException;
import java.net.*;
import static pl.mario.vip.ViP.*;


/**
 * Klasa wysyłająca komendy na serwer
 * @author Mario PL
 */
public class Pakiety extends Thread{

    public static String sendPck(String msg)
    {
       String message = "";
      try{
        InetAddress servAddr = InetAddress.getByName(ip);
        byte buf[] = msg.getBytes();
        byte buff[];
        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(3000);
        
        socket.send(new DatagramPacket(rcon, rcon.length, servAddr, port)); //wysylamy rcon
        buff= new byte[4096];
        DatagramPacket prcon = new DatagramPacket(buff, buff.length);
        socket.receive(prcon);  //odbieramy komende
        
        socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
        buf = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);  //odbieramy komende
        socket.close();
        
        message = new String(packet.getData());
        message = message.trim();
        message = message.substring(22,message.length()-4);
        
     }catch(Exception ex)
        {
   
        }
      return message;
    }
    
    public static String sendPckAdmin(String msg)
    {
       String message = "";
      try{
        InetAddress servAddr = InetAddress.getByName(ip);
        byte buf[] = msg.getBytes();
        byte buff[];
        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(3000);

        socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
        buf = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);  //odbieramy komende
        socket.close();
        
        message = new String(packet.getData());
        message = message.trim();
        message = message.substring(22,message.length()-4);
        
     }catch(Exception ex)
        {
   
        }
      return message;
    }
    

    public static String sendStatus()
    {
        String message = "";
        try{
        InetAddress servAddr = InetAddress.getByName(ip);

        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(3000);
        socket.send(new DatagramPacket(status, status.length, servAddr, port));              //nastepnie komende

        
        byte buf[] = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);                                             //odbieramy komende
        message = new String(packet.getData());
        socket.close();
        
        }catch(IOException ex)
        {
            //Loggs.loguj("Pakiety-sendStatus", ex.toString()); 
        }
        return message.trim();
    }
    
   

    
   }
