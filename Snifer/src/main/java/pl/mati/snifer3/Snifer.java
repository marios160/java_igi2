/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mati.snifer3;

import java.io.IOException;
import static java.lang.System.out;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snifer {

    public static void snifuj(){

          
        Enumeration<NetworkInterface> nets;
        try {
            nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                if (netint.isUp()) {
                    displayInterfaceInformation(netint);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Snifer.class.getName()).log(Level.SEVERE, null, ex);
        }

//        List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs  
//        StringBuilder errbuf = new StringBuilder(); // For any error msgs  
//
//        int r = Pcap.findAllDevs(alldevs, errbuf);
//        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
//            System.err.printf("Can't read list of devices, error is %s", errbuf
//                    .toString());
//            return;
//        }
//
//        System.out.println("Network devices found:");
//
//        int i = 0;
//        for (PcapIf device : alldevs) {
//            String description
//                    = (device.getDescription() != null) ? device.getDescription()
//                            : "No description available";
//            System.out.printf("#%d: %s [%s]\n", i++, device.getName(), description);
//            for (PcapAddr alldev : device.getAddresses()) {
//                System.out.println(alldev.getAddr().toString());
//                //System.out.println(alldev.getDstaddr().toString());
//            }
//        }
//
//        PcapIf device = alldevs.get(0); // We know we have atleast 1 device  
//        System.out
//                .printf("\nChoosing '%s' on your behalf:\n",
//                        (device.getDescription() != null) ? device.getDescription()
//                                : device.getName());
//
//        int snaplen = 64 * 1024;           // Capture all packets, no trucation  
//        int flags = Pcap.MODE_PROMISCUOUS; // capture all packets  
//        int timeout = 10 * 1000;           // 10 seconds in millis  
//        Pcap pcap
//                = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
//
//        if (pcap == null) {
//            System.err.printf("Error while opening device for capture: "
//                    + errbuf.toString());
//            return;
//        }
//
//        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
//
//            public void nextPacket(PcapPacket packet, String user) {
//                if (packet.hasHeader(new Udp())) {
//                    System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
//                            new Date(packet.getCaptureHeader().timestampInMillis()),
//                            packet.getCaptureHeader().caplen(), // Length actually captured  
//                            packet.getCaptureHeader().wirelen(), // Original length   
//
//                            user // User supplied object  
//                    );
//                }
//                byte[] data = packet.getByteArray(0, packet.size());
//                System.out.println(new String(data));
//                //JOptionPane.showMessageDialog(null, user);
//            }
//        };
//
//        pcap.loop(10, jpacketHandler, "jNetPcap rocks!");
//
//        pcap.close();
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress: %s\n", inetAddress);
        }
        out.printf("\n");
    }
}
