package pl.mario.mautobannet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import static pl.mario.mautobannet.Loggs.zakoduj;

/**
 *
 * @author Mario PL
 */
public class SnifferDos extends Thread {

    PcapIf device;
    String deviceIP;
    boolean interrupt;
    static List<SnifferDos> list;
    static boolean checked = false;
    StartSniffer sniff;
    static String IP = "";
    static boolean doss = false;
    static int dossPacket = 0;
    static int normalPacket = 0;

    long time;
    String ips = null;
    int port = 0;

    public SnifferDos(PcapIf device, String ip, StartSniffer sniff) {
        this.device = device;
        this.deviceIP = ip;
        this.interrupt = false;
        this.sniff = sniff;
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

            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                Udp udp = new Udp();
                Ip4 ip = new Ip4();

                public void nextPacket(PcapPacket packet, String user) {

                    if (packet.hasHeader(udp)) {
                        if (interrupt) {
                            return;
                        }
                        ifDos(packet);
                    }
                }

            };
            pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, null);
            pcap.close();
        } catch (Exception ex) {
            Loggs.loguj("Sniffer-run", ex);
        }
    }

    public boolean ifDos(PcapPacket packet) {
        Udp udp = new Udp();
        Ip4 ip = new Ip4();
        Ethernet et = new Ethernet();
        SLL sll = new SLL();
        byte[] byteData = null;
        String data = null;
        int offset = 0;
        if (AutoBan.sys.equals("win")) {
            offset = 42;
        } else if (AutoBan.sys.equals("lin")) {
            offset = 44;
        }
        int length = packet.size() - offset;

        byteData = packet.getByteArray(offset, length);

        data = new String(byteData);
        if (dossPacket > 20) {
            if (normalPacket > dossPacket) {
                //Sniffer.doss = false;
                for (SnifferDos el : list) {
                    el.interrupt = true;
                }
            } else {
                dossPacket = 0;
                normalPacket = 0;
            }
        }
        if (packet.hasHeader(ip) && packet.hasHeader(udp)) {
            if (udp.destination() == AutoBan.port) {

                if (data.trim().isEmpty()) {
                    dossPacket++;
                } else {
                    normalPacket++;
                }
            }
        }

        return true;
    }


    public void loguj(Udp udp, Ip4 ip, long time) {
        try {
            PrintWriter log = new PrintWriter(new FileWriter("Mtbn.log", true));
            log.append(ips + ":" + port + " -> " + FormatUtils.ip(ip.source()) + ":" + udp.source() + time + "\n");
            log.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Loguj: " + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Loguj: " + ex);
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

    public static List<SnifferDos> getList() {
        return list;
    }

    public static void setList(List<SnifferDos> list) {
        SnifferDos.list = list;
    }

    public static boolean isChecked() {
        return checked;
    }

    public static void setChecked(boolean checked) {
        SnifferDos.checked = checked;
    }

}
