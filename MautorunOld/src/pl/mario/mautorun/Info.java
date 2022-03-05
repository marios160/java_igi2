
package pl.mario.mautorun;


/**
 * Klasa przechowująca dane ze statusu serwera
 * @author Mario PL
 */
public class Info implements Runnable{
     static String status;
     static String hostname;
     static int hostport;
     static String mapname;
     static int numplayers;
     static int maxplayers;
     static int uptime;
     static String timeleft;
     static boolean password;
     static int scoreIGI;
     static int scoreCons;
     static Player[] players;
     static Player[] oldPlayers;
     static Player[] banPlayers;
     static int crash;
     static int odwiedziny;
     static int licz,liczb;
     static String[] listMaps;
     static String[] listIdMaps;
     
    public Info() {
        
        this.status = "";
        this.hostname = "IGI2 Server [igi2.xaa.pl]";
        this.hostport = 26001;
        this.mapname = "";
        this.numplayers = 0;
        this.maxplayers = 0;
        this.uptime = 0;
        this.timeleft = "00:00";
        this.password = false;
        this.scoreIGI = 0;
        this.scoreCons = 0;
        players = new Player[35];
        oldPlayers = new Player[5];
        banPlayers = new Player[3];
        this.crash = crash;
        this.odwiedziny = 0;
        this.licz = 0;
        this.liczb = 0;
        while(!Pakiety.sendListMaps());
        Gui.map_button.setModel(new javax.swing.DefaultComboBoxModel(Info.listMaps));
        updateInfo();
    }

    @Override
    public void run() {
       while(true)
       {    
           if(!Glowna.crash)
               return;
           updateInfo();
           try {Thread.sleep(1000);
           } catch (InterruptedException ex) {Loggs.loguj("Info-run", ex.toString());}
       }
       
    }
    
    public static void addBanPlayers(Player p) {
        if (liczb > 2)
            liczb = 0;
        banPlayers[liczb] = p;
        liczb++;
    }
    
    public static void delPlayers(int id)
    {
        if(licz > 4)
            licz = 0;
        oldPlayers[licz] = players[id];
        players[id] = null;
        licz++;
    }
    
    public static void addPlayer(Player p)
    {
        players[Integer.parseInt(p.getId())] = p;
    }
    
    
    public static void updateInfo()
    {
        String pom;
        CrashBar bar = new CrashBar();
        Pakiety pakiet = new Pakiety();
        status = "";
        status = pakiet.sendStatus();
        int i=0;
        if(status.isEmpty())
            bar.start();
 
        while(i <= 2 && status.isEmpty()){
            
            status = pakiet.sendStatus();
            i++;
        }
        if(status.isEmpty())
        {    
                Glowna.crash = true;
                return;
        } 
        CrashBar.var = false;
        Gui.crashbar.setValue(0);           
            
        try
        {
        pom = status.substring(status.indexOf("hostname\\")+9,status.indexOf("\\hostport"));
        hostname = pom;
        Gui.name_server.setText(hostname);
        pom = status.substring(status.indexOf("hostport\\")+9,status.indexOf("\\mapname"));
        hostport = Integer.parseInt(pom);
        pom = status.substring(status.indexOf("mapname\\")+8,status.indexOf("\\gametype"));
        mapname = pom;
        Gui.map_button.setSelectedItem(mapname);
        pom = status.substring(status.indexOf("numplayers\\")+11,status.indexOf("\\maxplayers"));
        numplayers = Integer.parseInt(pom);
        Gui.current_players.setText(pom);
        pom = status.substring(status.indexOf("maxplayers\\")+11,status.indexOf("\\gamemode"));
        maxplayers = Integer.parseInt(pom);
        
        Gui.maxplayers.setSelectedIndex(maxplayers);
        pom = status.substring(status.indexOf("uptime\\")+7,status.indexOf("\\timeleft"));
        uptime = Integer.parseInt(pom);
        pom = uptime/60+"h "+uptime%60+"min";
        Gui.uptimeVal.setText(pom);
        pom = status.substring(status.indexOf("timeleft\\")+9,status.indexOf("\\mapstat"));
        timeleft = pom;
        Gui.time_button.setText(pom);
        pom = status.substring(status.indexOf("password\\")+9,status.indexOf("\\team_t0"));
        password = Boolean.valueOf(pom);
        if(password)
        {
            Gui.passwdVal.setForeground(new java.awt.Color(255, 0, 0));
            Gui.passwdVal.setText("Yes");
        }
        else
        {
            Gui.passwdVal.setForeground(new java.awt.Color(0, 153, 51));
            Gui.passwdVal.setText("No");
        }
        pom = status.substring(status.indexOf("score_t0\\")+9,status.indexOf("\\score_t1"));
        scoreIGI = Integer.parseInt(pom);
        pom = status.substring(status.indexOf("score_t1\\")+9);
        pom = pom.substring(0,pom.indexOf("\\"));
        scoreCons = Integer.parseInt(pom);
        Gui.visitVal.setText(odwiedziny+"");
        Gui.crashVal.setText(crash+"");
        
            updatePlayers(status);
            updateTable();
        }
        catch(StringIndexOutOfBoundsException ex)
        {
            Loggs.loguj("Info-updateInfo", ex.toString());    
        }
        
    }
    
