/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webunban;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class WebUnban {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            while (true) {
                String ip = Web.readHTML("http://mariopl.y0.pl/unbaniki");
                if (ip.isEmpty()) {
                    Thread.sleep(10000);
                    continue;
                }
                String cmd = "/sbin/iptables -I INPUT -s "+ip+" -j ACCEPT";
                Runtime.getRuntime().exec(cmd);
                System.out.println("Unbanned ip: "+ip);
                Web.visitHTML("http://mariopl.y0.pl/uban.php?usun=1");
                ip = "";
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(WebUnban.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebUnban.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
