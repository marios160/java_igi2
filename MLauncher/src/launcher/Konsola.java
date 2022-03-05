/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public class Konsola {

    public static List<Server> lista;
    public static List<Server> oldList;
    static String version = "v2.1";
    static Data d;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gui g = new Gui();
        d = Data.getConfFile();
        boolean var = false;
        Server pom = null;
        refresh();
        
    }
    
    public static void refresh() {
        List<String> ipList;
        ipList = getIpList();
            lista = new ArrayList<Server>();
            Server s = null;
            for (String ips : ipList) {
                s = new Server(ips);
                try {
                    s.start();
                } catch (Exception e) {
                    System.out.println("Konsola.refresh" + s.ips + e);
                }
            }
            try {          
   
                Thread.sleep(200);
                updateServerList();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println(Konsola.class.getName() + ex);
            }
    }

    public static List<String> getIpList() {
        List<String> ipList = null;
          ipList = Web.readHTML(d.masterServer);
        return ipList;
    }

    public static void updateServerList() {
        
        if (lista != null) {
                    Collections.sort(lista, new Comparator<Server>() {
                        @Override
                        public int compare(final Server object1, final Server object2) {
                            return object1.name.compareTo(object2.name);
                        }
                    });
                }
        
        for (int i = lista.size(); i < 15; i++) {
            Gui.jTable1.setValueAt(null, i, 0);
            Gui.jTable1.setValueAt(null, i, 1);
            Gui.jTable1.setValueAt(null, i, 2);
            Gui.jTable1.setValueAt(null, i, 3);
            Gui.jTable1.setValueAt(null, i, 4);
            Gui.jTable1.setValueAt(null, i, 5);
            Gui.jTable1.setValueAt(null, i, 6);
            Gui.jTable1.setValueAt(null, i, 7);
            Gui.jTable1.setValueAt(null, i, 8);
        }
        int i = 0;
        for (Server el : lista) {

            Gui.jTable1.setValueAt(el.ip, i, 0);
            Gui.jTable1.setValueAt(el.port, i, 1);
            Gui.jTable1.setValueAt(el.name, i, 2);
            Gui.jTable1.setValueAt(el.uptime, i, 3);
            Gui.jTable1.setValueAt(el.map, i, 4);
            Gui.jTable1.setValueAt(el.numpl + "/" + el.maxpl, i, 5);
            Gui.jTable1.setValueAt(el.time, i, 6);
            Gui.jTable1.setValueAt(el.password, i, 7);
            Gui.jTable1.setValueAt(el.mapstat, i, 8);
            i++;
        }

    }

}