    static void updatePlayers(String status)
    {
        String pom;
        for(Player zm: players)
        {
            if(zm != null)
            {
                try
                {
                if (numplayers < 14)
                {
                    pom = status.substring(status.indexOf(zm.sfrag)+zm.sfrag.length(),status.indexOf(zm.sdeath)-1);
                    zm.setFrags(Integer.parseInt(pom));
                    pom = status.substring(status.indexOf(zm.sdeath)+zm.sdeath.length(),status.indexOf(zm.sping)-1);
                    zm.setDeaths(Integer.parseInt(pom));
                }
                else
                {
                    zm.setFrags(0);
                    zm.setDeaths(0);
                }
                pom = status.substring(status.indexOf(zm.sping)+zm.sping.length(),status.indexOf(zm.steam)-1);
                zm.setPing(Integer.parseInt(pom));
                pom = status.substring(status.indexOf(zm.steam)+zm.steam.length(),status.indexOf(zm.steam)+zm.steam.length()+1);
                zm.setTeam(Integer.parseInt(pom));
                }catch(StringIndexOutOfBoundsException ex)
                {
                    Loggs.loguj("Info-updatePlayers", ex.toString());
                    return;
                }
            }
        }
    }
    
    static void updateTable()   
    {
        int igi = 0;
        int cons = 0;
        for(int i=0; i<32; i++)
        {
                    Gui.igiTab.setValueAt(null,i, 0);
                    Gui.igiTab.setValueAt(null,i, 1);
                    Gui.igiTab.setValueAt(null,i, 2);
                    Gui.igiTab.setValueAt(null,i, 3);
                    Gui.igiTab.setValueAt(null,i, 4);
                    Gui.consTab.setValueAt(null,i, 0);
                    Gui.consTab.setValueAt(null,i, 1);
                    Gui.consTab.setValueAt(null,i, 2);
                    Gui.consTab.setValueAt(null,i, 3);
                    Gui.consTab.setValueAt(null,i, 4);
                   
                  
        }
        for(Player zm: players)
        {
            if(zm != null)
            {
                if(zm.getTeam() == 0)
                {
                    Gui.igiTab.setValueAt(zm.getId(),igi, 0);
                    Gui.igiTab.setValueAt(zm.getNick(),igi, 1);
                    Gui.igiTab.setValueAt(zm.getFrags()+"/"+zm.getDeaths(),igi, 2);
                    Gui.igiTab.setValueAt(zm.getIp(),igi, 3);
                    Gui.igiTab.setValueAt(zm.getPing(),igi, 4);
                    igi++;
                }
                else
                {
                    Gui.consTab.setValueAt(zm.getId(),cons, 0);
                    Gui.consTab.setValueAt(zm.getNick(),cons, 1);
                    Gui.consTab.setValueAt(zm.getFrags()+"/"+zm.getDeaths(),cons, 2);
                    Gui.consTab.setValueAt(zm.getIp(),cons, 3);
                    Gui.consTab.setValueAt(zm.getPing(),cons, 4);
                    cons++;
                }
            }     
        }

    }
    
    /**
     * 
     * @param nick
     * @return 
     */
    public static boolean find(String nick)
    {      
        for(Player zm : players)
        {
            if (zm != null && zm.getNick().equals(nick))
            {
               return true;
            }
        }
        return false;
    }

    
}
