package aayush.atharva.server.controller.x;

import javax.swing.JOptionPane;

/**
 *
 * @author Aayush Atharva
 * 
 */
public class Main {

    public static String ServerIP;
    public static int ServerPort;

    public static void main(String[] args) throws InterruptedException {

        ServerIP = "139.59.41.114";//JOptionPane.showInputDialog(null, "Enter Server IP", "Server Console X", 1);

        ServerPort = 9110;//Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Server Port", "Server Console X", 1));

        GUI_Main.main(args);

    }

    // (id, nick, stats, ping, team)
    
    public static void echo(String msg) {
        System.out.println(msg);
    }

    public static void error(String msg) {
        System.err.println(msg);
    }

    public static void echo(int msg) {
        System.out.println(msg);
    }

}
