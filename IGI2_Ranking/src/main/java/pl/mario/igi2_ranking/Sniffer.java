
package pl.mario.igi2_ranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;


/**
 *
 * @author Mario PL
 */
public class Sniffer extends Thread{
    
    static boolean var = false;
    static long czas;
    static int gid1;
    static int gid2;
    static boolean stop = true;
    static String status = "";
    Kill k;

    public Sniffer() throws IOException {
    }
    
    
    public void run() {  
        var = true;
        List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs  
        StringBuilder errbuf = new StringBuilder(); // For any error msgs  
        int r = Pcap.findAllDevs(alldevs, errbuf);  
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {  
            JOptionPane.showMessageDialog(null,"Can't read list of devices, error is "+errbuf  
                .toString());  
            return;  
        }  
        PcapIf device = alldevs.get(0); // We know we have atleast 1 device  
        int snaplen = 64 * 1024;           // Capture all packets, no trucation  
        int flags = Pcap.MODE_PROMISCUOUS; // capture all packets  
        int timeout = 10 * 1000;           // 10 seconds in millis  
        Pcap pcap =  
            Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);  
        if (pcap == null) {  
            JOptionPane.showMessageDialog(null,"Error while opening device for capture: "  
                + errbuf.toString());  
            return;  
        }  
  
        
        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {  
            Udp udp = new Udp();
            Ip4 ip = new Ip4();
            String join = new String(new byte[] {(byte)0x0c,(byte)0x01,(byte)0xab,(byte)0xab,(byte)0xab,(byte)0x00,(byte)0xab,(byte)0xab,(byte)0xab});
            String pong = "GNOP";
            String kill = "LLIK";
            
            
            public void nextPacket(PcapPacket packet, String user) {
                if(!stop)
                    return;
                if(packet.hasHeader(udp))
                {
                    if(packet.hasHeader(ip) && FormatUtils.ip(ip.source()).equals(HeartBeat.ipsrv)){
                        byte[] data =  packet.getByteArray(0, packet.size());   
                        String d = new String(data);
                        
                        if(d.contains(join)){
                            Main.player.setIntid(data[70]);
                        }
                        else 
                        if(d.contains(pong)){
                            Main.player.setIntid(data[50]);
                            HeartBeat.var = true;
                        }
                        else 
                        if(d.contains(kill)){
                            if(data[78] == Main.player.getIntid() || data[82] == Main.player.getIntid()){
                               if((gid1 != data[78] || gid2 != data[82]) || System.currentTimeMillis() > czas+1000){
                                    analyzeKill(data);
                                    gid1 = data[78];
                                    gid2 = data[82];
                                    czas = System.currentTimeMillis();
                               }
                            }
                        }
                    
                    }
                }
                
                
            }  
        };  
        pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler,null);  
        pcap.close();
    }
    
    
    static void analyzeKill(byte[] pck) {

        String team1 = "", team2 = "";
        String idpl = "team_"+Main.player.getId()+"\\";
        int id1 = pck[78];
        int id2 = pck[82]; 
        int place = pck[86];
        int type = pck[90];
        int weapon = pck[94];
        int spawn = pck[98];
        int frag = 0;
        String teamid1 = "team_"+id1+"\\"; 
        String teamid2 = "team_"+id2+"\\"; 
        String id1S = Integer.toString(id1); 
        String id2S = Integer.toString(id2); 
        try{
            Main.player.setTeam(status.substring(status.indexOf(idpl)+idpl.length(),status.indexOf(idpl)+idpl.length()+1));
            team1 = status.substring(status.indexOf(teamid1)+teamid1.length(),status.indexOf(teamid1)+teamid1.length()+1);
            team2 = status.substring(status.indexOf(teamid2)+teamid2.length(),status.indexOf(teamid2)+teamid2.length()+1);
        }catch(StringIndexOutOfBoundsException ex){System.out.println("teams substr: "+status);}
        int team1i = 0;
        int team2i = 0;
        try{
            team1i = Integer.parseInt(team1);
            team2i = Integer.parseInt(team2);
        }catch(NumberFormatException ex){System.out.println("teams parse: "+team1+" "+team2+" "+status);}
        Kill kill = null;
       /* if(player.getId().equals(id2S)){//smierc
            if(player.getId().equals(id1S)){//suicide i upadek
                kill = new Kill(0,0,17);
            }else{//death
                if(id1 == -1){ //miny dzialko
                    if(pck[90] == 1){//miny
                        kill = new Kill(0,15,19);
                    }else{          //dzialko
                        kill = new Kill(0,0,17);
                    }
                }else{  //normalna smierc
                    if(pck[90] == 1){//rozwalony
                        kill = new Kill(0,15,19);
                    }else{ //zabity
                        kill = new Kill(0,pck[94],pck[86]);
                    }
                }
            }
                
        }else{//kill
            if(player.getId().equals(id1S)){
                if(team1.equals(team2)){
                    kill = new Kill(1,44,18);
                }else{
                    if(pck[98] == 1){ //spawn
                        kill = new Kill(1,44,18);
                    }else
                    if(pck[90] == 1){//rozwalony
                        kill = new Kill(1,15,19);
                    }else
                    if(pck[90] == 3){//rozwalony
                        kill = new Kill(1,19,20);
                    }else{ //zabity
                        kill = new Kill(1,pck[94],pck[86]);
                    }                   
                }
            }
        }*/
        
        if(id1 != Main.player.getIntid() || id1 == -1 || id1 == id2){
            frag = 0;
        }else
        if(id1 == Main.player.getIntid()){
            frag = 1;
        }
        if(team1i == team2i)
            spawn = 1;

        
        switch(id1){
            case -1://skok, miny, dzialko
                kill = new Kill(frag,0,17);
                break;
            default://reszta
                switch(weapon){
                    case -1://suicide
                        kill = new Kill(frag,0,17);
                        break;
                    default://reszta
                        switch(spawn){
                            case 0://nie na spawnie
                                switch(type){
                                    case 0://normalnie
                                        kill = new Kill(frag,weapon,place);
                                        break;
                                    case 1://wybuch
                                        kill = new Kill(frag,15,19);
                                        break;
                                    case 3://noz kolba
                                        switch(weapon){
                                            case 18://noz
                                                kill = new Kill(frag,weapon,place);
                                                break;
                                            default://kolba
                                                kill = new Kill(frag,19,20);
                                                break;
                                        }
                                        break;
                                    default:
                                        kill = new Kill(frag,-1,-1);
                                        break;
                                }
                                break;
                            case 1://na spawnie
                                switch(frag){
                                    case 0://jesli dostalem na spawnie
                                        if(id1 == id2) //jesli siebie granatem to suicide
                                            kill = new Kill(frag,0,17);
                                        else
                                            kill = new Kill(frag,weapon,place);
                                        break;
                                    case 1://jesli zabilem na spawnie
                                            kill = new Kill(frag,44,18);
                                        break;
                                }
                                
                                break;
                            default:
                                kill = new Kill(frag,-1,-1);
                                break;
                        }
                        break;
                }
                break;
        }
        System.out.print("id1 "+id1+" id2 "+id2+" frag "+frag+" spawn "+spawn+" type "+type+" == ");
        String staty = kill.getString();
        Main.player.setStats(staty);
        Kill.staty(staty);
    }
    

}
