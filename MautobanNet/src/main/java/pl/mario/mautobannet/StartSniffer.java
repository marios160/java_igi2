package pl.mario.mautobannet;

import pl.mario.mautobannet.Sniffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapAddr;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;

/**
 *
 * @author Mateusz
 */
public class StartSniffer extends Thread {

    List<Sniffer> list = new ArrayList<>();
    static AutoBan a;
    public void run() {
        try {
            this.a = new AutoBan();
            List<PcapIf> alldevs = new ArrayList<PcapIf>();
            StringBuilder errbuf = new StringBuilder();
            int r = Pcap.findAllDevs(alldevs, errbuf);
            if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Can't read list of devices, error is " + errbuf
                        .toString());
                return;
            }
            boolean foundInterface = false;
            String addr = "";
            //SnifferDos.list = new ArrayList<>();
            for (PcapIf alldev : alldevs) {
//                if (alldev.getAddresses().isEmpty()) {
//                    Sniffer s = new Sniffer(alldev, addr, this);
//                    list.add(s);
//                    s.start();
//                    continue;
//                }
                for (PcapAddr adr : alldev.getAddresses()) {
                    if (adr.getAddr() != null) {
                        addr = adr.getAddr().toString();
                    }
                    //if (addr.indexOf("[INET4:0.0.0.0]") < 0 && addr.indexOf("[INET4:") > -1) {
                    if(addr.contains(htmlReader.pobierzIP())){
                        Sniffer s = new Sniffer(alldev, addr, this);
                        list.add(s);
                        s.start();
                         foundInterface = true;
                        break;
                    }
                }
                if (foundInterface) {
                    break;
                }
            }
            

        } catch (UnsatisfiedLinkError e) {
            JOptionPane.showMessageDialog(AutoBan.cns, "You have problem with libraries.\nContact with Mario PL!");
            System.exit(0);
        } catch (Exception ex) {
            Loggs.loguj("StartSniffer-Run", ex);
        }

//        long count = -1;
//        TestSniffer s = null;
//        for (TestSniffer el : list) {
//            if (count < el.count) {
//                count = el.count;
//                s = el;
//            }
//        }
//        closeSniffers();
//        AutoBan.snifer = new Sniffer(s.device, s.deviceIP, s.sniff);
//        AutoBan.snifer.start();
    }

//    public void closeSniffers() {
//        for (TestSniffer snifer : list) {
//            snifer.interrupt = true;
//            snifer.interrupt();
//        }
//    }

}
