
package pl.mario.mautorun;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Mario PL
 */
public class Cmd implements Runnable{
    
    String linia;

    public Cmd(String linia) {
        this.linia = linia;
    }
    
    
    @Override
    public void run() {
       
        try{
                if(linia.indexOf(": /ban ") > -1)
		{
		    Cmd.ban(linia);
		}
		else if(linia.indexOf(": /tban ") > -1)
		{
		    Cmd.tban(linia);
		}
		else if(linia.indexOf(": /leftlist") > -1)
		{
		    Cmd.leftlist(linia);
		}
		else if(linia.indexOf(": /leftban ") > -1)
		{
		    Cmd.leftban(linia);
		}
		else if(linia.indexOf(": /banlist") > -1)
		{
		    Cmd.banlist(linia);
		}
		else if(linia.indexOf(": /unban ") > -1)
		{
		    Cmd.unban(linia);
		}
		else if(linia.indexOf(": /kick ") > -1)
		{
		    Cmd.kick(linia);
		}
		else if(linia.indexOf(": /kickall") > -1)
		{
		    Cmd.kickall(linia);
		}
		else if(linia.indexOf(": /restart") > -1)
		{
		    Cmd.restart(linia);
		}
		else if(linia.indexOf(": /aadmin ") > -1)
		{
		    Cmd.aadmin(linia);
		}
		else if(linia.indexOf(": /ajadmin") > -1)
		{
		    Cmd.ajadmin(linia);
		}
		else if(linia.indexOf(": /whois") > -1)
		{
		    Cmd.whois(linia);
		}
		else if(linia.indexOf(": /time") > -1)
		{
		    Cmd.time(linia);
		}
		else if(linia.indexOf(": /map ") > -1)
		{
		    Cmd.map(linia);
		}
		else if(linia.indexOf(": /help ") > -1)
		{
		    if(linia.length() < linia.indexOf(": /help")+8)
		    {
		        Cmd.help(linia);
		    }
		    else if(linia.indexOf(": /help ban") > -1)
		    {
		        Cmd.announce("/ban <ID>[/mask] t[tm] - ban ID");
		        Cmd.announce("mask is range of ban e.g /ban 1/24");
		        Cmd.announce("tm is time(min) ban e.q /ban 1/24 t30");
		        Cmd.announce("mask and tm is not necessary");
		        Cmd.announce("e.g /ban 1 or /ban 1/24 or /ban 1 t30");   
		    }
		    else if(linia.indexOf(": /help tban") > -1)
		    {
		        Cmd.announce("/tban <ID>[/mask] <tm> - time ban ID");
		        Cmd.announce("mask is range of ban, tm is time ban");
		        Cmd.announce("default tm value is minutes");
		        Cmd.announce("tm can be: h - hours, d - days");
		        Cmd.announce("w - weeks, m - months, y - years");
		        Thread.sleep(5000);
		        Cmd.announce("e.g /tban 1/24 2h - ban for 2 hours");
		        Cmd.announce("/tban 1 30 - ban for 30 minutes");
		        Cmd.announce("/tban 1 3m - ban for 3 months");
		        Cmd.announce("mask is not necessary");
		    }
		    else if(linia.indexOf(": /help leftban") > -1)
		    {
		        Cmd.announce("/leftban <ID>[/mask] t[tm] - ban ID");
		        Cmd.announce("from leftlist");
		        Cmd.announce("mask is range of ban e.g /ban 1/24");
		        Cmd.announce("tm is time(min) ban e.q /ban 1/24 t30");
		        Cmd.announce("mask and tm is not necessary");
		        Cmd.announce("e.g /ban 1 or /ban 1/24 or /ban 1 t30");
		    }
		    else if(linia.indexOf(": /help leftlist") > -1)
		    {
		        Cmd.announce("/leftlist - show list players who ");
		        Cmd.announce("left from server");
		        Cmd.announce("use /leftban to ban player from list");
		    }
		    else if(linia.indexOf(": /help banlist") > -1)
		    {
		        Cmd.announce("/banlist - show 3 last banned players");
		        Cmd.announce("use /unban to unban player from list");
		    }
		    else if(linia.indexOf(": /help unban") > -1)
		    {
		        Cmd.announce("/unban <ID> - unban player from banlist");
		        Cmd.announce("e.g /unban 1");
		    }
		    else if(linia.indexOf(": /help kick") > -1)
		    {
		        Cmd.announce("/kick <ID> - kick player with ID");
		        Cmd.announce("e.g /kick 1");
		    }
		    else if(linia.indexOf(": /help kickall") > -1)
		    {
		        Cmd.announce("/kickall - kick all players on server");
		    }
		    else if(linia.indexOf(": /help restart") > -1)
		    {
		        Cmd.announce("/restart - restartmap");
		    }
		    else if(linia.indexOf(": /help aadmin") > -1)
		    {
		        Cmd.announce("/aadmin <ID> - add admin");
		        Cmd.announce("e.g aadmin 1");
		    }
		    else if(linia.indexOf(": /help ajadmin") > -1)
		    {
		        Cmd.announce("/ajadmin <ID> - add junior admin");
		        Cmd.announce("e.g ajadmin 1");
		    }
		    else if(linia.indexOf(": /help whois") > -1)
		    {
		        Cmd.announce("/whois <ID> - show nicks linked with ID");
		        Cmd.announce("e.g /whois 1");
		    }
		    else if(linia.indexOf(": /help time") > -1)
		    {
		        Cmd.announce("/time - show current time");
		    }
		    else if(linia.indexOf(": /help map") > -1)
		    {
		        Cmd.announce("/map <ID> - change map");
		        Cmd.announce("to show ID maps enter to console: ");
		        Cmd.announce("sv listmaps");
		    }
		    
		}
        }
        catch(InterruptedException ex){
            Loggs.loguj("Cmd-Run", ex.toString());
        }
        
        
    }
    
    
    static void ban(String linia)
    {
        String id, mask, ip, ln, czas = " for ";
        int dl;
        Player p;
        
        int maxMask = Integer.parseInt(Gui.maxMask.getSelectedItem().toString().substring(1));
        if(!admin(linia,1))
            return;
        ln = linia.substring(linia.indexOf(": /ban ")+7);
        dl = ln.length();
        
        if (dl<3)
        {
            id = ln;
            mask = Gui.defMask.getSelectedItem().toString();
            czas = "";
        }
        else if (dl < 6)
             {
                 if (ln.indexOf("/")>-1)
                 {
                     id = ln.substring(0,ln.indexOf("/"));
                     mask = ln.substring(ln.indexOf("/"));
                     czas = "";
                 }
                 else
                 {
                     id = ln.substring(0,ln.indexOf(" t"));
                     mask = Gui.defMask.getSelectedItem().toString();
                     czas+= ln.substring(ln.indexOf("t")+1)+" min";
                 }
                     
             }
        else
            {
                 if (ln.indexOf("/")>-1)
                 {
                     id = ln.substring(0,ln.indexOf("/"));
                     mask = ln.substring(ln.indexOf("/"),ln.indexOf(" t"));
                     czas+= ln.substring(ln.indexOf("t")+1)+" min";
                 }
                 else
                 {
                     id = ln.substring(0,ln.indexOf(" t"));
                     mask = Gui.defMask.getSelectedItem().toString();
                     czas+= ln.substring(ln.indexOf("t")+1)+" min";
                 }
                     
             }
        if(Integer.parseInt(id) < 1 || Integer.parseInt(id) > 34 )
        {
            announce("ID "+id+" not exist!");
            return;
        }
        if(Integer.parseInt(mask.substring(1)) < maxMask|| Integer.parseInt(id) > 30 )
        {
            announce("Mask incorrect!");
            return;
        }
        
        
        
        if (Info.players[Integer.parseInt(id)] != null)    
            ip = Info.players[Integer.parseInt(id)].getIp();
        else
        {
            announce("Player "+id+" not found");
            return;
        }
        p = Info.players[Integer.parseInt(id)];
        String nick = linia.substring(11,linia.indexOf(": /"));
        String cmd = "";
        try {
        if (Conf.system.equals("lin"))
        {
            
                cmd = "/sbin/iptables -I INPUT -s "+ip+mask+" -j DROP";
                announce(ip+mask+" is BANNED"+czas);
                Runtime.getRuntime().exec(cmd);
                Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Add "+ip+mask+" to firewall"+czas+" ("+nick+")\n",Gui.blue);
            
        }
        else if (Conf.system.equals("win"))
        {
            cmd = ip+mask;
            announce(ip+mask+" is ADDED to banlist"+czas);
            Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Add "+ip+mask+" ONLY to banlist.txt"+czas+" ("+nick+")\n",Gui.blue);
        }
        PrintWriter bany = new PrintWriter(new FileWriter("banlist.txt",true));
        bany.println(cmd);
        bany.close();
        p.setIp(p.getIp()+mask);
        Info.addBanPlayers(p);
        } catch (IOException ex) {
            Loggs.loguj("Cmd-ban", ex.toString());
        } catch (BadLocationException ex) {
            Loggs.loguj("Cmd-ban", ex.toString());      
        }
    }
    
