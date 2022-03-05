package pl.mario.mautobannet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TestSniffer extends Thread {

    PcapIf device;
    String deviceIP;
    boolean interrupt;
    StartSniffer sniff;
    


    Udp udp = new Udp();
    Ip4 ip = new Ip4();
    Ethernet et = new Ethernet();
    SLL sll = new SLL();
    byte[] byteData = null;
    String data = null;
    int offset = 0;

    long count;
    long time;
    String ips = null;
    int port = 0;

    public TestSniffer(PcapIf device, String ip, StartSniffer sniff) {
        this.device = device;
        this.deviceIP = ip;
        this.interrupt = false;
        this.sniff = sniff;
        if (AutoBan.sys.equals("win")) {
            offset = 42;
        } else if (AutoBan.sys.equals("lin")) {
            offset = 44;
        }
        count = 0;
    }

    public TestSniffer(TestSniffer s) {
        this.device = s.device;
        this.deviceIP = s.deviceIP;
        this.interrupt = false;
        this.sniff = sniff;
        if (AutoBan.sys.equals("win")) {
            offset = 42;
        } else if (AutoBan.sys.equals("lin")) {
            offset = 44;
        }
        count = 0;
    }
    
    

    public void run() {
        try {
            StringBuilder errbuf = new StringBuilder();
            int snaplen = 64 * 1024;
            int flags = Pcap.MODE_NON_BLOCKING;
            int timeout = 1;

            final Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
            if (pcap == null) {
                JOptionPane.showMessageDialog(null, "Error while opening device for capture: "
                        + errbuf.toString());
                return;
            }

            PcapBpfProgram program = new PcapBpfProgram();
            pcap.compile(program, "udp port " + AutoBan.port, 0, 0xFFFFFF00);
            pcap.setFilter(program);

            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                Udp udp = new Udp();
                Ip4 ip = new Ip4();

                public void nextPacket(PcapPacket packet, String user) {
                    if (interrupt) {
                        return;
                    }
                    analyze(packet);

                }

            };
            pcap.loop(100, jpacketHandler, null);
            pcap.close();
        } catch (Exception ex) {
            Loggs.loguj("Sniffer-run", ex);
        }
    }


    public void analyze(PcapPacket packet) {
        try {
            if (packet.hasHeader(ip) && packet.hasHeader(udp)) {
                if (udp.destination() == AutoBan.port) {
                    count++;
                }

            }

        } catch (Exception ex) {
            Loggs.loguj("Packet:", ex, data);
        }
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



   

}
