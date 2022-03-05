
package pl.mario.igi2_ranking;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mario PL
 */
public class Kill {

    
    private int place;
    private int weapon;
    private int kill;

    private String placeS;
    private String weaponS;
    private String killS;
    
    private String pstopa = "pstopa";
    private String plydka = "plydka";
    private String pudo = "pudo";
    private String pdlon = "pdlon";
    private String plokiec = "plokiec";
    private String pramie = "pramie";
    private String lstopa = "lstopa";
    private String llydka = "llydka";
    private String ludo = "ludo";
    private String ldlon = "ldlon";
    private String llokiec = "llokiec";
    private String lramie = "lramie";
    private String klatka = "klatka";
    private String plecy = "plecy";
    private String jaja = "jaja";
    private String glowa = "glowa";
    
    private String ak = "ak";
    private String aug = "aug";
    private String deagle = "deagle";
    private String g11 = "g11";
    private String g36 = "g36";
    private String glock = "glock";
    private String grenade = "grenade";
    private String jackhammer = "jackhammer";
    private String kolba = "kolba";
    private String m1014 = "m1014";
    private String m16 = "m16";
    private String m82a1 = "m82a1";
    private String mac10 = "mac10";
    private String magnum = "magnum";
    private String makarov = "makarov";
    private String minimi = "minimi";
    private String mp5 = "mp5";
    private String noz = "noz";
    private String psg = "psg";
    private String smg2 = "smg2";
    private String socom = "socom";
    private String spas = "spas";
    private String svd = "svd";
    private String twinuzi = "twinuzi";
    private String type64 = "type64";
    private String uzi = "uzi";
    
    private String suicide = "suicide";
    private String spawn = "spawn";

    

    public Kill( int kill, int weapon, int place) {
        this.place = place;
        this.weapon = weapon;
        this.kill = kill;
    }
    
    
    public String getString(){
        
        switch(this.weapon){
            
            case 0:
                weaponS = suicide;
                break;
            case 1:
                weaponS = ak;
                break;
            case 3:
                weaponS = aug;
                break;
            case 4:
                weaponS = m82a1;
                break;
            case 8:
                weaponS = magnum;
                break;
            case 9:
                weaponS = deagle;
                break;
            case 10:
                weaponS = svd;
                break;
            case 12:
                weaponS = g11;
                break;
            case 13:
                weaponS = g36;
                break;
            case 14:
                weaponS = glock;
                break;
            case 15:
                weaponS = grenade;
                break;
            case 16:
                weaponS = m1014;
                break;
            case 17:
                weaponS = jackhammer;
                break;
            case 18:
                weaponS = noz;
                break;
            case 19:
                weaponS = kolba;
                break;
            case 21:
                weaponS = m16;
                break;
            case 22:
                weaponS = mac10;
                break;
            case 23:
                weaponS = makarov;
                break;
            case 26:
                weaponS = minimi;
                break;
            case 27:
                weaponS = mp5;
                break;
            case 29:
                weaponS = psg;
                break;
            case 35:
                weaponS = smg2;
                break;
            case 37:
                weaponS = socom;
                break;
            case 38:
                weaponS = spas;
                break;
            case 41:
                weaponS = type64;
                break;
            case 42:
                weaponS = uzi;
                break;
            case 43:
                weaponS = twinuzi;
                break;
            case 44:
                weaponS = spawn;
                break;
            default:
                weaponS = "???";
                break;
        }
        
        switch(this.place){
            case 0:
                placeS = pstopa;
                break;
            case 1:
                placeS = plydka;
                break;
            case 2:
                placeS = pudo;
                break;
            case 3:
                placeS = pdlon;
                break;
            case 4:
                placeS = plokiec;
                break;
            case 5:
                placeS = pramie;
                break;
            case 6:
                placeS = lstopa;
                break;
            case 7:
                placeS = llydka;
                break;
            case 8:
                placeS = ludo;
                break;
            case 9:
                placeS = ldlon;
                break;
            case 10:
                placeS = llokiec;
                break;
            case 11:
                placeS = lramie;
                break;
            case 12:
                placeS = klatka;
                break;
            case 13:
                placeS = klatka;
                break;
            case 14:
                placeS = plecy;
                break;
            case 15:
                placeS = jaja;
                break;
            case 16:
                placeS = glowa;
                break;
            case 17:
                placeS = suicide;
                break;
            case 18:
                placeS = spawn;
                break;
            case 19:
                placeS = grenade;
                break;
            case 20:
                placeS = kolba;
                break;
            default:
                weaponS = "???";
                break;
        }
        
        
        if(this.kill == 1)
            this.killS = "k";
        else
            this.killS = "d";
        SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        System.out.println(this.killS+"||"+this.weaponS+"||"+this.placeS+"||"+simpleDateHere.format(new Date()));
        return this.killS+"||"+this.weaponS+"||"+this.placeS+"||"+simpleDateHere.format(new Date());
    }
    
    public static void staty(String kill){
        if(kill.indexOf("k") == 0){
            if(kill.indexOf("spawn") > -1){
                Main.player.subKills();
            }else{
                Main.player.addKills();
            }
        }else{
            Main.player.addDeaths();
        }
    }
    
    
}