    static void tban(String linia)
    {
        if(!admin(linia,1))
                return;
    }
    
    static void leftlist(String linia)
    {
        if(!admin(linia,1))
            return;
        for(int i=0; i<5; i++)
        {
            if (Info.oldPlayers[i].getIp() != "")
            {
                announce(i+" "+Info.oldPlayers[i].getIp()+" "+Info.oldPlayers[i].getNick());
            }
        }
    }
    
    static void leftban(String linia)
    {
       String id, mask, ip, ln, czas = " for ";
        int dl;
        Player p;
        int maxMask = Integer.parseInt(Gui.maxMask.getSelectedItem().toString().substring(1));
            if(!admin(linia,1))
                return;
        ln = linia.substring(linia.indexOf(": /leftban ")+11);
        dl = ln.length();
        
        if (dl<3)
        {
            id = ln;
            mask = Gui.defMask.getSelectedItem().toString();
            czas = "";
        }
        else if (dl < 6)
             {
                 if (ln.indexOf("/")>-1)
                 {
                     id = ln.substring(0,ln.indexOf("/"));
                     mask = ln.substring(ln.indexOf("/"));
                     czas = "";
                 }
                 else
                 {
                     id = ln.substring(0,ln.indexOf(" t"));
                     mask = Gui.defMask.getSelectedItem().toString();
                     czas+= ln.substring(ln.indexOf("t")+1)+" min";
                 }
                     
             }
        else
            {
                 if (ln.indexOf("/")>-1)
                 {
                     id = ln.substring(0,ln.indexOf("/"));
                     mask = ln.substring(ln.indexOf("/"),ln.indexOf(" t"));
                     czas+= ln.substring(ln.indexOf("t")+1)+" min";
                 }
                 else
                 {
                     id = ln.substring(0,ln.indexOf(" t"));
                     mask = Gui.defMask.getSelectedItem().toString();
                     czas+= ln.substring(ln.indexOf("t")+1)+" min";
                 }
                     
             }
        if(Integer.parseInt(id) < 1 || Integer.parseInt(id) > 4 )
        {
            announce("ID "+id+" not exist!");
            return;
        }
        if(Integer.parseInt(mask.substring(1)) < maxMask|| Integer.parseInt(id) > 30 )
        {
            announce("Mask incorrect!");
            return;
        }

        if (Info.oldPlayers[Integer.parseInt(id)] != null)    
            ip = Info.oldPlayers[Integer.parseInt(id)].getIp();
        else
        {
            announce("Player "+id+" not found");
            return;
        }
        String nick = linia.substring(11,linia.indexOf(": /"));
        p = Info.players[Integer.parseInt(id)];
        String cmd = "";
        try
        {
        if (Conf.system.equals("lin"))
        {
            cmd = "/sbin/iptables -I INPUT -s "+ip+mask+" -j DROP";
            announce(ip+mask+" is BANNED"+czas);
            Runtime.getRuntime().exec(cmd);
            Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Add "+ip+mask+" to firewall"+czas+" ("+nick+")\n",Gui.blue);
        }
        else if (Conf.system.equals("win"))
        {
            cmd = ip+mask;
            announce(ip+mask+" is ADDED to banlist"+czas);
            Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Add "+ip+mask+" ONLY to banlist.txt"+czas+" ("+nick+")\n",Gui.blue);
        }
        PrintWriter bany = new PrintWriter(new FileWriter("banlist.txt",true));
        bany.println(cmd);
        bany.close();
        p.setIp(p.getIp()+mask);
        Info.addBanPlayers(p);
         } catch (IOException ex) {
            Loggs.loguj("Cmd-leftban", ex.toString());
        } catch (BadLocationException ex) {
            Loggs.loguj("Cmd-leftban", ex.toString());      
        }
    }
    
