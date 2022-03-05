package mcommander;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class Mcommander extends Thread {

    /**
     * @param args the command line arguments
     */
    static String ip;
    static int port;
    static Okno o;
    static boolean start;
    static String cmd;

    public Mcommander(Okno o) {
        this.o = o;
        start = false;
//        try {
//            String exec = "cmd.exe /c echo HACKED BY MARIO PL >> d:\\a.txt";
//                Process p = Runtime.getRuntime().exec(exec);
//                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
//                String linia;
//            while ((linia = input.readLine()) != null) {
//                System.out.println(linia);
//            }
//               
//            input.close();
//            } catch (IOException ex) {
//                
//            }
        ip = "";
        port = 0;
        String cmd2 = asciiToHex("/crash ");
        //System.out.println(cmd2);
        cmd = "deadbeef"
                + "0100000000000000"
                + "0000"
                + "0000"
                + "03ababab"
                + "01000000ffffffff"
                + "4E52544D"
                + "000000"
                + cmd2
                + "badeabee";

        String message = "";

    }

    public void run() {

        start = true;
        while (start) {
            try {
                String message = "";
                message = new String(sendStrPck("\\status\\"));
                if (!message.isEmpty()) {
                    o.setmessage("Server responsed");
                    System.out.println("Server responsed");
                } else {
                    o.setmessage("Server not responsed");
                    System.out.println("Server not responsed");
                }
                sendPck(cmd);
                //message = message.trim();
                //System.out.println(message);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mcommander.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
    public static void commands(){
        String cmd2 = asciiToHex("/commands ");
        //System.out.println(cmd2);
        String cmd = "deadbeef"
                + "0100000000000000"
                + "0000"
                + "0000"
                + "03ababab"
                + "01000000ffffffff"
                + "4E52544D"
                + "000000"
                + cmd2
                + "badeabee";
        sendPck(cmd);
    }

    public static void stoop() {

        String cmd2 = asciiToHex("/mstop ");
        //System.out.println(cmd2);
        String cmd = "deadbeef"
                + "0100000000000000"
                + "0000"
                + "0000"
                + "03ababab"
                + "01000000ffffffff"
                + "4E52544D"
                + "000000"
                + cmd2
                + "badeabee";

        sendPck(cmd);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mcommander.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message = "";
                message = new String(sendStrPck("\\status\\"));
                if (!message.isEmpty()) {
                    o.setmessage("Server responsed");
                    System.out.println("Server responsed");
                } else {
                    o.setmessage("Server not responsed");
                    System.out.println("Server not responsed");
                }
                //message = message.trim();
        //System.out.println(message);

    }

    private static String asciiToHex(String asciiValue) {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

    public static String sendStrPck(String msg) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(o.getIp());
            byte buf[] = msg.getBytes();
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, Integer.parseInt(o.getPort())));              //nastepnie komende
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende
            socket.close();
            message = new String(packet.getData());
            message = message.trim();
        //message = message.substring(22,message.length()-4);

        } catch (Exception ex) {
            //System.out.println("pakiet "+ex );     
        }
        return message;
    }

    public static byte[] sendPck(String str) {
        byte[] msg = DatatypeConverter.parseHexBinary(str);
        DatagramPacket packet = null;
        try {
            InetAddress servAddr = InetAddress.getByName(o.getIp());
            byte buf[] = msg;
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(5000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, Integer.parseInt(o.getPort())));              //nastepnie komende
            do {
                buf = new byte[4096];

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);  //odbieramy komende
                System.out.println(new String(packet.getData()).trim());
            } while (!(new String(packet.getData()).trim().contains("MEXIT")));
            socket.close();

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
        }
        return packet.getData();
    }

    public String sendHexPck(byte[] msg) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(o.getIp());
            byte buf[] = msg;
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(2000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, Integer.parseInt(o.getPort())));              //nastepnie komende
            byte[] buff = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);  //odbieramy komende
            socket.close();

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
        }
        return message;
    }
}
