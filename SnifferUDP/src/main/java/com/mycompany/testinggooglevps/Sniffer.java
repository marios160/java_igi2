package com.mycompany.testinggooglevps;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.lan.SLL;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 *
 * @author Mario PL
 */
public class Sniffer extends Thread {

    PcapIf device;
    String deviceIP;
    boolean interrupt;
    static List<Sniffer> list;
    static boolean checked = false;
    Test sniff;

    private String LPRC = "LPRC"; //Create player
    private String TAHC = "TAHC"; //chat
    private String LLIK = "LLIK"; //kill
    private String HTUA = "HTUA"; //cd key
    private String LPED = "LPED"; //deactive player
    private String TTES = "TTES"; //set team and skin
    private String TSIL = "TSIL"; //commands
    private String PORD = "PORD"; //commands
    private String KCIP = "KCIP"; //commands
    private String ITCA = "ITCA"; //commands
    private String WSQR = "WSQR"; //commands
    private String IUQE = "IUQE"; //commands
    private String NRTM = "NRTM"; //Mautorun commands
    Queue<String> pckt;

    public Sniffer(PcapIf device, String ip) {
        this.device = device;
        this.deviceIP = ip;
        this.interrupt = false;
        this.sniff = sniff;
        this.pckt = new LinkedList<>();
        Save s = new Save(pckt);
        s.start();
    }

    public void run() {
        try {

            StringBuilder errbuf = new StringBuilder();
            int snaplen = 64 * 1024;
            int flags = Pcap.MODE_PROMISCUOUS;
            int timeout = 10 * 1000;
            String pomip = deviceIP;
            //final String localip = pomip.substring(0, pomip.length() - 1);

            final Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
            if (pcap == null) {
                JOptionPane.showMessageDialog(null, "Error while opening device for capture: "
                        + errbuf.toString());
                return;
            }

            PcapBpfProgram program = new PcapBpfProgram();
            pcap.compile(program, "udp port 26001", 0, 0xFFFFFF00);
            pcap.setFilter(program);

            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                Udp udp = new Udp();
                Ip4 ip = new Ip4();

                public void nextPacket(PcapPacket packet, String user) {

                    if (packet.hasHeader(udp) && packet.hasHeader(ip)) {
                        if (interrupt) {
                            return;
                        }
                        byte[] byteData = null;
                        char[] data = null;
                        int length = packet.size() - 42;

                        byteData = packet.getByteArray(42, length);

                        data = new String(byteData).toCharArray();
                        String pck = "";

                        pck += new SimpleDateFormat("[MM.dd][HH:mm:ss.SSSS]").format(new Date()) + "\n";
                        pck += FormatUtils.ip(ip.source()) + ":" + udp.source() + "   -->   "
                                + FormatUtils.ip(ip.destination()) + ":" + udp.destination()
                                + "   len: " + data.length + "\n\n";
                        int i = 0, j = 0;
                        for (byte c : byteData) {

                            if (i == 8) {
                                pck += " ";
                            }
                            pck += String.format("%02x ", c);
                            i++;

                            if (i > 15) {
                                pck += "   ";
                                for (int k = j; k < j + 16; k++) {
                                    if (k == j + 8) {
                                        pck += " ";
                                    }
                                    if ((int) data[k] < 33 || (int) data[k] > 126) {
                                        pck += ".";
                                    } else {
                                        pck += data[k];
                                    }

                                }
                                j += 16;
                                pck += "\n";
                                i = 0;

                            }

                        }
                        int x = 52;
                        if (i > 8) {
                            x = 51;
                        }
                        for (int k = 0; k < (x - (i * 3)); k++) {
                            pck += " ";
                        }
                        for (int k = j; k < data.length && k < j + 16; k++) {
                            if ((int) data[k] < 33 || (int) data[k] > 126) {
                                pck += ".";
                            } else {
                                pck += data[k];
                            }
                            if (k == j + 7) {
                                pck += " ";
                            }
                        }
                        pck += "\n";
                        pck += "\n";
                        pck += "\n";
                        //System.out.println(pck);
                        pckt.add(pck);
                    }
                    if (Thread.currentThread().isInterrupted()) {

                        pcap.breakloop();
                        return;
                    }
                }

            };
            pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, null);
            pcap.close();
        } catch (Exception ex) {
            System.out.println("Sniffer-run" + ex);
        }
    }

    public PcapIf getDevice() {
        return device;
    }

    public void setDevice(PcapIf device) {
        this.device = device;
    }

    public boolean isActive() {
        return interrupt;
    }

    public void setActive(boolean active) {
        this.interrupt = active;
    }

    public static List<Sniffer> getList() {
        return list;
    }

    public static void setList(List<Sniffer> list) {
        Sniffer.list = list;
    }

    public static boolean isChecked() {
        return checked;
    }

    public static void setChecked(boolean checked) {
        Sniffer.checked = checked;
    }

}
