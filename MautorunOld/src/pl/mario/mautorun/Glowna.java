
package pl.mario.mautorun;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;


/**
 *
 * @author Mateusz
 */
public class Glowna implements Runnable{
     
    static Process igi;
    public static Thread tlog;
    public static Thread tinfo;
    public static Thread trun;
    public static boolean crash;
    public static Runnable log;
    public static Runnable info;
    public static Runnable run;

    public Glowna() {
    }
    
    
     public static void main(String args[]) {
         
         
        
         File file = new File("networkconfig.cfg");
         if(!file.exists())
         {
             JOptionPane.showMessageDialog(null, "File "+file.getName()+" not found!\nPlace Mautorun in PC");
             return;
         }
         File file2 = new File("mss32.dll");
         if(!file2.exists())
         {
             JOptionPane.showMessageDialog(null, "File "+file2.getName()+" not found!\nPlace Mautorun in PC");
             return;
         }
         Error sett = new Error();
         Komendy.wyciagnij();
         Conf conf = new Conf();
         Conf.zapiszKlase(conf);
         sett.setVisible(false);
         Gui gui = new Gui();
         Pakiety pkt = new Pakiety();
         Loggs logs = new Loggs();
         igi = null;
         
         
    }
     
    
    
    
    public void run()
    {
        String linia;
        Process p = null;
        boolean var = false;
        while(true)
        {
            Conf.exe = Gui.nameexe.getText();
            Runtime r = Runtime.getRuntime(); 
        try {
            Gui.dodajLog("Starting server...",Gui.green);
            Pakiety snifer = new Pakiety();
            snifer.start();
            
            if(Conf.system.equals("win"))
                p = Runtime.getRuntime().exec("tasklist");
            else if (Conf.system.equals("lin"))
                p = Runtime.getRuntime().exec("ps ax");
            
            var = false;
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((linia = input.readLine()) != null)
                if (linia.indexOf(Conf.exe) > -1)
                {
                    var = true;
                    break;
                }
            input.close();
            
            if(!var)
            {
                if(Conf.system.equals("win"))
                    igi = r.exec(Conf.exe+" serverdedicated");
                else if (Conf.system.equals("lin"))
                    igi = r.exec("./"+Conf.exe+" serverdedicated");
                Thread.sleep(1000);
            }
        } catch (IOException ex) {
            Loggs.loguj("Glowna-Run", ex.toString());
        } catch (InterruptedException ex) {
            Loggs.loguj("Glowna-Run", ex.toString());
        }
        
        
        crash = false;
        String status = "";
        while(status.isEmpty())
            status = Pakiety.sendStatus();
        Gui.startSrvTogg.setText("Disable Server");
        Info inf = new Info();
        Gui.crashbar.setValue(0);
        log = new Log();
        info = new Info();
        tinfo = new Thread(info);
        tlog = new Thread(log);
        tinfo.start();
        tlog.start();
            
            while(!crash)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Loggs.loguj("Glowna-Run", ex.toString());
                }
            }
            Gui.dodajLog("CRASH!",Gui.boom);
            Glowna.tlog.interrupt();
            Glowna.tinfo.interrupt();
            
         
            try {
                if(Conf.system.equals("win"))
                    Runtime.getRuntime().exec("taskkill /F /IM "+Conf.exe+"*");
                else if (Conf.system.equals("lin"))
                    Runtime.getRuntime().exec("pkill "+Conf.exe);
                Thread.sleep(1000);
            } catch (IOException ex) {
                Loggs.loguj("Glowna-Run-kill", ex.toString());
            } catch (InterruptedException ex) {
                Loggs.loguj("Glowna-Run-kill", ex.toString());
            }
            
            
            Log.zapiszLog();
            int c = Integer.parseInt(Gui.crashVal.getText());
            Info.crash=c+1;
            Gui.crashVal.setText(Info.crash+"");
        }
        
    }
    
    public static void startServer()
    {
        run = new Glowna();
        trun = new Thread(run);
        trun.start();
    }
    
    public static void stopServer()
    {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to disable server?","Close Server",YES_NO_OPTION);
            if(x == 0)
            {
                Gui.dodajLog("Stops server...",Gui.red);
                igi.destroy();
                try {
                    if(Conf.system.equals("win"))
                       Runtime.getRuntime().exec("taskkill /F /IM "+Conf.exe+"*");
                    else if (Conf.system.equals("lin"))
                       Runtime.getRuntime().exec("pkill "+Conf.exe);
                    Thread.sleep(1000);
                } catch (IOException ex) {
                    Loggs.loguj("Glowna-Run-kill", ex.toString());
                } catch (InterruptedException ex) {
                    Loggs.loguj("Glowna-Run-kill", ex.toString());
                }
                tinfo.interrupt();
                
                tlog.interrupt();
                
                trun.interrupt();
                Log.zapiszLog();
                Gui.startSrvTogg.setText("Enable Server");
            }
            else
            {
                Gui.startSrvTogg.setSelected(true);
            }
            
    }
    
}
