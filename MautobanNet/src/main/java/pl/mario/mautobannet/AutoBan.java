package pl.mario.mautobannet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static pl.mario.mautobannet.Strings.*;

/**
 *
 * @author Mario PL
 */
public class AutoBan {

    static Queue<Ban> bans = new LinkedList<>();
    static List<String> banList;
    static String sys;
    static Console cns;
    static String linia = "";
    static long dl, olddl = 0;
    static long poz = 0;
    static int l = 0;
    static int spr = 0, sysi = 0;
    static long times;
    static Player[] players = {null, null, null, null, null};
    static int port;
    static String ip;
    static String version = "";
    static Sniffer snifer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        cns = new Console();
        Console.setmessage(odkoduj(astarting));
        sys = System.getProperty("os.name").toLowerCase().substring(0, 3);
        if (sys.contains("win")) {
            sysi = 1;
        } else if (sys.contains("lin")) {
            sysi = 2;
        } else {
            JOptionPane.showMessageDialog(null, "System not recognized!\nReport to Mario PL");
            System.exit(0);
        }
        File f = new File(odkoduj(vqdll));
        if (!f.exists()) {
            JOptionPane.showMessageDialog(null, "Place MautoBan in IGI2 PC!");
            System.exit(0);
        }
        ip = htmlReader.pobierzIP();
        //Sprawdz s = new Sprawdz();
        //s.start();
        getNetworkF();
        getBanlist();
        new StartSniffer().start();
        new Ban().start();
//        Thread.sleep(10000);
//        PacketCounter p = new PacketCounter();
//        p.start();
        Console.setmessage(odkoduj(detect));
//        while (true) {
//            Thread.sleep(1000 * 60);
//            snifer.count = 0;
//        }

    }

    public static void getBanlist() {
        try {
            banList = new ArrayList<>();
            String sub = "";
            switch (AutoBan.sysi) {
                case 1:
                    sub = "remoteip=";
                    break;
                case 2:
                    sub = "INPUT -s ";
                    break;
                default:
                    return;
            }
            File f = new File("bany.txt");
            if (!f.exists()) {
                return;
            }
            Scanner file = new Scanner(f);
            while (file.hasNextLine()) {
                String linia = file.nextLine();
                if (linia.indexOf(sub) >= 0) {
                    String ip = linia.substring(linia.indexOf(sub)+9, linia.indexOf("/32"));
                    banList.add(ip);
                }
            }
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AutoBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public void getNetworkF() {
        File file = new File("networkconfig.cfg");
        if (file.exists()) {
            BufferedReader bufferedReader = null;

            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Loggs.loguj("Conf-pobierznetw", ex);
            }

            String linia = null;
            do {

                try {
                    linia = bufferedReader.readLine();
                } catch (IOException ex) {
                    Loggs.loguj("Conf-pobierznetw", ex);
                }

//                if (linia.indexOf(ServerCommands.rconpass) > 0) {
//                    this.rcon = linia.substring(13, linia.indexOf("\");"));
//                } else 
                if (linia.indexOf("svport") > 0) {
                    port = Integer.parseInt(linia.substring(10));
                    break;
                }

            } while (linia != null);

            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Loggs.loguj("Conf-pobierznetw", ex);
            }
        }
    }

    public static String odkoduj(String txt) {
        char[] tab = txt.toCharArray();
        char[] tab2 = new char[txt.length() / 3];
        for (int i = 0, j = txt.length() - 1; i < tab2.length; i++, j -= 3) {
            String l = tab[j] + "" + tab[j - 1] + "" + tab[j - 2];
            int x = Integer.parseInt(l);
            x = (int) Math.ceil(x / 5.0);
            x = x - 70;
            tab2[i] = (char) x;
        }
        return new String(tab2);
    }

    public synchronized void ban(String ip, int method) {
        String cmd = "";
        boolean var = false;
        try {
            synchronized (banList) {
                for (String el : banList) {
                    if (el.equals(ip)) {
                        var = true;
                        break;
                    }
                }
            }
            if (var) {
                switch (AutoBan.sysi) {
                    case 1:
                        cmd = "netsh advfirewall firewall delete rule name=all remoteip=" + ip;
                        Runtime.getRuntime().exec(cmd);
                        cmd = "netsh advfirewall firewall add rule name=\\\"IGIBan\\\" "
                                + "dir=in protocol=udp interface=any action=block remoteip=" + ip + "/32";
                        Runtime.getRuntime().exec(cmd);
                        break;
                    case 2:
                        cmd = "iptables -D INPUT -s " + ip + "/32 -j DROP";
                        Runtime.getRuntime().exec(cmd);
                        break;
                    default:
                        return;
                }
            } else {
                switch (AutoBan.sysi) {
                    case 1:
                        cmd = "netsh advfirewall firewall add rule name=\\\"IGIBan\\\" "
                                + "dir=in protocol=udp interface=any action=block remoteip=" + ip + "/32";
                        break;
                    case 2:
                        
                        cmd = "iptables -I INPUT -s " + ip + "/32 -j DROP";
                        break;
                    default:
                        return;
                }
                String ms = "";
                switch (method) {
                    case 1:
                        ms = "Bugs port";
                        break;
                    case 2:
                        ms = "fs";
                        break;
                    case 3:
                        ms = "0 byte";
                        break;
                    case 4:
                        ms = "DDos";
                        break;
                    case 5:
                        ms = "Bugs time";
                        break;
                }
                Console.setmessage(odkoduj(fcrasher) + "(" + ms + ")" + ip);

                FileWriter file = new FileWriter(odkoduj(bany), true);
                PrintWriter plik = new PrintWriter(file);
                plik.append(cmd + "       #" + time() + odkoduj(powod) + "\n");
                plik.close();
            }

        } catch (OutOfMemoryError ex) {
            Loggs.loguj("AutoBan-Ban", ex);
        } catch (IOException ex) {
            Logger.getLogger(AutoBan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String time() {
        SimpleDateFormat czas = new SimpleDateFormat(odkoduj(data));
        return czas.format(new Date()) + " ";
    }

    public static long timem() {
        return System.currentTimeMillis();
    }

}
