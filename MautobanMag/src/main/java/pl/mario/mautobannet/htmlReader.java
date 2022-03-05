/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.mautobannet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario PL
 */
public class htmlReader extends Thread {
    public htmlReader(){
    }
 
    public static boolean visitHTML(String adres) {
        BufferedReader br = null;
        try {
            StringBuffer content = new StringBuffer();
            URL url = null;

            url = new URL(adres);

            br = new BufferedReader(new InputStreamReader(url.openStream()));
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            Loggs.loguj("HtmlReader-visitHTML", ex);
            return false;
        }
        return true;
    }
    
   public static String readHTML(String adres)
   {
        StringBuffer content = new StringBuffer();
        try {
            
            URL url = null;
            
            url = new URL(adres);
            BufferedReader br = null;
            
            br = new BufferedReader(new InputStreamReader(url.openStream()));
 
            String line = null;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            if (br != null) {
                
                br.close();    
            }
        } catch (IOException ex) {
            Loggs.loguj("HtmlReader: read: ",ex);
        }
         return content.toString();
    }
   public static String pobierzIP() {
        String ip = null;
        /*if (visitHTML("http://www.igi2.xaa.pl/mautorun/mautorunip.php")) {
            ip = readHTML("http://www.igi2.xaa.pl/mautorun/ip");
            visitHTML("http://www.igi2.xaa.pl/mautorun/mautorunip.php?usun=1");
        } else*/ if (visitHTML("http://www.mariopl.y0.pl/mautorun/mautorunip.php")) {
            ip = readHTML("http://www.mariopl.y0.pl/mautorun/ip");
            visitHTML("http://www.mariopl.y0.pl/mautorun/mautorunip.php?usun=1");
        } else if (visitHTML("http://www.mariopl.comli.com/mautorun/mautorunip.php")) {
            ip = readHTML("http://www.mariopl.comli.com/mautorun/ip");
            visitHTML("http://www.mariopl.comli.com/mautorun/mautorunip.php?usun=1");
        } else {
            System.exit(1);
        }
        return ip.trim();
    }
   

 
}

