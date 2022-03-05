/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewall;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class Firewall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("1");
            ServerSocket s = new ServerSocket(26001);
            System.out.println("2");
            Socket sc = s.accept();
            System.out.println("3");
            System.out.println(sc.getInetAddress());
            System.out.println("4");
            OutputStream out = sc.getOutputStream();
            PrintStream pout = new PrintStream(out);
            InputStream in = sc.getInputStream();
            DataInputStream din = new DataInputStream(in);
            System.out.println("5");


        } catch (IOException ex) {
            Logger.getLogger(Firewall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
