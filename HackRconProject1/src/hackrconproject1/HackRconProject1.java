/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackrconproject1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Mateusz
 */
public class HackRconProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip = args[0];
        int port = 25001;
        System.out.println(sendStatus(ip, port));
        
    }
    
    static public String sendStatus(String ip, int port) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = "\\status\\".getBytes();
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
        } catch (Exception ex) {
            System.out.println(HackRconProject1.class.getName() +" "+ip+" "+ ex);
        }
        return message;
    }
    
}
