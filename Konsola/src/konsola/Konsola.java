/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konsola;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class Konsola {

    public static List<Server> lista;
    public static List<Server> oldList;
    public static int update;
    static String version = "v3.0";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> ipList;
        Gui g = new Gui();
        boolean var = false;
        Server pom = null;
        oldList = new ArrayList<>();
        while (true) {
            update = 0;
            if (!Web.visitHTML("http://masterserver.igi2masterserver.igi2.co.in/igi2/masterserver/servers.txt")) {
                Web.visitHTML("http://www.mariopl.y0.pl/a.php");
                ipList = Web.readHTML("http://masterserver.igi2masterserver.igi2.co.in/igi2/masterserver/servers.txt");
            } else {
                ipList = Web.readHTML("http://masterserver.igi2masterserver.igi2.co.in/igi2/masterserver/servers.txt");
            }
            lista = new ArrayList<Server>();
            Server s = null;
            int size = Konsola.oldList.size();
            for (int i = 0; i < size; i++) {
                Konsola.oldList.get(i).crash = (boolean) Gui.jTable1.getValueAt(i, 0);
            }
            Gui.disableTable();
            Gui.disableButtons();
            for (String ips : ipList) {
                var = false;
                if (!oldList.isEmpty()) {
                    for (Server el : oldList) {
                        if (el.ips.equals(ips)) {
                            pom = el;
                            var = true;
                            break;
                        }
                    }
                }
                if (!var) {
                    s = new Server(ips);
                } else {
                    s = new Server(pom);
                }
                try {
                    s.start();
                } catch (Exception e) {
                    System.out.println("eeeeeeeeeeeee" + s.ips + e);
                }
            }
            try {
                while (update < ipList.size()) {
                    Thread.sleep(200);
                }
                updateServerList();
                oldList = lista;
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println(Konsola.class.getName() + ex);
            }
        }
    }

    public static void updateServerList() {
        for (int i = lista.size(); i < 15; i++) {
            Gui.jTable1.setValueAt(false, i, 0);
            Gui.jTable1.setValueAt(null, i, 1);
            Gui.jTable1.setValueAt(null, i, 2);
            Gui.jTable1.setValueAt(null, i, 3);
            Gui.jTable1.setValueAt(null, i, 4);
            Gui.jTable1.setValueAt(null, i, 5);
            Gui.jTable1.setValueAt(null, i, 6);
            Gui.jTable1.setValueAt(null, i, 7);
            Gui.jTable1.setValueAt(null, i, 8);
            Gui.jTable1.setValueAt(null, i, 9);
        }
        int i = 0;
        for (Server el : lista) {

            Gui.jTable1.setValueAt(el.crash, i, 0);
            Gui.jTable1.setValueAt(el.ip, i, 1);
            Gui.jTable1.setValueAt(el.port, i, 2);
            Gui.jTable1.setValueAt(el.name, i, 3);
            Gui.jTable1.setValueAt(el.uptime, i, 4);
            Gui.jTable1.setValueAt(el.map, i, 5);
            Gui.jTable1.setValueAt(el.numpl + "/" + el.maxpl, i, 6);
            Gui.jTable1.setValueAt(el.time, i, 7);
            Gui.jTable1.setValueAt(el.password, i, 8);
            Gui.jTable1.setValueAt(el.mapstat, i, 9);

            Gui.enableButtons();
            Gui.enableTable();
            i++;
        }

    }

}
