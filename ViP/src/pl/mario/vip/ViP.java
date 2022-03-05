
package pl.mario.vip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario PL
 */
public class ViP {

    static String ip = "91.230.202.108";
    static int port = 9987;
    static byte [] rcon = "/*?0|$K!+".getBytes();
    static String nick = "";
    static byte [] status = "\\status\\".getBytes();
    public static void main(String[] args) {
        if(args.length > 0)
            nick = args[0];
        else{
            Scanner file = null;
            try {
                file = new Scanner(new File("networkconfig.cfg"));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            String linia;
            while(file.hasNext()){
                linia = file.nextLine();
                if(linia.indexOf("lo playername(\"")> -1){
                    nick = linia.substring(15,linia.length()-3);
                    break;
                }
            }
        }
        String statusS;
        if(!(statusS = Pakiety.sendStatus()).isEmpty()){
            int maxp,nump;
            int pozm, pozn, gamm;
            
            pozn = statusS.indexOf("numplayers\\")+11;
            pozm = statusS.indexOf("maxplayers\\")+11;
            gamm = statusS.indexOf("\\gamemode\\");
            nump = Integer.parseInt(statusS.substring(pozn,pozm-12));
            maxp = Integer.parseInt(statusS.substring(pozm,gamm));
            
            if(nump >= maxp){
                
                Pakiety.sendPck("/sv maxplayers "+(nump+1));
                Pakiety.sendPck("/sv specmode 2");
                try {
                    Runtime.getRuntime().exec("igi2.exe igi2 ip91.230.202.108 port9987 player\""+nick+"\"");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                statusS = "";
                for(int i = 0; i<20 && statusS.indexOf(nick)<0; i++){
                    statusS = Pakiety.sendStatus();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    
                }
                Pakiety.sendPck("/sv maxplayers "+maxp);
                Pakiety.sendPck("/sv specmode 1");
                if(statusS.indexOf(nick)>-1){
                    String id = statusS.substring(statusS.indexOf(nick)-5,statusS.indexOf(nick)-1);
                    id = id.substring(id.indexOf("_")+1);
                    Pakiety.sendPckAdmin("/\n[VV:II:PP] ["+id+"]*"+nick+"[");
                    
                }
            }else{
                Pakiety.sendPck("/sv specmode 2");
                try {
                    Runtime.getRuntime().exec("igi2.exe igi2 ip91.230.202.108 port9987 player\""+nick+"\"");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                statusS = "";
                for(int i = 0; i<20 && statusS.indexOf(nick)<0; i++){
                    statusS = Pakiety.sendStatus();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    
                }
                Pakiety.sendPck("/sv specmode 1");
                if(statusS.indexOf(nick)>-1){
                    String id = statusS.substring(statusS.indexOf(nick)-5,statusS.indexOf(nick)-1);
                    id = id.substring(id.indexOf("_")+1);
                    Pakiety.sendPckAdmin("/\n[VV:II:PP] ["+id+"]*"+nick+"[");
                    
                }
            
            }
            
        }
    }
    
}
