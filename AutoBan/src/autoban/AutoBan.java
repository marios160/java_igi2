
package autoban;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import static autoban.Strings.*;

/**
 *
 * @author Mario PL
 */
public class AutoBan {
    static String sys;
    static String linia = "";
    static long dl,olddl=0;
    static long poz = 0;
    static int l = 0;
    static int spr = 0, sysi = 0;
    static long times;
    static RandomAccessFile czyt = null;
    static Player [] players = {null,null,null,null,null};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Console cns = new Console();
        try {
                Console.okno.insertString(Console.okno.getLength(),time()+odkoduj(astarting)+"\n", Console.black);
            } catch (BadLocationException ex) {
                System.out.println(time()+"Main: starting: "+ex);
            }
        
        sys = System.getProperty("os.name");
        if(sys.contains("Windows"))
            sysi = 1;
        else if(sys.contains("Linux"))
            sysi = 2;
        else
        {
            JOptionPane.showMessageDialog(null, "System not recognized!\nReport to Mario PL");
            System.exit(0);
        }     
        Sprawdz s = new Sprawdz();
        
        
        s.start();
        try {
                Console.okno.insertString(Console.okno.getLength(),time()+odkoduj(detect)+"\n", Console.black);
            } catch (BadLocationException ex) {
                System.out.println(time()+"Main: detect: "+ex);
            }
        while(true)
        {
            analizuj();
            Thread.sleep(100);
        }
        
    }
    
    public static void analizuj()
    {
        try {

                czyt = new RandomAccessFile(odkoduj(multiplayer),"r");
                dl = czyt.length();
                if(dl < olddl)
                    poz=0;
                else
                    olddl = dl;
                czyt.seek(poz);
                while(poz < dl)
                {
                    linia = czyt.readLine(); 
                    poz = czyt.getFilePointer();
                    if(!linia.isEmpty() && linia.indexOf(odkoduj(serverinfo)) > -1)
                    {
                        String ip="", port="";
                        int time = 0;
                        try{
                            time = Integer.parseInt(linia.substring(7,9));
                            ip = linia.substring(31,linia.indexOf(":",31));
                            port = linia.substring(linia.indexOf(":",31)+1);
                        }catch(StringIndexOutOfBoundsException ex){System.out.println(linia+ex);}
                        
                        players[l] = new Player(ip,port,time);
                        if(l >= 4)
                            l = 0;
                        else
                            l++;
                        times = timem();
                        
                        //[16:18:42] Server info sent to 178.172.192.71:1954
                    }
                    else if(!linia.isEmpty() && linia.indexOf(odkoduj(networkpacket)) > -1)
                    {
                        String ip="", port="";
                        int time = 0, time2 = 0;
                        try{
                            time = Integer.parseInt(linia.substring(7,9));
                            if(time == 0)
                                time2 = 59;
                            else
                                time2 = time-1;
                            ip = linia.substring(45,linia.indexOf(":",45));
                            port = linia.substring(linia.indexOf(":",45)+1,linia.indexOf("]",11));
                        }catch(StringIndexOutOfBoundsException ex){System.out.println(linia+ex);}
                        
                        for(int i=0; i<5; i++)
                        {
                            if(players[i] != null && (players[i].time == time || players[i].time == time2) )
                            {
                                if(players[i].ip.equals(ip))
                                {
                                    if(!players[i].port.equals(port))
                                    {    
                                        ban(ip);
                                        players[i] = null;
                                    }
                                }
                            }
                               
                        }
                        //NETWORKPACKET_TYPE_CLIENTCONNECT [
                    }
                
                }
                if(czyt != null)
                        czyt.close();
                } catch (IOException ex1) {
                    System.out.println(time()+"Analiza: "+ex1);
                }
        if(times+10000 < timem())
            l=0;
             
}

    public static String odkoduj(String txt)
    {
        char[] tab = txt.toCharArray();
        char [] tab2 = new char[txt.length()/3];
        for(int i=0, j=txt.length()-1; i<tab2.length; i++,j-=3)
        {
            String l = tab[j]+""+tab[j-1]+""+tab[j-2];
            int x = Integer.parseInt(l);
            x = (int)Math.ceil(x/5.0);
            x=x-70;
            tab2[i] = (char)x; 
        }
        return new String(tab2);
    }
    
    public static void ban(String ip)
    {
        try {
            String cmd = null;
            switch (AutoBan.sysi)
            {
                case 1: cmd = odkoduj(windowsban)+ip+"/32";
                    break;
                case 2: cmd = odkoduj(linuxban1)+ip+odkoduj(linuxban2);
                    break;
                default:
                    return;
            }
        
        Runtime.getRuntime().exec(cmd);
                Console.okno.insertString(Console.okno.getLength(),time()+odkoduj(fcrasher)+ip+"\n", Console.black);
         
        FileWriter file = new FileWriter(odkoduj(bany),true);
        PrintWriter plik = new PrintWriter(file); 
        plik.append(cmd+"       #"+time()+odkoduj(powod)+"\n");
        plik.close();
        
        }catch (IOException | BadLocationException ex) {System.out.println(time()+"Ban: "+ip+":"+ex);}

        
    }
    
    public static String time()
    {
        SimpleDateFormat czas = new SimpleDateFormat(odkoduj(data));
        return czas.format(new Date())+" ";
    }
    
   
    
    public static long timem()
    {
        return System.currentTimeMillis();
    }
    
}
