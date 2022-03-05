
package bangun;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario PL
 */
public class Loguj1 {
    
    public static void loguj(String msg)
    {
        try {
            PrintWriter log = new PrintWriter(new FileWriter("MBanGun.log",true));
            log.append(time()+" "+msg+"\n");
            log.close();   
        } catch (IOException ex) {
            Logger.getLogger(Loguj1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public static String time()
    {
        SimpleDateFormat czas = new SimpleDateFormat("[MM.dd][HH:mm:ss]");
        return czas.format(new Date())+" ";
    }
    
}