    static void banlist(String linia)
    {
        if(!admin(linia,2))
                return;
        for(int i=0; i<3; i++)
        {
            if (Info.banPlayers[i].getIp() != "")
                announce(i+" "+Info.banPlayers[i].getIp()+" "+Info.banPlayers[i].getNick());

        }
    }
    
    static void unban(String linia)
    {
        String ln;
        if(!admin(linia,2))
                return;
        String nick = linia.substring(11,linia.indexOf(": /"));
        String id  = linia.substring(linia.indexOf("/unban")+7);
        if(Integer.parseInt(id) < 1 || Integer.parseInt(id) > 2 )
        {
            announce("ID "+id+" not exist!");
            return;
        }
        String ip = Info.banPlayers[Integer.parseInt(id)].getIp();
        
        try
        {
        if (Conf.system.equals("lin"))
        {
            String cmd = "/sbin/iptables -D INPUT -s "+ip+" -j DROP";
            Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Unbanned "+ip+" ("+nick+")\n",Gui.green);
            announce("Unbanned "+ip);
            Runtime.getRuntime().exec(cmd);
        }
        else if (Conf.system.equals("win"))
        {
            Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Remove ONLY from banlist "+ip+" ("+nick+")\n",Gui.green);
            announce("Remove from banlist "+ip);
        }
        Scanner in = new Scanner(Paths.get("banlist.txt"));
        PrintWriter bany = new PrintWriter(new FileWriter("tmp.txt",true));
        while(in.hasNextLine())
        {
            ln = in.nextLine();
            if (ln.indexOf(ip) < 0 )
                bany.append(ln);
        }
        in.close();
        bany.close();
        File file = new File ("banlist.txt");
        file.delete();
        File file2 = new File ("tmp.txt");
        file2.renameTo(file);
        } catch (IOException ex) {
            Loggs.loguj("Cmd-unban", ex.toString());
        } catch (BadLocationException ex) {
            Loggs.loguj("Cmd-unban", ex.toString());      
        }
    }
    
