package fakeserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 * Klasa wysyłająca komendy na serwer
 *
 * @author Mario PL
 */
public class Pakiety1 extends Thread {

    int port;
    static String nazwa, mapa, ilgraczy, maxgraczy, wersja, 
            uptime = "1", 
            timeleft = "14:59";
    static long iuptime = 1;
    static long itimeleft = 899;
    static long time = System.currentTimeMillis();
    static long time2 = 1480792443;
                        
    static DatagramSocket socket;
    String info = "deadbeefabababababababab0000000005abababbadeabee";
    String odpInfo = "deadbeefabababababababab3b8d016e06ababab6c01556e637261736861626c6520536572766572206279204d617"
            + "2696f20504c00000000000000000000000000000000000000000000000000000000000000000000312e320000000000000000"
            + "000300000000000c01000000ffffffffffffffff00fffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
            + "fffff000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
            + "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
            + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
            + "00000000000000000000000ffff00000000003bc40d000f000000000000000000000000000000ffffffff190f0000000000000"
            + "000000001000000010000000b5a00002c01000000000000000000000700000001000000badeabee";
    String status = "5c7374617475735c";
    String join = "deadbeefabababababababab0003001400ababab0300000000000000000000000000000000000000badeabee";
    String odpJoin = "deadbeefabababababababab02aa000c01ababab00ababab3f69000001000000badeabee";
    String spawn = "ffffffff57535152";
    String odpSpawn = "deadbeef07000000020000000791001c03ababab00000000ffffffff4e57505354480200020000003900000001f73745badeabee";

    public Pakiety1(String nazwa, String mapa, String ilgraczy, String maxgraczy, String wersja) {
        this.nazwa = nazwa;
        this.mapa = mapa;
        this.ilgraczy = ilgraczy;
        this.maxgraczy = maxgraczy;
        this.wersja = wersja;
        info = new String(DatatypeConverter.parseHexBinary(info));
        status = new String(DatatypeConverter.parseHexBinary(status));
        join = new String(DatatypeConverter.parseHexBinary(join));
        spawn = new String(DatatypeConverter.parseHexBinary(spawn));
    }

    /**
     * Wysyła komende na serwer. przed wysłaniem komendy jest zawsze wysyłany
     * rcon
     *
     * @param msg treść komendy
     * @return odpowiedz od serwera
     */
    public void sendPck() {
        try {
            InetAddress servAddr = InetAddress.getByName("65.112.87.186");
            byte buf[] = "\\heartbeat\\0\\gamename\\projectigi2r".getBytes();
            DatagramSocket socket = new DatagramSocket(26001);
            socket.setSoTimeout(3000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, 27900));              //nastepnie komende
            this.port = socket.getLocalPort();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Pakiety1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metoda wysyła \\status\\ w celu otrzymania informacji na temat serwera
     *
     * @return tresc statusu
     */
    public void run() {
        System.out.println(System.currentTimeMillis());
        this.sendPck();
        System.out.println(this.port);
        try {

            DatagramSocket socket = new DatagramSocket(port);
            byte[] odp = null;
            uptime = "1";

            byte[] buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            while (true) {
                /* 1. Oczekujemy na pakiet ...*/
                socket.receive(packet);
                times();
                int length = packet.getLength() - 42;
                byte[] byteData = packet.getData();
                String data = new String(byteData);
                
                if (data.contains(status)){
                    odp = Status();
                } else if (data.contains(info)){
                    odp = Info();
                } else if (data.contains(join)){
                    odp = Join();
                } else if (data.contains(spawn)){
                    odp = Spawn();
                    
//                } else if (data.contains(info)){
//                } else if (data.contains(info)){
//                } else if (data.contains(info)){
//                } else if (data.contains(info)){
//                } else if (data.contains(info)){
//                    
                } else {
                    odp = "".getBytes();
                }
                    
                
                /* 2. Odsyłamy do nadawcy ... */
                socket.send(new DatagramPacket(odp, odp.length, packet.getAddress(), packet.getPort()));
            }
        } catch (SocketException ex) {
            Logger.getLogger(Pakiety1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pakiety1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public byte[] Status(){
        return new String("\\gamename\\projectigi2r\\gamever\\" + wersja + "\\location\\0\\hostname\\"
                        + nazwa + "\\hostport\\" + port + "\\mapname\\" + mapa + "\\gametype\\Team match\\numplayers\\"
                        + ilgraczy + "\\maxplayers\\" + maxgraczy + "\\gamemode\\openplaying\\bwidth\\0\\maxout\\300\\"
                        + "uptime\\" + uptime + "\\timeleft\\" + timeleft + "\\mapstat\\    \\timelimit\\0\\autobalance\\0\\teamdamage\\0\\"
                        + "sniper\\1\\bombrepos\\0\\specmode\\1\\pingmax\\400\\plossmax\\15.0\\bwidth\\0\\maxout"
                        + "\\300\\dedicated\\1\\password\\1\\team_t0\\IGI\\team_t1\\CON\\score_t0\\0\\score_t1\\0\\"
                        + "final\\\\queryid\\5.1").getBytes();
    }
    
    public byte[] Info(){
        return DatatypeConverter.parseHexBinary(odpInfo);
    }
    
    public byte[] Join(){
        return DatatypeConverter.parseHexBinary(odpJoin);
    }
    
    public byte[] Spawn(){
        return DatatypeConverter.parseHexBinary(odpSpawn);
    }
    
    
    public static void times() {
        long ttime = (System.currentTimeMillis() - time) / 1000;
        time = System.currentTimeMillis();
        iuptime = ((time / 1000) - time2) / 60;
        itimeleft = itimeleft - ttime;
        if (itimeleft < 0) {
            itimeleft = 899;
        }
        uptime = Long.toString(iuptime);
        //timeleft = Long.toString(itimeleft / 60) + ":";
        if (Long.toString(itimeleft / 60).length() == 1) {
            timeleft = "0" + Long.toString(itimeleft / 60) + ":";
        } else {
            timeleft = Long.toString(itimeleft / 60) + ":";
        }
        if (Long.toString(itimeleft % 60).length() == 1) {
            timeleft += "0" + Long.toString(itimeleft % 60);
        } else {
            timeleft += Long.toString(itimeleft % 60);
        }
    }
    
    public String sendStrPck(String msg, String ip, int port) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg.getBytes();
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende
            message = new String(packet.getData());
            message = message.trim();
            //message = message.substring(22,message.length()-4);

        } catch (Exception ex) {
            System.out.print(".");
        }
        return message;
    }

    public String sendStatus(String ip, int port) {
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
            System.out.println(Pakiety1.class.getName() +" "+ip+" "+ ex);
        }
        return message;
    }

    public byte[] sendPck(String str, String ip, int port) {
        byte[] msg = DatatypeConverter.parseHexBinary(str);
        DatagramPacket packet = null;
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg;
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
        }
        return packet.getData();
    }

    public String sendHexPck(byte[] msg, String ip, int port) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg;
            socket.setSoTimeout(2000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            byte[] buff = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);  //odbieramy komende

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
        }
        return message;
    }

    
}
