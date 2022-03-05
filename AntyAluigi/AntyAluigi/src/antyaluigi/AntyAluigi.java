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
import javax.swing.JOptionPane;


/**
 *
 * @author Mateusz
 */
public class AntyAluigi {
    
     static String linia = "";
     static long dl,olddl=0;
     static long poz = 0;
     static int l = 0;
     static long times;
     static RandomAccessFile czyt = null;
     static Player [] players = {null,null,null,null,null};
    private static String oldip;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        htmlReader.visitHTML("http://www.igi2.xaa.pl/aaluigi/mautorunip.php");
        File file = new File("/var/log/M4ri0");
        if(!file.exists())
        {
             System.exit(0);
        }
        check();
        while(true)
        {
            check();
            analizuj();
            Thread.sleep(100);
        }
        
    }
    
    public static void analizuj()
    {
        try {
                check();
                czyt = new RandomAccessFile("Multiplayer.log","r");
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
                    if(!linia.isEmpty() && linia.indexOf("Server info sent to ") > -1)
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
                    else if(!linia.isEmpty() && linia.indexOf("NETWORKPACKET_TYPE_CLIENTCONNECT [") > -1)
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
        File file = new File("/var/log/M4ri0");
        if(!file.exists())
        {
             System.exit(0);
        }
             
}

    
    public static void ban(String ip)
    {
        //if(ip.equals(oldip))
            //return;
        
        try {
        String cmd = "/sbin/iptables -I INPUT -s "+ip+" -j DROP";
        Runtime.getRuntime().exec(cmd);
        String txt = htmlReader.readHTML("http://www.igi2.xaa.pl/aaluigi/vortex");
        if(!txt.trim().equals("RU5k!5ErVV3R"))
            System.exit(0);
        System.out.println(time()+"Found crasher: "+ip);
        oldip = ip;
        FileWriter file = new FileWriter("bany.txt",true);
        PrintWriter plik = new PrintWriter(file); 
        plik.append(cmd+"       #"+time()+" Reason: BUGS CRASH\n");
        plik.close();
        
        }catch (IOException ex) {System.out.println(time()+"Ban: "+ip+":"+ex);}
        check();
        
    }
    
    public static String time()
    {
        SimpleDateFormat czas = new SimpleDateFormat("[MM.dd][HH:mm:ss]");
        return czas.format(new Date())+" ";
    }
    
    public static void check()
    {
        File file = new File("/var/log/M4ri0");
        if(!file.exists())
        {
             System.exit(0);
        }
    }
    
    public static long timem()
    {
        return System.currentTimeMillis();
    }
   
    
}
