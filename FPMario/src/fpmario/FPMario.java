package fpmario;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class FPMario extends Thread {

    static DatagramSocket socket;
    boolean interrupt = false;
    String ip;
    int port;
    int slots;
    Okno o;

    public FPMario(String ip, int port, int slots, Okno o) {
        this.ip = ip;
        this.port = port;
        this.slots = slots;
        System.out.println(slots);
        this.o = o;
        if (slots < 1 || slots > 32) {
            String status = null;
            while ((status = sendStrPck("\\status\\")) == null);
            String maxpl = status.substring(status.indexOf("maxplayers\\") + 11, status.indexOf("\\gamemode"));
            this.slots = Integer.parseInt(maxpl);
        }
    }

    public void run() {
        try {
            int numplayers = 0;
            while (!interrupt) {
                do {
                    String status = null;
                    while ((status = sendStrPck("\\status\\")) == null) {
                    }
                    String name = status.substring(status.indexOf("hostname\\") + 9, status.indexOf("\\hostport"));
                    String numpl = status.substring(status.indexOf("numplayers\\") + 11, status.indexOf("\\maxplayers"));
                    numplayers = Integer.parseInt(numpl);
                    String maxplayers = status.substring(status.indexOf("maxplayers\\") + 11, status.indexOf("\\gamemode"));
                    this.o.getStatus().setText(name + "   " + numpl + "\\" + maxplayers);
                    Thread.sleep(3000);
                } while (numplayers > this.slots);

                for (int i = 0; i < this.slots - numplayers; i++) {
                    socket = new DatagramSocket();
                    while (sendPck("deadbeefabababababababab0000000005abababbadeabee") == null) {
                    }
                    socket.close();
                }

            }

        } catch (SocketException ex) {
            Logger.getLogger(FPMario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FPMario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String sendStrPck(String msg) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(this.ip);
            byte buf[] = msg.getBytes();
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, this.port));              //nastepnie komende
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende
            socket.close();
            message = new String(packet.getData());
            message = message.trim();
            //message = message.substring(22,message.length()-4);

        } catch (Exception ex) {
            System.out.println("pakietasdasda " + ex);
        }
        return message;
    }

    public byte[] sendPck(String str) {
        byte[] msg = DatatypeConverter.parseHexBinary(str);
        DatagramPacket packet = null;
        try {
            InetAddress servAddr = InetAddress.getByName(this.ip);
            byte buf[] = msg;
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, this.port));              //nastepnie komende
            buf = new byte[4096];

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende

        } catch (Exception ex) {
            //System.out.println("pakiet " + ex);
            return null;
        }
        return packet.getData();
    }

}
