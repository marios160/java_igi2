
package pl.mario.mautoban;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario PL
 */
public class Loguj {
    
    public static void loguj(String msg)
    {
        try {
            PrintWriter log = new PrintWriter(new FileWriter("mautoban.log",true));
            log.append(AutoBan.time()+" "+msg+"\n");
            log.close();   
        } catch (IOException ex) {
            Logger.getLogger(Loguj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
