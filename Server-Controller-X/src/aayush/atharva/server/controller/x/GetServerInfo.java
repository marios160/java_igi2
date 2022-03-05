package aayush.atharva.server.controller.x;

import static aayush.atharva.server.controller.x.Main.ServerIP;
import static aayush.atharva.server.controller.x.Main.ServerPort;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hyper
 */
public class GetServerInfo extends Thread {

    public static DatagramSocket socket;

    public static String name;
    public static String map;
    public static int numpl;
    public static int maxpl;
    public static String uptime;
    public static String time;
    public static String mapstat;
    public static String password;

    // Server Data
    public static String ServerName;
    public static String ServerMap;
    public static String Players;
    public static String ServerUptime;
    public static String ServerMapTime;
    public static String ServerMapStat;
    public static String ServerPassword;

    List<Player> players;

    public boolean setServer(String status) {
        if (status.length() <= 0) {
            return false;
        }
        try {
            String pom = status.substring(status.indexOf("hostname\\") + 9, status.indexOf("\\hostport"));
            this.name = pom;
            pom = status.substring(status.indexOf("mapname\\") + 8, status.indexOf("\\gametype"));
            this.map = pom;
            pom = status.substring(status.indexOf("numplayers\\") + 11, status.indexOf("\\maxplayers"));
            this.numpl = Integer.parseInt(pom);
            pom = status.substring(status.indexOf("maxplayers\\") + 11, status.indexOf("\\gamemode"));
            this.maxpl = Integer.parseInt(pom);
            pom = status.substring(status.indexOf("uptime\\") + 7, status.indexOf("\\timeleft"));

            int up = Integer.parseInt(pom);
            this.uptime = (up / 60 + "h " + up % 60 + " min");
            pom = status.substring(status.indexOf("timeleft\\") + 9, status.indexOf("\\mapstat"));
            this.time = pom;
            pom = status.substring(status.indexOf("mapstat\\") + 8, status.indexOf("\\timelimit"));
            this.mapstat = pom.replaceFirst("roundlimit_", "");
            pom = status.substring(status.indexOf("password\\") + 9, status.indexOf("\\team_t0"));
            if (pom.equals("1")) {
                this.password = "Yes";
            } else {
                this.password = "No";
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Info-updateInfo: " + ex.toString() + status);
        }

        return true;
    }

    public static String sendStatus() {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ServerIP);
            byte[] buf = "\\status\\".getBytes();
            socket = new DatagramSocket();
            socket.setSoTimeout(1000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, ServerPort));
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            socket.close();
            message = new String(packet.getData());
        } catch (Exception ex) {
            return "";
        }
        return message.trim();
    }

    public List<Player> getPlayers() {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ServerIP);
            byte[] buf = "\\players\\".getBytes();
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(1000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, ServerPort));
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            socket.close();
            message = new String(packet.getData());
            message = message.trim();
        } catch (Exception ex) {
            return null;
        }
        this.players = new ArrayList();
        setNewPlayer(message);
        return this.players;
    }

    public void setNewPlayer(String status) {
        while (status.lastIndexOf("player") > 0) {
            String sfrag = "frags_";
            String sdeath = "deaths_";
            String sping = "ping_";
            String steam = "team_";
            String splayer = "player_";
            if (status.indexOf(splayer) < 0) {
                return;
            }
            String id = status.substring(status.indexOf(splayer) + splayer.length(), status.indexOf("\\", status.indexOf(splayer) + splayer.length()));
            splayer = splayer + id + "\\";
            sfrag = sfrag + id + "\\";
            sdeath = sdeath + id + "\\";
            sping = sping + id + "\\";
            steam = steam + id + "\\";
            String pom = status.substring(status.indexOf(splayer) + splayer.length(), status.indexOf(sfrag) - 1);
            String nick = pom;
            pom = status.substring(status.indexOf(sfrag) + sfrag.length(), status.indexOf(sdeath) - 1);
            String stats = pom;
            pom = status.substring(status.indexOf(sdeath) + sdeath.length(), status.indexOf(sping) - 1);
            stats = stats + "/" + pom;
            pom = status.substring(status.indexOf(sping) + sping.length(), status.indexOf(steam) - 1);
            String ping = pom;
            pom = status.substring(status.indexOf(steam) + steam.length(), status.indexOf(steam) + steam.length() + 1);
            String team = pom;
            Player p = new Player(id, nick, stats, ping, team);
            status = status.substring(status.indexOf(splayer) + splayer.length() + 2);
            this.players.add(p);
        }
    }
}
