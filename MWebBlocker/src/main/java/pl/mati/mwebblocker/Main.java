/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mati.mwebblocker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapAddr;
import org.jnetpcap.PcapIf;

/**
 *
 * @author Mateusz
 */
public class Main {
    
    public static void main(String [] args){
        String path = System.getenv("SystemRoot");
        System.out.println(path);
        checkInterfaces();
    }
    

    public static void checkInterfaces() {
        List<PcapIf> alldevs = new ArrayList<PcapIf>();
        StringBuilder errbuf = new StringBuilder();
        int r = Pcap.findAllDevs(alldevs, errbuf);
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can't read list of devices, error is " + errbuf
                    .toString());
            return;
        }

        String addr = "";
        for (PcapIf alldev : alldevs) {
            for (PcapAddr adr : alldev.getAddresses()) {
                if (adr.getAddr() != null) {
                    addr = adr.getAddr().toString();
                }
                if (addr.indexOf("[INET4:0.0.0.0]") < 0 && addr.indexOf("[INET4:") > -1) {
                    Sniffer s = new Sniffer(alldev, addr);
                    System.out.println(addr);
                    s.start();
                }
            }
        }
    }
    
    
    public static void blokuj (String adres){
        String path = System.getenv("SystemRoot")+"\\System32\\drivers\\etc\\hosts";
        /*try {
            Runtime.getRuntime().exec("taskkill /F /IM chrome*");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore*");
            Runtime.getRuntime().exec("taskkill /F /IM opera*");
            Runtime.getRuntime().exec("taskkill /F /IM firefox*");
            Runtime.getRuntime().exec("taskkill /F /IM safari*");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {
            PrintWriter plik = new PrintWriter(new FileWriter(path,true));
            plik.append("46.239.162.37  "+adres.substring(7)+"\n");
            plik.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
