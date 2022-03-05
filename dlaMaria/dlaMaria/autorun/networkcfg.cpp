#include "networkcfg.h"
#include <fstream>
#include <iostream>
#include <windows.h>
#include <string>
#include "funkcje_dodatkowe.cpp"
#include "wytnij.h"

using namespace std;

networkcfg::networkcfg()
{
    ifstream plik;
    string linia;
    plik.open("DATA\\ustawienia.nin");
    if (!plik)
        msgbox("Nie udalo sie otworzyc pliku 'ustawienia.nin'!");

    while(!plik.eof())
    {
        getline(plik,linia);
        if(linia.length()==0 || (linia[0]=='/' && linia[1]=='/')) continue;
        else if (find_pos(linia,"svname="))	svname = wytnij_nawiasy(trim(wytnij(linia,7)));
        else if (find_pos(linia,"svpassword="))	svpassword = wytnij_nawiasy(trim(wytnij(linia,11)));
        else if (find_pos(linia,"rconpass="))	rconpass = wytnij_nawiasy(trim(wytnij(linia,9)));
        else if (find_pos(linia,"svport="))	svport = atoi(wytnij(linia,7).c_str());
        else if (find_pos(linia,"svport_min="))	svport_min = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"svport_max="))	svport_max = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"svinterface="))	svinterface = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"svinterface_min="))	svinterface_min = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"svinterface_max="))	svinterface_max = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"clport="))	clport = atoi(wytnij(linia,7).c_str());
        else if (find_pos(linia,"clport_min="))	clport_min = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"clport_max="))	clport_max = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"maxplayers="))	maxplayers = atoi(wytnij(linia, 11).c_str());
        else if (find_pos(linia,"maxplayers_min="))	maxplayers_min = atoi(wytnij(linia, 15).c_str());
        else if (find_pos(linia,"maxplayers_max="))	maxplayers_max = atoi(wytnij(linia, 15).c_str());
        else if (find_pos(linia,"specmode="))	specmode = atoi(wytnij(linia,9).c_str());
        else if (find_pos(linia,"specmode_min="))	specmode_min = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"specmode_max="))	specmode_max = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"teamdamage="))	teamdamage = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"teamdamage_min="))	teamdamage_min = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"teamdamage_max="))	teamdamage_max = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"autobalance="))	autobalance = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"autobalance_min="))	autobalance_min = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"autobalance_max="))	autobalance_max = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"warmup="))	warmup = atoi(wytnij(linia,7).c_str());
        else if (find_pos(linia,"warmup_min="))	warmup_min = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"warmup_max="))	warmup_max = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"public="))	publicc = atoi(wytnij(linia,7).c_str());
        else if (find_pos(linia,"public_min="))	publicc_min = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"public_max="))	publicc_max = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"activatemap="))	activatemap = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"activatemap_min="))	activatemap_min = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"activatemap_max="))	activatemap_max = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"moneystart="))	moneystart = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"moneystart_min="))	moneystart_min = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"moneystart_max="))	moneystart_max = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"moneycap="))	moneycap = atoi(wytnij(linia,9).c_str());
        else if (find_pos(linia,"moneycap_min="))	moneycap_min = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"moneycap_max="))	moneycap_max = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"moneykill="))	moneykill = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"moneykill_min="))	moneykill_min = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"moneykill_max="))	moneykill_max = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"moneyteamkill="))	moneyteamkill = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"moneyteamkill_min="))	moneyteamkill_min = atoi(wytnij(linia,18).c_str());
        else if (find_pos(linia,"moneyteamkill_max="))	moneyteamkill_max = atoi(wytnij(linia,18).c_str());
        else if (find_pos(linia,"moneyplayerobjwin="))	moneyplayerobjwin = atoi(wytnij(linia,18).c_str());
        else if (find_pos(linia,"moneyplayerobjwin_min="))	moneyplayerobjwin_min = atoi(wytnij(linia,22).c_str());
        else if (find_pos(linia,"moneyplayerobjwin_max="))	moneyplayerobjwin_max = atoi(wytnij(linia,22).c_str());
        else if (find_pos(linia,"moneyteamobjwin="))	moneyteamobjwin = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"moneyteamobjwin_min="))	moneyteamobjwin_min = atoi(wytnij(linia,20).c_str());
        else if (find_pos(linia,"moneyteamobjwin_max="))	moneyteamobjwin_max = atoi(wytnij(linia,20).c_str());
        else if (find_pos(linia,"moneyteamobjlost="))	moneyteamobjlost = atoi(wytnij(linia,17).c_str());
        else if (find_pos(linia,"moneyteamobjlost_min="))	moneyteamobjlost_min = atoi(wytnij(linia,21).c_str());
        else if (find_pos(linia,"moneyteamobjlost_max="))	moneyteamobjlost_max = atoi(wytnij(linia,21).c_str());
        else if (find_pos(linia,"maprounds="))	maprounds = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"maprounds_min="))	maprounds_min = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"maprounds_max="))	maprounds_max = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"mapteamscore="))	mapteamscore = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"mapteamscore_min="))	mapteamscore_min = atoi(wytnij(linia,17).c_str());
        else if (find_pos(linia,"mapteamscore_max="))	mapteamscore_max = atoi(wytnij(linia,17).c_str());
        else if (find_pos(linia,"objtime="))	objtime = atoi(wytnij(linia,8).c_str());
        else if (find_pos(linia,"objtime_min="))	objtime_min = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"objtime_max="))	objtime_max = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"bombtime="))	bombtime = atoi(wytnij(linia,9).c_str());
        else if (find_pos(linia,"bombtime_min="))	bombtime_min = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"bombtime_max="))	bombtime_max = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"spawncost="))	spawncost = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"spawncost_min="))	spawncost_min = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"spawncost_max="))	spawncost_max = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"spawntimer="))	spawntimer = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"spawntimer_min="))	spawntimer_min = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"spawntimer_max="))	spawntimer_max = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"spawnsafetimer="))	spawnsafetimer = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"spawnsafetimer_min="))	spawnsafetimer_min = atoi(wytnij(linia,19).c_str());
        else if (find_pos(linia,"spawnsafetimer_max="))	spawnsafetimer_max = atoi(wytnij(linia,19).c_str());
        else if (find_pos(linia,"allowsniperrifles="))	allowsniperrifles = atoi(wytnij(linia,18).c_str());
        else if (find_pos(linia,"allowsniperrifles_min="))	allowsniperrifles_min = atoi(wytnij(linia,22).c_str());
        else if (find_pos(linia,"allowsniperrifles_max="))	allowsniperrifles_max = atoi(wytnij(linia,22).c_str());
        else if (find_pos(linia,"smooth="))	smooth = atoi(wytnij(linia,7).c_str());
        else if (find_pos(linia,"smooth_min="))	smooth_min = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"smooth_max="))	smooth_max = atoi(wytnij(linia,11).c_str());
        else if (find_pos(linia,"bandwidth="))	bandwidth = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"bandwidth_min="))	bandwidth_min = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"bandwidth_max="))	bandwidth_max = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"choke="))	choke = atoi(wytnij(linia,6).c_str());
        else if (find_pos(linia,"choke_min="))	choke_min = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"choke_max="))	choke_max = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"fillpercent="))	fillpercent = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"fillpercent_min="))	fillpercent_min = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"fillpercent_max="))	fillpercent_max = atoi(wytnij(linia,16).c_str());
        else if (find_pos(linia,"timeout="))	timeout = atoi(wytnij(linia,8).c_str());
        else if (find_pos(linia,"timeout_min="))	timeout_min = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"timeout_max="))	timeout_max = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"autokick="))	autokick = atoi(wytnij(linia,9).c_str());
        else if (find_pos(linia,"autokick_min="))	autokick_min = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"autokick_max="))	autokick_max = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"ploss="))	ploss = atoi(wytnij(linia,6).c_str());
        else if (find_pos(linia,"ploss_min="))	ploss_min = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"ploss_max="))	ploss_max = atoi(wytnij(linia,10).c_str());
        else if (find_pos(linia,"bombrepostime="))	bombrepostime = atoi(wytnij(linia,14).c_str());
        else if (find_pos(linia,"bombrepostime_min="))	bombrepostime_min = atoi(wytnij(linia,18).c_str());
        else if (find_pos(linia,"bombrepostime_max="))	bombrepostime_max = atoi(wytnij(linia,18).c_str());
        else if (find_pos(linia,"forcefirstspec="))	forcefirstspec = atoi(wytnij(linia,15).c_str());
        else if (find_pos(linia,"forcefirstspec_min="))	forcefirstspec_min = atoi(wytnij(linia,19).c_str());
        else if (find_pos(linia,"forcefirstspec_max="))	forcefirstspec_max = atoi(wytnij(linia,19).c_str());
        else if (find_pos(linia,"pingmax="))	pingmax = atoi(wytnij(linia,8).c_str());
        else if (find_pos(linia,"pingmax_min="))	pingmax_min = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"pingmax_max="))	pingmax_max = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"plossmax="))	plossmax = atoi(wytnij(linia,9).c_str());
        else if (find_pos(linia,"plossmax_min="))	plossmax_min = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"plossmax_max="))	plossmax_max = atoi(wytnij(linia,13).c_str());
        else if (find_pos(linia,"idlemax="))	idlemax = atoi(wytnij(linia,8).c_str());
        else if (find_pos(linia,"idlemax_min="))	idlemax_min = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"idlemax_max="))	idlemax_max = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"goutmax="))	goutmax = atoi(wytnij(linia,8).c_str());
        else if (find_pos(linia,"goutmax_min="))	goutmax_min = atoi(wytnij(linia,12).c_str());
        else if (find_pos(linia,"goutmax_max="))	goutmax_max = atoi(wytnij(linia,12).c_str());
    }

}
