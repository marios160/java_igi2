/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testinggooglevps;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JOptionPane;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapAddr;
import org.jnetpcap.PcapIf;

/**
 *
 * @author Mateusz
 */
public class Test {
    
    public static void main(String[] args) {
        try{
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
                if(alldev.getAddresses().isEmpty()){
                    Sniffer s = new Sniffer(alldev, addr);
                        s.start();
                }
                for (PcapAddr adr : alldev.getAddresses()) {
                    if (adr.getAddr() != null) {
                        addr = adr.getAddr().toString();
                    }
                    if (addr.indexOf("[INET4:0.0.0.0]") < 0 && addr.indexOf("[INET4:") > -1) {
                        Sniffer s = new Sniffer(alldev, addr);
                        s.start();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
