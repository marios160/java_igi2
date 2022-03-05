/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serwerudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Mateusz
 */
public class SerwerUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        client();
        /*
       try {
            
            DatagramSocket socket = new DatagramSocket(4321);
            while(true)
            {
                byte[] buf = new byte[100];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive( packet );
                System.out.println(new String(buf));

                socket.send(new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort()));
            }
        } catch (SocketException ex) {
           
        } catch (IOException ex) {
            
        }*/
    }
    
    static void client()
    {
        try{
/* 1. Wysłanie pakietu ... */
InetAddress servAddr =
InetAddress.getByName("192.168.0.105");
byte buf[] = "Mautorun rcon".getBytes();
DatagramSocket socket= new DatagramSocket();
socket.send(new DatagramPacket(buf,buf.length,servAddr,4321 ));
/* 2. Odbiór pakietu ... */
buf = new byte[256];
DatagramPacket packet =new DatagramPacket(buf,buf.length);
socket.receive(packet);
System.out.println(packet.getPort()+new String(packet.getData()));
}
catch(Exception e)
{
System.err.println(e);
}
    }
    
}
