package pl.mario.mautobannet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;
import static pl.mario.mautobannet.StartSniffer.a;

/**
 *
 * @author Mario PL
 */
public class Sniffer extends Thread {

    PcapIf device;
    String deviceIP;
    boolean interrupt;
    static boolean checked = false;
    StartSniffer sniff;
    static String IP = "";

    static PacketData dataDos;
    String cmd1, cmd2;

    long count;
    long time;
    String ips = null;
    int port = 0;
    String infobyte;
    String joinbyte;
    Udp udp;
    Ip4 ip;


    public Sniffer(PcapIf device, String devIp, StartSniffer sniff) {
        this.device = device;
        this.deviceIP = devIp;
        this.interrupt = false;
        this.sniff = sniff;
        
        this.infobyte = new String(DatatypeConverter.parseHexBinary("abababababababab0000000005ababab"));
        this.joinbyte = new String(DatatypeConverter.parseHexBinary("001400ababab"));
        if (AutoBan.sys.equals("win")) {
            cmd1 = "netsh advfirewall firewall add rule name=\\\"IGIBan\\\" "
                    + "dir=in protocol=udp interface=any action=block remoteip=";
            cmd2 = "/32";
        } else if (AutoBan.sys.equals("lin")) {
            cmd1 = "iptables -I INPUT -s ";
            cmd2 = "/32 -j DROP";
        }
        dataDos = new PacketData("", "", null, null);
        count = 0;
        udp = new Udp();
        ip = new Ip4();
    }

    public void run() {

        StringBuilder errbuf = new StringBuilder();
        int snaplen = 64 * 1024;
        int flags = Pcap.MODE_PROMISCUOUS;
        int timeout = 2;

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

            public void nextPacket(PcapPacket packet, String user) {
                try {
                    if (interrupt) {
                        return;
                    }
                    analyze(packet);

                } catch (Exception | OutOfMemoryError ex) {
                    Loggs.loguj("Sniffer-run", ex);
                }
            }

        };
        pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, null);
        pcap.close();
    }

    public void analyze(PcapPacket packet) {
        String data;
        int length = packet.size() - 42;
        count++;
        data = new String(packet.getByteArray(42, length));
        try {
            if (packet.hasHeader(ip) && packet.hasHeader(udp)) {
                if (udp.destination() == AutoBan.port) {
                    if (data.contains(this.infobyte)) {
                     
                        ips = FormatUtils.ip(ip.source());
                        port = udp.source();
                        time = packet.getCaptureHeader().timestampInMillis();
                    } else if (data.contains(this.joinbyte)) {
                        //if (ips != null) {
                            if (ips.equals(FormatUtils.ip(ip.source()))) {
                                if (((packet.getCaptureHeader().timestampInMillis())) - (time) < 2000) {
                                    //Runtime.getRuntime().exec(cmd1 + ips + cmd2);
                                    a.ban(ips, 5);
                                    System.out.println(this.deviceIP);
                                } else if (port != udp.source()) {
                                    //Runtime.getRuntime().exec(cmd1 + ips + cmd2);
                                    a.ban(ips, 1);
                                }
                            }
//                        } else {
//                            System.out.println("Null: " + FormatUtils.ip(ip.source()));
//                        }

                        ips = "";
                        port = 0;
                        time = 0;
                    } else if (data.contains("%n%n")) {
                        System.out.println(cmd1 + ips + cmd2);
                        //Runtime.getRuntime().exec(cmd1 + ips + cmd2);
                        a.ban(FormatUtils.ip(ip.source()), 2);
                    } else if (data.trim().isEmpty()) {
                        //Runtime.getRuntime().exec(cmd1 + ips + cmd2);
                        a.ban(FormatUtils.ip(ip.source()), 3);
                    } else if (data.contains("3BE!ody6SAMPBE!dii")) {
                        //Runtime.getRuntime().exec(cmd1 + ips + cmd2);
                        a.ban(FormatUtils.ip(ip.source()), 4);
                    }
                    PacketData dataDos2 = new PacketData(FormatUtils.ip(ip.source()), data, dataDos, packet);
                    dataDos2.check();
                    dataDos = dataDos2;
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

    public static boolean isChecked() {
        return checked;
    }

    public static void setChecked(boolean checked) {
        Sniffer.checked = checked;
    }

}
