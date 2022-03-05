
package pl.mario.mautorun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class Log implements Runnable{
    
    private static Player[] players;
    
     @Override
    public void run() {
        Log.analizuj();
    }
    
    
    public static void analizuj() 
    {
        String linia = "";
        long dl;
        long poz = 0;
        int l = 0;
        players = new Player[5];
        Player player;
        String pom1, pom2="";
        RandomAccessFile czyt = null;
        
        try {
            czyt = new RandomAccessFile("Multiplayer.log","r");
            while(true)
            {     
                    
                   
                 
                
                dl = czyt.length();
                czyt.seek(poz);
                while(poz < dl)
                {
                    linia = czyt.readLine();
                    //Thread.State state = Thread.currentThread().getState();
                    //JOptionPane.showMessageDialog(null, state); 
                    poz = czyt.getFilePointer();
                    //nowy gracz czeka w kolejce
                    if(linia.indexOf("Popped new networkID for joining client:") > -1)
                    {
                        String id, ip;
                        id = Wytnij.wytnij(linia,53,"]");
                        ip = Wytnij.wytnij(linia,linia.indexOf("][")+2,":");
                        if(l > 4 )
                            l = 0;
                        players[l] = new Player(id, ip);
                        l++;
                    }
                    //nowy gracz wchodzi
                    else if(linia.indexOf("Client[") > -1 && linia.indexOf("created locally") > -1)
                    {
                        if(linia.indexOf("[]")> -1)
                        {
                            continue;
                        }
                        
                        pom1 = Wytnij.wytnij(linia,18,"]");
                        String nick = Wytnij.wytnij(linia,linia.indexOf("][")+2,"]");
                        for(int i=0; i<5; i++)
                            if (players[i] != null && players[i].getId().equals(pom1))
                            {
                                player = new Player(players[i].getId(),players[i].getIp(),nick);
                                Info.addPlayer(player);
                                players[i] = null;
                                Info.odwiedziny++;
                                Gui.dodajChat("["+pom1+"] "+nick+" has joined",Gui.gray);
                            }
                    }
                    
                    else if(linia.indexOf("][") == 13 || linia.indexOf("][") == 14)
                    {
                        //gracz wychodzi
                        if(linia.indexOf(" left the game") > -1)
                        {
                            pom1 = Wytnij.wytnij(linia,12,"]");
                            String nick = Wytnij.wytnij(linia,linia.indexOf("][")+2,"]");
                            Info.delPlayers(Integer.parseInt(pom1));
                            Gui.dodajChat("["+pom1+"] "+nick+" left the game",Gui.gray);
                        }
                        //gracz traci polaczenie
                        else if(linia.indexOf(" removed: Connection lost") > -1)
                        {
                            pom1 = Wytnij.wytnij(linia,12,"]");
                            String nick = Wytnij.wytnij(linia,linia.indexOf("][")+2,"]");
                            Info.delPlayers(Integer.parseInt(pom1));
                            Gui.dodajChat("["+pom1+"] "+nick+" lost connection",Gui.lgray);
                        }
                        //gracz wykopany
                        else if(linia.indexOf(" kicked out of the game") > -1)
                        {
                            pom1 = Wytnij.wytnij(linia,12,"]");
                            String nick = Wytnij.wytnij(linia,linia.indexOf("][")+2,"]");
                            Info.delPlayers(Integer.parseInt(pom1));
                            Gui.dodajChat("["+pom1+"] "+nick+" kicked",Gui.lgray);
                        }
                    }
                    else if(linia.indexOf("Client_Deletehandler: Removing ") > -1)
                    {
                        pom1 = Wytnij.wytnij(linia,43,"]");
                        if (Info.players[Integer.parseInt(pom1)] != null)
                        {
                            String nick = linia.substring(43+pom1.length()+1);
                            Info.delPlayers(Integer.parseInt(pom1));
                            Gui.dodajChat("["+pom2+"] "+nick+" left the game",Gui.gray);
                        }
                    }
                    //crash blad sieci
                    else if(linia.indexOf("Network_Send():sendto:error") > -1)
                    {
                        Gui.dodajLog("Network error",Gui.blue);
                        Info.crash++;
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////
                    else if(linia.indexOf(": ") > -1)
                    {
                        String nick = linia.substring(11,linia.indexOf(": "));
                        if (Info.find(nick))
                        {
                            Gui.dodajChat(linia.substring(11)+"",Gui.black);
                        }
                        
                        if(linia.indexOf(": /") > -1)
                        {
                            ////////////////////////////////////////////////////////////
                            //komendy
                            Gui.dodajLog(nick+" use \""+linia.substring(linia.indexOf(": /"))+"\"", null);
                            Runnable cmd = new Cmd(linia);
                            Thread sprcmd = new Thread(cmd);
                            sprcmd.start();
                        }
                    }
                   
                }
                //czyt.close();
                
                Thread.sleep(1000);
               
            
        }
        } 
            catch (IOException | InterruptedException ex) {
                try {
                    czyt.close();
                } catch (IOException ex1) {
                    Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Loggs.loguj("Log-analizuj", ex.toString());}
    }
    
    public static void zapiszLog()
    {
        File log = new File("logi");
        if (!log.exists())
            log.mkdir();      
        Conf.czas = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String czas = Conf.czas.format(new Date());
   
        File afile = new File("Multiplayer.log");
        File bfile = null;
        if(Conf.system.equals("lin"))
        {
            bfile = new File("logi/"+czas+".log");
        }
        else if(Conf.system.equals("win"))
        {
            bfile = new File("logi\\"+czas+".log");
        }
        
        try {
            Files.copy(afile.toPath(), bfile.toPath(), REPLACE_EXISTING);
        } catch (IOException ex) {Loggs.loguj("Log-zapiszLog", ex.toString());}
        
    }    

   
}