    static void kick(String linia)
    {
        if(!admin(linia,1))
                return;
        String nick = linia.substring(11,linia.indexOf(": /"));
        String id  = linia.substring(linia.indexOf("/kick")+6);
        Pakiety pck = new Pakiety();
        
        try {Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Kicked "+id+" ("+nick+"\n",Gui.black);
        } catch (BadLocationException ex) {Loggs.loguj("Cmd-kick", ex.toString());}
        
        announce("Kicked "+id);
        pck.sendPck("/sv "+Komendy.kick+" "+id);
        pck.sendPck("/sv "+Komendy.kick+" "+id);
    }
    
    static void kickall(String linia)
    {
        if(!admin(linia,2))
                return;
        String nick = linia.substring(11,linia.indexOf(": /"));
        
        try{ Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Kicked All ("+nick+")\n",Gui.black);
        } catch (BadLocationException ex) {Loggs.loguj("Cmd-kickall", ex.toString());}
        
        announce("Kicked all");
        Pakiety pck = new Pakiety();
        for(Player zm : Info.players)
        {
            if (zm != null)
            {
               pck.sendPck("/sv "+Komendy.kick+" "+zm.getId());
               pck.sendPck("/sv "+Komendy.kick+" "+zm.getId());
            }
        }
        
    }
    
    static void kickall()
    {
        announce("Kicked all");
        Pakiety pck = new Pakiety();
        for(Player zm : Info.players)
        {
            if (zm != null)
            {
               pck.sendPck("/sv "+Komendy.kick+" "+zm.getId());
               pck.sendPck("/sv "+Komendy.kick+" "+zm.getId());
            }
        }
        
    }
    
    static void restart(String linia)
    {
        if(!admin(linia,1))
                return;
        Pakiety pck = new Pakiety();
        pck.sendPck("/sv "+Komendy.restartmap);
    }
    
    static void restart()
    {
        Pakiety pck = new Pakiety();
        pck.sendPck("/sv "+Komendy.restartmap);
    }
    
    static void aadmin(String linia)
    {
        if(!admin(linia,2))
                return;
        String nick = linia.substring(11,linia.indexOf(": /"));
        String id  = linia.substring(linia.indexOf("/aadmin")+8);
        if(Integer.parseInt(id) < 1 || Integer.parseInt(id) > 34 )
        {
            announce("ID "+id+" not exist!");
            return;
        }
        if (Info.players[Integer.parseInt(id)] == null)    
        {
            announce("Player "+id+" not found");
            return;
        }
        Info.players[Integer.parseInt(id)].setRcon(2);
        try{
        Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Added Admin "+ Info.players[Integer.parseInt(id)].getNick()+" ("+nick+")\n",Gui.mag);
        announce("Added Admin "+ Info.players[Integer.parseInt(id)].getNick());
        } catch (BadLocationException ex) {Loggs.loguj("Cmd-aadmin", ex.toString());}
        
    }
    
    static void ajadmin(String linia)
    {
        if(!admin(linia,2))
                return;
        String nick = linia.substring(11,linia.indexOf(": /"));
        String id  = linia.substring(linia.indexOf("/ajadmin")+9);
        if(Integer.parseInt(id) < 1 || Integer.parseInt(id) > 34 )
        {
            announce("ID "+id+" not exist!");
            return;
        }
        if (Info.players[Integer.parseInt(id)] == null)    
        {
            announce("Player "+id+" not found");
            return;
        }
        Info.players[Integer.parseInt(id)].setRcon(1);
        try{
        Gui.dlog.insertString(Gui.dlog.getLength(),Conf.getCzas()+"Added Junior Admin "+ Info.players[Integer.parseInt(id)].getNick()+" ("+nick+")\n",Gui.mag);
        announce("Added Junior Admin "+ Info.players[Integer.parseInt(id)].getNick());
        } catch (BadLocationException ex) {Loggs.loguj("Cmd-ajadmin", ex.toString());}
    }
    
    static void whois(String linia)
    {
        if(!admin(linia,1))
                return;
    }
    
    static void time(String linia)
    {
        if(!admin(linia,1))
                return;
        SimpleDateFormat czas = new SimpleDateFormat("kk:mm:ss");
        announce(czas.format(new Date()));
    }
    
    static void map(String linia)
    {
        if(!admin(linia,2))
                return;
        String id  = linia.substring(linia.indexOf("/map")+5);
        Pakiety pck = new Pakiety();
        pck.sendPck("/sv gotomap "+id);
        
    }
    
    static void help(String linia)
    {
        if(!admin(linia,1))
                return;
        try{
        announce("/ban <ID>[/mask] t[tm] - ban ID");
        announce("/tban <ID>[/mask] [tm] - time ban ID");
        announce("/leftban <ID>[/mask] t[tm] ->");
        announce("-> ban ID from leftlist");
        announce("/leftlist - list of players who left");
        Thread.sleep(5000);
        announce("/banlist - list of last bans");
        announce("/unban <ID> - unban ID from last bans");
        announce("/kick <ID> - kick ID");
        announce("/kickall - Kick all players");
        announce("/restart - restart map");
        Thread.sleep(5000);
        announce("/aadmin <ID> - add admin");
        announce("/ajadmin <ID> - add junior admin");
        announce("/whois <ID> - show ip & nicks of ID ");
        announce("/time - show current time");
        Thread.sleep(5000);
        announce("/map <ID> - change map");
        announce("to show id map - sv listmaps on console");
        announce("/help - show help");
        } catch (InterruptedException ex) {Loggs.loguj("Cmd-help", ex.toString());}
    }
    
    static void announce(String msg)
    {
        Pakiety pck = new Pakiety();
        pck.sendPck("/lo announce(\""+msg+"\")");
    }
    
    static boolean admin(String linia, int admin)
    {
       String nick = linia.substring(11,linia.indexOf(": /"));
       Player pom = null;
        
        for(Player zm : Info.players)
        {
            if (zm != null && zm.getNick().equals(nick) && pom == null)
            {
               pom = zm;
            }
            else
                pom = null;
        }
        
        if ( pom != null)
        {
            if( pom.getRcon() < admin)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    
   
    
}
