
package pl.mario.igi2_ranking;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario PL
 */
public class Main {
    
    
    static String web = "http://ranking-001-site1.smarterasp.net/";
    static Sniffer snif;
    static HeartBeat h;
    static ModDetector mod;
    static Player player;
    public static void main(String args[]) throws IOException, InterruptedException{

        
        File f = new File("vqdll.dll");
        if(!f.exists())
        {
            JOptionPane.showMessageDialog(null, "Place Ranking in IGI2 PC!");
            System.exit(0);
        }
        f = new File("networkconfig.cfg");
        if(!f.exists())
        {
            JOptionPane.showMessageDialog(null, "Place Ranking in IGI2 PC!");
            System.exit(0);
        }
        f = new File("igi2.exe");
        if(!f.exists())
        {
            JOptionPane.showMessageDialog(null, "Place Ranking in IGI2 PC!");
            System.exit(0);
        }
        //new Links();
        //Logowanie l = new Logowanie();

        starnRanking();
    } 
    
    
    public static void starnRanking() throws IOException, InterruptedException{
        
            try {
                if(!Sniffer.var){
                    //mod = new ModDetector();
                    //mod.start();
                    snif = new Sniffer();
                    snif.start();
                    h = new HeartBeat();
                    h.start();
                }
                if(!checkTaskList())
                    Runtime.getRuntime().exec("igi2.exe");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Start IGI: "+ex);
            }
          
            
    }
    
    public static boolean checkTaskList() throws IOException{
        String linia;
        Process p = Runtime.getRuntime().exec("tasklist");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((linia = input.readLine()) != null)
                if (linia.indexOf("igi2.exe") > -1)
                    return true;
            
        return false;
    }
    
}
