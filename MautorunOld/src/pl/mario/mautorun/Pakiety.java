
package pl.mario.mautorun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Klasa wysyłająca komendy na serwer
 * @author Mario PL
 */
public class Pakiety extends Thread{
    private static byte rcon[];    
    private static String ip;
    private static int port;
    private static byte status[];
    static public String localip;
    static String nameInterface;
    static int snifport;

    public Pakiety() {
        try{      
            String r = "/"+Conf.rcon;
            this.rcon = r.getBytes();
            this.ip = Conf.ip;
            this.port = Conf.port;
            this.status = "\\status\\".getBytes();
            InetAddress me = InetAddress.getLocalHost();
            this.localip = me.getHostAddress();
            this.nameInterface = getInterface();
            this.snifport = Integer.parseInt(Gui.snifport.getText());
         } catch (UnknownHostException ex) {
            Loggs.loguj("Pakiety-konstruktor-localip", ex.toString());
        }
    }
    
    /**
     * Wysyła komende na serwer.
     * przed wysłaniem komendy jest zawsze wysyłany rcon
     * @param msg treść komendy
     * @return odpowiedz od serwera
     */
    
    public static String getInterface()
    {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets))
            {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses))
                {
                    String adres = inetAddress.toString();
                    if(adres.substring(1).equals(ip) || adres.substring(1).equals(localip))
                    {
                        return netint.getName();
                    }
                }
            }
            
        } catch (SocketException ex) {
            Loggs.loguj("Conf-getInterfaces", ex.toString());
        }
        return null;
    }
    
    
    public static String sendPck(String msg)
    {
       String message = "";
      try{
        InetAddress servAddr = InetAddress.getByName(Pakiety.ip);
        byte buf[] = msg.getBytes();
        byte buff[];
        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(3000);
        
        socket.send(new DatagramPacket(Pakiety.rcon, Pakiety.rcon.length, servAddr, Pakiety.port)); //wysylamy rcon
        buff= new byte[4096];
        DatagramPacket prcon = new DatagramPacket(buff, buff.length);
        socket.receive(prcon);  //odbieramy komende
        
        socket.send(new DatagramPacket(buf, buf.length, servAddr, Pakiety.port));              //nastepnie komende
        buf = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);  //odbieramy komende
        socket.close();
        
        message = new String(packet.getData());
        message = message.trim();
        message = message.substring(22,message.length()-4);
        
     }catch(Exception ex)
        {
               Loggs.loguj("Pakiety-sendPck", ex.toString());     
        }
      return message;
    }
    
    /**
     * Metoda wysyła \\status\\ w celu otrzymania
     * informacji na temat serwera
     * @return tresc statusu
     */
    public static String sendStatus()
    {
        String message = "";
        try{
        InetAddress servAddr = InetAddress.getByName(Pakiety.ip);

        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(3000);
        socket.send(new DatagramPacket(status, status.length, servAddr, Pakiety.port));              //nastepnie komende

        
        byte buf[] = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);                                             //odbieramy komende
        message = new String(packet.getData());
        socket.close();
        
        }catch(IOException ex)
        {
            //Loggs.loguj("Pakiety-sendStatus", ex.toString()); 
        }
        return message.trim();
    }
    
   
    public static boolean sendListMaps()
    {
        String message = "";
        String msg = "/sv listmaps";
      try{
        InetAddress servAddr = InetAddress.getByName(Pakiety.ip);
        byte buf[] = msg.getBytes();
        byte buff[];
        DatagramSocket socket= new DatagramSocket();
        socket.setSoTimeout(3000);
        
        socket.send(new DatagramPacket(Pakiety.rcon, Pakiety.rcon.length, servAddr, Pakiety.port)); //wysylamy rcon
        buff= new byte[4096];
        DatagramPacket prcon = new DatagramPacket(buff, buff.length);
        socket.receive(prcon);  //odbieramy komende
        
        socket.send(new DatagramPacket(buf, buf.length, servAddr, Pakiety.port));              //nastepnie komende
        
        int i = 0;
        String[] maps = new String[100];
        do
        {
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende
            message = null;
            message = new String(packet.getData());
            message = message.trim();
            message = message.substring(22,message.length()-4);
            if(message.indexOf("NetManager_ListMapsCB called") > 0)
                break;
            maps[i] = message;
            i++;
        }while(message.indexOf("NetManager_ListMapsCB called") < 0);
        socket.close();
        
        Info.listMaps = new String[i];
        Info.listIdMaps = new String[i];
        for(int j=0; j<i; j++)
        {
            Info.listIdMaps[j] = maps[j].substring(maps[j].indexOf("[")+1,maps[j].indexOf("]"));
            Info.listMaps[j] = maps[j].substring(maps[j].indexOf("]")+1,maps[j].length());
            if (Info.listMaps[j].indexOf("[ACTIVE]") > 0)
                Info.listMaps[j] = Info.listMaps[j].substring(0,Info.listMaps[j].indexOf("["));
            Info.listMaps[j] = Info.listMaps[j].trim();
        }
     }catch(Exception ex)
        {
               Loggs.loguj("Pakiety-sendListMaps", ex.toString()); 
               return false;
        }
        return true;
    }
    
    public void run()
    {
        
        try {
            if(Conf.system.equals("win"))
                Runtime.getRuntime().exec("MSnifer.exe "+localip+" "+snifport+" "+nameInterface);
            else if (Conf.system.equals("lin"))
                Runtime.getRuntime().exec("./MSnifer.exe "+localip+" "+snifport+" "+nameInterface);
            DatagramSocket socket = new DatagramSocket(snifport);
            while(true)
            {
                byte[] buf = new byte[4096];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                /* 1. Oczekujemy na pakiet ...*/
                socket.receive( packet );
                System.out.println(new String(buf));
                byte[] odp = odbierz(new String(buf)).getBytes();
                System.out.println(new String(odp));
                /* 2. Odsyłamy do nadawcy ... */
                socket.send(new DatagramPacket(odp, odp.length, packet.getAddress(), packet.getPort()));
            }
        } catch (SocketException ex) {
            Loggs.loguj("Pakiety-ServerUDP", ex.toString());
        } catch (IOException ex) {
            Loggs.loguj("Pakiety-ServerUDP", ex.toString());
        }
    }

    static String odbierz(String pkt)
    {
        try{
        if(pkt.indexOf("Mautorun remoteip") > -1)
            return Conf.ip;
        else if(pkt.indexOf("Mautorun interface") > -1)
            return nameInterface;
        else if(pkt.indexOf("Mautorun juniorrcon") > -1)
            return Conf.juniorRcon;
        else if(pkt.indexOf("Mautorun rcon") > -1)
            return Conf.rcon; 
        else if(pkt.indexOf("Mautorun time") > -1)
            return Conf.getCzas();
        else if(pkt.indexOf("Mautorun items") > -1)
        {
            
        }
        else if(pkt.indexOf("Mautorun chat") > -1)
        {
            System.out.println(pkt);
        }
        else if(pkt.indexOf("Mautorun /cmd/") > -1)
        {
            String linia, odp = "";
            Process p;
            p = Runtime.getRuntime().exec(pkt.substring(15));
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((linia = input.readLine()) != null)
               odp+=linia+"\n"; 
            input.close();
            return odp;
        }
        else if(pkt.indexOf("Mautorun aadmin") > -1)
        {
            
        }
        }catch(IOException ex){Loggs.loguj("Pakiety-Odbierz", ex.toString()); }
        
        return "Wrong command";
    }
    
   }
