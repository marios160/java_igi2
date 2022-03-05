package pl.mati.mwebblocker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.swing.JOptionPane;
import org.jnetpcap.*;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JHeader;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 *
 * @author Mario PL
 */
public class Sniffer extends Thread {

    PcapIf device;
    String deviceIP;
    boolean active;
    static List<Sniffer> list;
    static boolean checked = false;

    public Sniffer(PcapIf device, String ip) {
        this.device = device;
        this.deviceIP = ip;
        this.active = false;
    }

    public void run() {
        StringBuilder errbuf = new StringBuilder();
        int snaplen = 64 * 1024;
        int flags = Pcap.MODE_PROMISCUOUS;
        int timeout = 10 * 1000;
        String pomip = deviceIP;
        final String localip = pomip.substring(0, pomip.length() - 1);
        final Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
        if (pcap == null) {
            JOptionPane.showMessageDialog(null, "Error while opening device for capture: "
                    + errbuf.toString());
            return;
        }

        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
            Ip4 ip = new Ip4();
            Udp udp = new Udp();

            public void nextPacket(PcapPacket packet, String user) {
                if (packet.hasHeader(udp) && packet.hasHeader(ip)) {
                    if (udp.source() == 53) {
                        int offset = udp.getLength() + ip.getLength() + udp.getLength();
                        int length = udp.size() - offset;
                        byte[] byteData = packet.getByteArray(0, packet.size());
                        int dlugosc = byteData[54];
                        String data = new String(byteData);
                        int i = dlugosc;
                        int poz = 55;
                        String adres = "";
                        while (byteData[poz] != 0) {
                            adres += data.substring(poz, poz+i);
                            
                            poz = poz+i+1;
                            i = byteData[poz-1];
                            if(byteData[poz] != 0)
                                adres+=".";
                        }
                        Web s = new Web(adres);
                        s.start();
                    }

                }
            }

        };
        pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, null);
        pcap.close();
    }

    public PcapIf getDevice() {
        return device;
    }

    public void setDevice(PcapIf device) {
        this.device = device;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
