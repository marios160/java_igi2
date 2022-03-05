package pl.mario.mautorun;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Klasa z ustawieniami programu 
 * @author Mario PL
 */
public class Conf implements Serializable{
    static public String ip;
    
    static public int port;
    static public String rcon;
    static public String juniorRcon;
    static String system;
    static SimpleDateFormat czas;
    static String exe;
    
    
    public Conf()  {
            Conf.ip = htmlReader.pobierzIP();
            pobierzNetw();
            this.system =  System.getProperty("os.name").toLowerCase();
            this.system = this.system.substring(0,3);
            this.czas = new SimpleDateFormat("[MM.dd][HH:mm:ss]");
            this.exe = "igi2.exe";
            
       
    }
    
    public static String getCzas()
    {
        Conf.czas = new SimpleDateFormat("[MM.dd][HH:mm:ss]");
        return Conf.czas.format(new Date())+" " ;
    }
    

    private static void pobierzNetw()
    {
        File file = new File("networkconfig.cfg");
        if(file.exists())
        {
            BufferedReader bufferedReader = null;
            
            try { bufferedReader = new BufferedReader(new FileReader(file));} 
            catch (FileNotFoundException ex) {Loggs.loguj("Conf-pobierznetw", ex.toString());}
            
            String linia = null;
            do {
                
                try {linia = bufferedReader.readLine();
                } catch (IOException ex) {Loggs.loguj("Conf-pobierznetw", ex.toString());}
                
                if (linia.indexOf(Komendy.rconpass) > 0)
                    Conf.rcon = linia.substring(13,linia.indexOf("\");"));
                else if (linia.indexOf(Komendy.svport) > 0)
                {
                    Conf.port = Integer.parseInt(linia.substring(10));
                    break;
                }
                
            } while(linia != null);
            
            try {bufferedReader.close();} 
            catch (IOException ex) {Loggs.loguj("Conf-pobierznetw", ex.toString());}
        }
        Conf c = pobierzKlase();
        if (c != null)
            Conf.juniorRcon = c.juniorRcon;
        else
            Conf.juniorRcon = "MarioPL";
        
    }
    
    public static Conf pobierzKlase()
    {
        File file2 = new File("cfg.mat");
        Conf c = null;
        if(file2.exists())
        {
            ObjectInputStream pl2=null;
            
            try {
                pl2=new ObjectInputStream(new FileInputStream("cfg.mat"));
                c = (Conf)pl2.readObject();
                pl2.close();
            } catch (IOException ex) {Loggs.loguj("Conf-pobierzklase", ex.toString());}
            catch (ClassNotFoundException ex) {Loggs.loguj("Conf-pobierzklase", ex.toString());}
        }
        else
        {
            return null;
        }
        return c;
    }
    
    public static void zapiszKlase(Conf c)
    {
        ObjectOutputStream pl=null;
        try {
            pl = new ObjectOutputStream(new FileOutputStream("cfg.mat"));
            pl.writeObject(c);
            pl.flush();
            pl.close();
        } catch (FileNotFoundException ex) {Loggs.loguj("Conf-zapiszklase", ex.toString());}
         catch (IOException ex) { Loggs.loguj("Conf-zapiszklase", ex.toString());}
    }
}
