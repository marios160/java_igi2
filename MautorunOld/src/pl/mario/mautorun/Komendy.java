/*
 *wyciaganie komend z igi2.exe
 */
package pl.mario.mautorun;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Mateusz
 */
public class Komendy {
    
static public String	rcon	;
static public String	rconpass;
static public String	finger	;
static public String	kick	;
static public String	votekick;
static public String	ban	;
static public String	listban	;
static public String	unban	;
static public String	suicide	;
static public String	restartmap;
static public String	listmaps;
static public String	activatemap;
static public String	deactivatemap;
static public String	gotonext;
static public String	gotomap	;
static public String	votemap	;
static public String	votenext;
static public String	showvotes;
static public String	addmoney;
static public String	accounts;
static public String	monitor	;
static public String	ploss	;
static public String	timeout	;
static public String	bandwidth;
static public String	choke	;
static public String	fillpercent;
static public String	smooth	;
static public String	svname	;
static public String	svpassword;
static public String	svport;
static public String	svinterface;
static public String	maxplayers;
static public String	specmode;
static public String	spawncost;
static public String	spawntimer;
static public String	teamdamage;
static public String	warmup;
static public String	publicc;
static public String	bombtime;
static public String	autobalance;
static public String	autokick;
static public String	announce;
static public String	forcefirstspec;
static public String	bombrepostime;
static public String	pingmax;
static public String	plossmax;
static public String	idlemax;
static public String	goutmax;
static public String	moneystart;
static public String	moneycap;
static public String	moneykill;
static public String	moneyteamkill;
static public String	moneyplayerobjwin;
static public String	moneyteamobjwin;
static public String	moneyteamobjlost;
static public String	moneymissionwin;
static public String	moneymissionlost;
static public String	maprounds;
static public String	maptime;
static public String	mapteamscore;
static public String	playername;
static public String	spawnsafetimer ;
static public String	allowsniperrifles;
static public String	objtime;
static public String    clport;
    
    public static void wyciagnij()
    {
    try {
        FileReader fileReader = null;
        
        fileReader = new FileReader("igi2.exe");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linia = null;
        do {
            linia = bufferedReader.readLine();
            
            if (linia != null && linia.indexOf("Ban:Invalid parameters") > 0)
            {
                announce = linia.substring(395,403);
                finger = linia.substring(407,413);
                showvotes = linia.substring(415,424);
                monitor = linia.substring(451,458);
                rcon = linia.substring(479,483);
                suicide = linia.substring(487,494);
                listban = linia.substring(495,502);
                unban = linia.substring(523,528);
                ban = linia.substring(555,558);
                votekick = linia.substring(583,591);
                kick = linia.substring(627,631);
                restartmap = linia.substring(659,669);
                listmaps = linia.substring(695,703);
                votenext = linia.substring(727,735);
                votemap = linia.substring(759,766);
                deactivatemap = linia.substring(787,800);
                activatemap = linia.substring(831,842);
                gotomap = linia.substring(923,930);
                gotonext = linia.substring(951,959);
            }
            else
                if (linia != null &&linia.indexOf("IGI2 NETWORK CONFIG FILE") > 0)
                {
                    int poz = linia.indexOf("IGI2 NETWORK CONFIG FILE");
                    int poz2 = linia.indexOf("Not authorized");
                    
                    goutmax = linia.substring(poz2+16,poz2+23);
                    idlemax = linia.substring(poz2+24,poz2+31);
                    plossmax = linia.substring(poz2+32,poz2+40);
                    pingmax = linia.substring(poz2+44,poz2+51);
                    forcefirstspec = linia.substring(poz2+52,poz2+66);
                    bombrepostime = linia.substring(poz2+68,poz2+81);
                    playername = linia.substring(poz2+84,poz2+94);
                    ploss = linia.substring(poz2+96,poz2+101);
                    autokick = linia.substring(poz2+104,poz2+112);
                    timeout = linia.substring(poz2+116,poz2+123);
                    fillpercent = linia.substring(poz2+124,poz2+135);
                    choke = linia.substring(poz2+136,poz2+141);
                    bandwidth = linia.substring(poz2+144,poz2+153);
                    smooth = linia.substring(poz2+304,poz2+310);
                    allowsniperrifles = linia.substring(poz2+312,poz2+329);
                    spawnsafetimer = linia.substring(poz2+332,poz2+346);
                    spawntimer = linia.substring(poz2+348,poz2+358);
                    spawncost = linia.substring(poz2+360,poz2+369);
                    bombtime = linia.substring(poz2+372,poz2+380);
                    objtime = linia.substring(poz2+384,poz2+391);
                    mapteamscore = linia.substring(poz2+392,poz2+404);
                    maptime = linia.substring(poz2+496,poz2+503);
                    maprounds = linia.substring(poz2+576,poz2+585);
                    moneymissionlost = linia.substring(poz2+684,poz2+700);
                    moneymissionwin = linia.substring(poz2+704,poz2+719);
                    moneyteamobjlost = linia.substring(poz2+720,poz2+736);
                    moneyteamobjwin = linia.substring(poz2+740,poz2+755);
                    moneyplayerobjwin = linia.substring(poz2+756,poz2+773);
                    moneyteamkill = linia.substring(poz2+776,poz2+789);
                    moneykill = linia.substring(poz2+792,poz2+801);
                    moneycap = linia.substring(poz2+804,poz2+812);
                    moneystart = linia.substring(poz2+816,poz2+826);
                    publicc = linia.substring(poz2+1120,poz2+1126);
                    warmup = linia.substring(poz2+1128,poz2+1134);
                    autobalance = linia.substring(poz2+1136,poz2+1147);
                    teamdamage = linia.substring(poz2+1148,poz2+1158);
                    specmode = linia.substring(poz2+1160,poz2+1168);
                    maxplayers = linia.substring(poz2+1172,poz2+1182);
                    clport = linia.substring(poz2+1184,poz2+1190);
                    svinterface = linia.substring(poz-758,poz-747);
                    svport = linia.substring(poz-662,poz-656);
                    rconpass = linia.substring(poz-562,poz-554);
                    svpassword = linia.substring(poz-486,poz-476);
                    svname = linia.substring(poz-398,poz-392);
                    
                }
                else
                    if (linia != null && linia.indexOf("Netshop_AMMOSLOT_MEDIPACK") > 0)
                    {
                        int poz = linia.indexOf("Netshop_AMMOSLOT_MEDIPACK");
                        accounts = linia.substring(poz-20,poz-12);
                        addmoney = linia.substring(poz+112,poz+120);
                        
                    }
            
        } while(linia != null);
        bufferedReader.close();
        
    } catch (FileNotFoundException ex) {Loggs.loguj("Komendy-wyciagnij", ex.toString());
    } catch (IOException ex) { Loggs.loguj("Komendy-wyciagnij", ex.toString());}


    }
    
    
}
