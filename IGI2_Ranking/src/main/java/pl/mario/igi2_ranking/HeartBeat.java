
package pl.mario.igi2_ranking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Mateusz
 */
public class HeartBeat extends Thread{
    static boolean var = false;
    static boolean stop = true;
    static String ipsrv;
    static int portsrv;
    
    public HeartBeat() throws IOException {
        String adr = Web.readHTML(Main.web+Links.ip);
        this.ipsrv = adr.substring(adr.indexOf("[")+1,adr.indexOf(":"));
        this.portsrv = Integer.parseInt(adr.substring(adr.indexOf(":")+1,adr.indexOf("]")));
    }
    
    
    
    static void send(String nick) throws IOException{
            Web.visitHTML(Main.web+Links.heartbeat+"?login="+nick);
    }
    
    public void run(){
        while(stop){
            try {
                if(var){
                    send(Main.player.getLogin());
                    if(!Main.player.getStats().isEmpty()){
                        Web.sendPOST(Main.web+"Score", Main.player.getLogin(), Main.player.getStats());
                        Main.player.clrStats();
                    }
                    var = false;
                }
                for(int i=0; i<60; i++){
                    String stat = sendPck(ipsrv, portsrv);
                    if(!stat.isEmpty())
                        Sniffer.status = stat; 
                    Thread.sleep(1000);
                    if(!stop)
                        break;
                }
            } catch (IOException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "HeartBeat: Run: "+ex);
            } 
        }
    }
    
        public static String sendPck(String ip, int port){
        try {
            String message = "";
            String msg = "\\players\\";
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg.getBytes();
            
            DatagramSocket socket= new DatagramSocket();
            socket.setSoTimeout(3000);
            
            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende
            socket.close();
            
            message = new String(packet.getData());
            message = message.trim();
            return message;
        } catch (IOException ex) {
            return "";
        }
    }
    
}
