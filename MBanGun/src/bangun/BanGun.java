
package bangun;
import static bangun.ChooseWeapons.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koder.Koder;
/**
 *
 * @author Mario PL
 */
public class BanGun {

    public static String tab[];
    public static int tabLinii[];
    public static int tabBlokad[];
    public static int size;
    public static String path;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        if(!sprawdzPliki())
            System.exit(0);
        ChooseWeapons w = new ChooseWeapons();
        Tabs.init();
        wczytajPlik();
        while(true){
            Thread.sleep(5000);
        }

    }
    
    static boolean sprawdzPlik(){
        String linia = null;
        String liniaspr="Task_DeclareParameters(\"WeaponType\", \"Weapon ID\", \"String32\", \"Weapon Name\", "
                + "\"String256\", \"Weapon Animation Name\", \"String256\", \"Weapon Sprite\", \"String256\", "
                + "\"Weapon Type\", \"EnumInt32\", \"Weapon Category\", \"EnumInt32\", \"Sight Display Type\", "
                + "\"EnumInt32\", \"Fixed Model (Used by GunPickup)\", \"String32\", \"Available FireModes\", "
                + "\"Int32\", \"Firemode Rounds In Succession\", \"Int32\", \"Firemode Rounds In Succession1\", "
                + "\"Int32\", \"Firemode Rounds In Succession2\", \"Int32\", \"Firemode Name\", \"String32\", "
                + "\"Firemode Name1\", \"String32\", \"Firemode Name2\", \"String32\", "
                + "\"Firemode Firesound Stop Time\", \"Real32\", \"Firemode Firesound Stop Time1\", \"Real32\", "
                + "\"Firemode Firesound Stop Time2\", \"Real32\", \"Standing Precision (Degrees)\", \"Degrees\", "
                + "\"Running Precision (Degrees)\", \"Degrees\", \"Walking Precision (Degrees)\", \"Degrees\", "
                + "\"Crouching Precision (Degrees)\", \"Degrees\", \"Crouch Walking Precision (Degrees)\", "
                + "\"Degrees\", \"Lying Flat Precision (Degrees)\", \"Degrees\", \"Crawling Precision (Degrees)\", "
                + "\"Degrees\", \"Jumping Precision Modifier\", \"Real32\", \"Turning Precision Modifier\", \"Real32\", "
                + "\"Movement Speed Modifier\", \"Real32\", \"(De)Activate Time\", \"Real32\", "
                + "\"1st Person Stand Anim\", \"String32\", \"1st Person Move Anim\", \"String32\", "
                + "\"1st Person Activate Anim\", \"String32\", \"1st Person DeActivate Anim\", \"String32\", "
                + "\"3rd Person Stand Anim\", \"String32\", \"3rd Person Walk Anim\", \"String32\", "
                + "\"3rd Person Run Anim\", \"String32\", \"3rd Person Crouch Stand Anim\", \"String32\", "
                + "\"3rd Person Crouch Walk Anim\", \"String32\", \"3rd Person Activate Anim\", \"String32\", "
                + "\"3rd Person DeActivate Anim\", \"String32\", \"Activate Sound\", \"String32\", "
                + "\"DeActivate Sound\", \"String32\", \"TracerTexture\", \"String16\", \"TracerVelocity(m/s)\", "
                + "\"Real32\", \"Primary Fire TaskType\", \"EnumInt32\", \"Primary Force Reload\", \"bool8\", "
                + "\"Primary Reload rounds individiually\", \"bool8\", \"Secondary Fire TaskType\", \"EnumInt32\", "
                + "\"Secondary Force Reload\", \"bool8\", \"Secondary Reload rounds individiually\", \"bool8\", "
                + "\"Selectable When Empty\", \"bool8\", \"Pickup sprite\", \"String256\", \"Laser Dot\", \"bool8\", "
                + "\"Laser Dot Always On\", \"bool8\", \"Netshop price\", \"Int32\", \"Bad Guys can buy\", \"bool8\", "
                + "\"Good Guys can buy\", \"bool8\", \"Netshop category\", \"Int32\", \"Netshop slot position\", "
                + "\"Int32\", \"Smoke Particle Size\", \"Real32\", \"Can weapon be dropped in 1P game\", \"bool8\");";
        
        
        BufferedReader s;
        try {
            s = new BufferedReader(new FileReader("out\\weapon.qsc"));
            s.readLine();
            s.readLine();
            s.readLine();
            s.readLine();
            s.readLine();
            linia = s.readLine();
            s.close();
        } catch (IOException ex) {Loguj1.loguj("BanGun: SprawdzPlik: "+ex);}
        
        if(linia.equals(liniaspr)){
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect file!");
            return false;
        }

    }
    
    static boolean sprawdzPliki(){
        File org = new File("files\\orginal.qvm");
        File cc = new File("files\\cconf.qvm");
        File dd = new File("files\\dconf.qvm");
        if(!org.exists() || !cc.exists() || !dd.exists()){
            JOptionPane.showMessageDialog(null, "Conf files not found!\n Install MBanGun again!");
            return false;
        }
        else
            return true;
    }
    
    
    static void decompile(){
        try {
            File dc = new File("files\\dconf.qvm");
            dc.renameTo(new File("files\\dconf.exe"));
            Process p = Runtime.getRuntime().exec("files\\dconf.exe out\\weapon.qvm");
            p.waitFor();
            dc = new File("files\\dconf.exe");
            dc.renameTo(new File("files\\dconf.qvm"));
        } catch (IOException | InterruptedException ex) {
            Loguj1.loguj("BanGun: decompile: "+ex);
        } 
    }
    
    static void compile(){
        try {
            File cm = new File("files\\cconf.qvm");
            cm.renameTo(new File("files\\cconf.exe"));
            cm = new File("out\\weapon.qvm");
            cm.delete();
            Process p = Runtime.getRuntime().exec("files\\cconf.exe -87 out\\weapon.qsc");
            p.waitFor();
            cm = new File("out\\weapon.qsc");
            cm.delete();
            cm = new File("files\\cconf.exe");
            cm.renameTo(new File("files\\cconf.qvm"));
        } catch (IOException | InterruptedException  ex) {
            Loguj1.loguj("BanGun: compile: "+ex);
        } 
        
    }
    
    static void wczytajPlik(){
        try {
            decompile();
            if(!sprawdzPlik())
                System.exit(0);
            tab = new String[1000];
            tabLinii = new int[33];
            String blokada;
            int i = 0, j=1;
            File f = new File("out\\weapon.qsc");      
            Scanner s = new Scanner(f);
            while(s.hasNextLine() && i<1000){
                tab[i] = s.nextLine();
                if(tab[i].indexOf(", \"WeaponType\"") > -1){
                    if(tab[i].indexOf(Tabs.Nazwa_Broni[j]) > -1){
                        tabLinii[j]=i;
                        blokada = tab[i].substring(Tabs.Pozycje[j],Tabs.Pozycje[j]+12);
                        if(blokada.equals("FALSE, FALSE"))
                            ustawCheckBox(3, j);
                        else if(blokada.equals("FALSE, TRUE,"))
                            ustawCheckBox(2, j);
                        else if(blokada.equals("TRUE, FALSE,"))
                            ustawCheckBox(1, j);
                        else if(blokada.equals("TRUE, TRUE, "))
                            ustawCheckBox(0, j);
                        else
                            ustawCheckBox(0, j);
                        j++;
                    }
                }
                i++;
            }   
            s.close();
            size = i;
        } catch (IOException ex) {Loguj1.loguj("BanGun: wczytajPlik: "+ex);}
       
    }
    
    static void zapiszPlik(){

        try {
            ustawLinie(1,jCheckBox1.isSelected(),jCheckBox93.isSelected());
            ustawLinie(2,jCheckBox2.isSelected(),jCheckBox97.isSelected());
            ustawLinie(3,jCheckBox3.isSelected(),jCheckBox98.isSelected());
            ustawLinie(4,jCheckBox4.isSelected(),jCheckBox99.isSelected());
            ustawLinie(5,jCheckBox5.isSelected(),jCheckBox100.isSelected());
            ustawLinie(6,jCheckBox6.isSelected(),jCheckBox101.isSelected());
            ustawLinie(7,jCheckBox7.isSelected(),jCheckBox102.isSelected());
            ustawLinie(8,jCheckBox9.isSelected(),jCheckBox104.isSelected());
            ustawLinie(9,jCheckBox8.isSelected(),jCheckBox103.isSelected());
            ustawLinie(10,jCheckBox63.isSelected(),jCheckBox115.isSelected());
            ustawLinie(11,jCheckBox64.isSelected(),jCheckBox85.isSelected());
            ustawLinie(12,jCheckBox65.isSelected(),jCheckBox86.isSelected());
            ustawLinie(13,jCheckBox66.isSelected(),jCheckBox87.isSelected());
            ustawLinie(14,jCheckBox67.isSelected(),jCheckBox88.isSelected());
            ustawLinie(15,jCheckBox68.isSelected(),jCheckBox89.isSelected());
            ustawLinie(16,jCheckBox69.isSelected(),jCheckBox90.isSelected());
            ustawLinie(17,jCheckBox71.isSelected(),jCheckBox92.isSelected());
            ustawLinie(18,jCheckBox70.isSelected(),jCheckBox91.isSelected());
            ustawLinie(19,jCheckBox72.isSelected(),jCheckBox94.isSelected());
            ustawLinie(20,jCheckBox73.isSelected(),jCheckBox95.isSelected());
            ustawLinie(21,jCheckBox74.isSelected(),jCheckBox96.isSelected());
            ustawLinie(22,jCheckBox75.isSelected(),jCheckBox105.isSelected());
            ustawLinie(23,jCheckBox76.isSelected(),jCheckBox106.isSelected());
            ustawLinie(24,jCheckBox77.isSelected(),jCheckBox107.isSelected());
            ustawLinie(25,jCheckBox78.isSelected(),jCheckBox108.isSelected());
            ustawLinie(26,jCheckBox80.isSelected(),jCheckBox110.isSelected());
            ustawLinie(27,jCheckBox79.isSelected(),jCheckBox109.isSelected());
            ustawLinie(28,jCheckBox81.isSelected(),jCheckBox111.isSelected());
            ustawLinie(29,jCheckBox82.isSelected(),jCheckBox112.isSelected());
            ustawLinie(30,jCheckBox84.isSelected(),jCheckBox114.isSelected());
            ustawLinie(31,jCheckBox83.isSelected(),jCheckBox113.isSelected());
            File f = new File("out\\weapon.qsc");
            
            PrintWriter pw = new PrintWriter(new File("out\\weapon.qsc"));
            for(int i = 0; i<size; i++){
                pw.append(tab[i]);
                pw.append("\n");
            }
            pw.close();
            JOptionPane.showMessageDialog(jCheckBox1, "Asd");
            compile();
        } catch (FileNotFoundException ex) {
            Loguj1.loguj("BanGun: zapiszPlik: "+ex);
        } 
     
        
    }
    
    static void ustawLinie(int bron, boolean check1, boolean check2){
        String linia = tab[tabLinii[bron]];
        if(check1 && check2){ //to dwa false
            linia = linia.substring(0,Tabs.Pozycje[bron])+"FALSE, FALSE"+linia.substring(linia.indexOf(", ",Tabs.Pozycje[bron]+7));
            tab[tabLinii[bron]] = linia;
        }else if(check1 && !check2){ // false true
            linia = linia.substring(0,Tabs.Pozycje[bron])+"FALSE, TRUE"+linia.substring(linia.indexOf(", ",Tabs.Pozycje[bron]+7));
            tab[tabLinii[bron]] = linia;        
        }else if(!check1 && check2){ // true false
            linia = linia.substring(0,Tabs.Pozycje[bron])+"TRUE, FALSE"+linia.substring(linia.indexOf(", ",Tabs.Pozycje[bron]+7));
            tab[tabLinii[bron]] = linia;        
        }else if(!check1 && !check2){ //true true
            linia = linia.substring(0,Tabs.Pozycje[bron])+"TRUE, TRUE"+linia.substring(linia.indexOf(", ",Tabs.Pozycje[bron]+7));
            tab[tabLinii[bron]] = linia;
        }
    }
    
    static void ustawCheckBox(int blokada, int bron){
        switch(bron){
            case 1:
                switch(blokada){
                    case 0:
                        jCheckBox1.setSelected(false);
                        jCheckBox93.setSelected(false);
                        break;
                    case 1:
                        jCheckBox1.setSelected(false);
                        jCheckBox93.setSelected(true);
                        break;
                    case 2:
                        jCheckBox1.setSelected(true);
                        jCheckBox93.setSelected(false);
                        break;
                    case 3:
                        jCheckBox1.setSelected(true);
                        jCheckBox93.setSelected(true);
                        break;
                }
                break;
            case 2:
                switch(blokada){
                    case 0:
                        jCheckBox2.setSelected(false);
                        jCheckBox97.setSelected(false);
                        break;
                    case 1:
                        jCheckBox2.setSelected(false);
                        jCheckBox97.setSelected(true);
                        break;
                    case 2:
                        jCheckBox2.setSelected(true);
                        jCheckBox97.setSelected(false);
                        break;
                    case 3:
                        jCheckBox2.setSelected(true);
                        jCheckBox97.setSelected(true);
                        break;
                }
                break;
            case 3:
                switch(blokada){
                    case 0:
                        jCheckBox3.setSelected(false);
                        jCheckBox98.setSelected(false);
                        break;
                    case 1:
                        jCheckBox3.setSelected(false);
                        jCheckBox98.setSelected(true);
                        break;
                    case 2:
                        jCheckBox3.setSelected(true);
                        jCheckBox98.setSelected(false);
                        break;
                    case 3:
                        jCheckBox3.setSelected(true);
                        jCheckBox98.setSelected(true);
                        break;
                }
                break;
            case 4:
                switch(blokada){
                    case 0:
                        jCheckBox4.setSelected(false);
                        jCheckBox99.setSelected(false);
                        break;
                    case 1:
                        jCheckBox4.setSelected(false);
                        jCheckBox99.setSelected(true);
                        break;
                    case 2:
                        jCheckBox4.setSelected(true);
                        jCheckBox99.setSelected(false);
                        break;
                    case 3:
                        jCheckBox4.setSelected(true);
                        jCheckBox99.setSelected(true);
                        break;
                }
                break;
            case 5:
                switch(blokada){
                    case 0:
                        jCheckBox5.setSelected(false);
                        jCheckBox100.setSelected(false);
                        break;
                    case 1:
                        jCheckBox5.setSelected(false);
                        jCheckBox100.setSelected(true);
                        break;
                    case 2:
                        jCheckBox5.setSelected(true);
                        jCheckBox100.setSelected(false);
                        break;
                    case 3:
                        jCheckBox5.setSelected(true);
                        jCheckBox100.setSelected(true);
                        break;
                }
                break;
            case 6:
                switch(blokada){
                    case 0:
                        jCheckBox6.setSelected(false);
                        jCheckBox101.setSelected(false);
                        break;
                    case 1:
                        jCheckBox6.setSelected(false);
                        jCheckBox101.setSelected(true);
                        break;
                    case 2:
                        jCheckBox6.setSelected(true);
                        jCheckBox101.setSelected(false);
                        break;
                    case 3:
                        jCheckBox6.setSelected(true);
                        jCheckBox101.setSelected(true);
                        break;
                }
                break;
            case 7:
                switch(blokada){
                    case 0:
                        jCheckBox7.setSelected(false);
                        jCheckBox102.setSelected(false);
                        break;
                    case 1:
                        jCheckBox7.setSelected(false);
                        jCheckBox102.setSelected(true);
                        break;
                    case 2:
                        jCheckBox7.setSelected(true);
                        jCheckBox102.setSelected(false);
                        break;
                    case 3:
                        jCheckBox7.setSelected(true);
                        jCheckBox102.setSelected(true);
                        break;
                }
                break;
            case 8:
                switch(blokada){
                    case 0:
                        jCheckBox9.setSelected(false);
                        jCheckBox104.setSelected(false);
                        break;
                    case 1:
                        jCheckBox9.setSelected(false);
                        jCheckBox104.setSelected(true);
                        break;
                    case 2:
                        jCheckBox9.setSelected(true);
                        jCheckBox104.setSelected(false);
                        break;
                    case 3:
                        jCheckBox9.setSelected(true);
                        jCheckBox104.setSelected(true);
                        break;
                }
                break;
            case 9:
                switch(blokada){
                    case 0:
                        jCheckBox8.setSelected(false);
                        jCheckBox103.setSelected(false);
                        break;
                    case 1:
                        jCheckBox8.setSelected(false);
                        jCheckBox103.setSelected(true);
                        break;
                    case 2:
                        jCheckBox8.setSelected(true);
                        jCheckBox103.setSelected(false);
                        break;
                    case 3:
                        jCheckBox8.setSelected(true);
                        jCheckBox103.setSelected(true);
                        break;
                }
                break;
            case 10:
                switch(blokada){
                    case 0:
                        jCheckBox63.setSelected(false);
                        jCheckBox115.setSelected(false);
                        break;
                    case 1:
                        jCheckBox63.setSelected(false);
                        jCheckBox115.setSelected(true);
                        break;
                    case 2:
                        jCheckBox63.setSelected(true);
                        jCheckBox115.setSelected(false);
                        break;
                    case 3:
                        jCheckBox63.setSelected(true);
                        jCheckBox115.setSelected(true);
                        break;
                }
                break;
            case 11:
                switch(blokada){
                    case 0:
                        jCheckBox64.setSelected(false);
                        jCheckBox85.setSelected(false);
                        break;
                    case 1:
                        jCheckBox64.setSelected(false);
                        jCheckBox85.setSelected(true);
                        break;
                    case 2:
                        jCheckBox64.setSelected(true);
                        jCheckBox85.setSelected(false);
                        break;
                    case 3:
                        jCheckBox64.setSelected(true);
                        jCheckBox85.setSelected(true);
                        break;
                }
                break;
            case 12:
                switch(blokada){
                    case 0:
                        jCheckBox65.setSelected(false);
                        jCheckBox86.setSelected(false);
                        break;
                    case 1:
                        jCheckBox65.setSelected(false);
                        jCheckBox86.setSelected(true);
                        break;
                    case 2:
                        jCheckBox65.setSelected(true);
                        jCheckBox86.setSelected(false);
                        break;
                    case 3:
                        jCheckBox65.setSelected(true);
                        jCheckBox86.setSelected(true);
                        break;
                }
                break;
            case 13:
                switch(blokada){
                    case 0:
                        jCheckBox66.setSelected(false);
                        jCheckBox87.setSelected(false);
                        break;
                    case 1:
                        jCheckBox66.setSelected(false);
                        jCheckBox87.setSelected(true);
                        break;
                    case 2:
                        jCheckBox66.setSelected(true);
                        jCheckBox87.setSelected(false);
                        break;
                    case 3:
                        jCheckBox66.setSelected(true);
                        jCheckBox87.setSelected(true);
                        break;
                }
                break;
            case 14:
                switch(blokada){
                    case 0:
                        jCheckBox67.setSelected(false);
                        jCheckBox88.setSelected(false);
                        break;
                    case 1:
                        jCheckBox67.setSelected(false);
                        jCheckBox88.setSelected(true);
                        break;
                    case 2:
                        jCheckBox67.setSelected(true);
                        jCheckBox88.setSelected(false);
                        break;
                    case 3:
                        jCheckBox67.setSelected(true);
                        jCheckBox88.setSelected(true);
                        break;
                }
                break;
            case 15:
                switch(blokada){
                    case 0:
                        jCheckBox68.setSelected(false);
                        jCheckBox89.setSelected(false);
                        break;
                    case 1:
                        jCheckBox68.setSelected(false);
                        jCheckBox89.setSelected(true);
                        break;
                    case 2:
                        jCheckBox68.setSelected(true);
                        jCheckBox89.setSelected(false);
                        break;
                    case 3:
                        jCheckBox68.setSelected(true);
                        jCheckBox89.setSelected(true);
                        break;
                }
                break;
            case 16:
                switch(blokada){
                    case 0:
                        jCheckBox69.setSelected(false);
                        jCheckBox90.setSelected(false);
                        break;
                    case 1:
                        jCheckBox69.setSelected(false);
                        jCheckBox90.setSelected(true);
                        break;
                    case 2:
                        jCheckBox69.setSelected(true);
                        jCheckBox90.setSelected(false);
                        break;
                    case 3:
                        jCheckBox69.setSelected(true);
                        jCheckBox90.setSelected(true);
                        break;
                }
                break;
            case 17:
                switch(blokada){
                    case 0:
                        jCheckBox71.setSelected(false);
                        jCheckBox92.setSelected(false);
                        break;
                    case 1:
                        jCheckBox71.setSelected(false);
                        jCheckBox92.setSelected(true);
                        break;
                    case 2:
                        jCheckBox71.setSelected(true);
                        jCheckBox92.setSelected(false);
                        break;
                    case 3:
                        jCheckBox71.setSelected(true);
                        jCheckBox92.setSelected(true);
                        break;
                }
                break;
            case 18:
                switch(blokada){
                    case 0:
                        jCheckBox70.setSelected(false);
                        jCheckBox91.setSelected(false);
                        break;
                    case 1:
                        jCheckBox70.setSelected(false);
                        jCheckBox91.setSelected(true);
                        break;
                    case 2:
                        jCheckBox70.setSelected(true);
                        jCheckBox91.setSelected(false);
                        break;
                    case 3:
                        jCheckBox70.setSelected(true);
                        jCheckBox91.setSelected(true);
                        break;
                }
                break;
            case 19:
                switch(blokada){
                    case 0:
                        jCheckBox72.setSelected(false);
                        jCheckBox94.setSelected(false);
                        break;
                    case 1:
                        jCheckBox72.setSelected(false);
                        jCheckBox94.setSelected(true);
                        break;
                    case 2:
                        jCheckBox72.setSelected(true);
                        jCheckBox94.setSelected(false);
                        break;
                    case 3:
                        jCheckBox72.setSelected(true);
                        jCheckBox94.setSelected(true);
                        break;
                }
                break;
            case 20:
                switch(blokada){
                    case 0:
                        jCheckBox73.setSelected(false);
                        jCheckBox95.setSelected(false);
                        break;
                    case 1:
                        jCheckBox73.setSelected(false);
                        jCheckBox95.setSelected(true);
                        break;
                    case 2:
                        jCheckBox73.setSelected(true);
                        jCheckBox95.setSelected(false);
                        break;
                    case 3:
                        jCheckBox73.setSelected(true);
                        jCheckBox95.setSelected(true);
                        break;
                }
                break;
            case 21:
                switch(blokada){
                    case 0:
                        jCheckBox74.setSelected(false);
                        jCheckBox96.setSelected(false);
                        break;
                    case 1:
                        jCheckBox74.setSelected(false);
                        jCheckBox96.setSelected(true);
                        break;
                    case 2:
                        jCheckBox74.setSelected(true);
                        jCheckBox96.setSelected(false);
                        break;
                    case 3:
                        jCheckBox74.setSelected(true);
                        jCheckBox96.setSelected(true);
                        break;
                }
                break;
            case 22:
                switch(blokada){
                    case 0:
                        jCheckBox75.setSelected(false);
                        jCheckBox105.setSelected(false);
                        break;
                    case 1:
                        jCheckBox75.setSelected(false);
                        jCheckBox105.setSelected(true);
                        break;
                    case 2:
                        jCheckBox75.setSelected(true);
                        jCheckBox105.setSelected(false);
                        break;
                    case 3:
                        jCheckBox75.setSelected(true);
                        jCheckBox105.setSelected(true);
                        break;
                }
                break;
            case 23:
                switch(blokada){
                    case 0:
                        jCheckBox76.setSelected(false);
                        jCheckBox106.setSelected(false);
                        break;
                    case 1:
                        jCheckBox76.setSelected(false);
                        jCheckBox106.setSelected(true);
                        break;
                    case 2:
                        jCheckBox76.setSelected(true);
                        jCheckBox106.setSelected(false);
                        break;
                    case 3:
                        jCheckBox76.setSelected(true);
                        jCheckBox106.setSelected(true);
                        break;
                }
                break;
            case 24:
                switch(blokada){
                    case 0:
                        jCheckBox77.setSelected(false);
                        jCheckBox107.setSelected(false);
                        break;
                    case 1:
                        jCheckBox77.setSelected(false);
                        jCheckBox107.setSelected(true);
                        break;
                    case 2:
                        jCheckBox77.setSelected(true);
                        jCheckBox107.setSelected(false);
                        break;
                    case 3:
                        jCheckBox77.setSelected(true);
                        jCheckBox107.setSelected(true);
                        break;
                }
                break;
            case 25:
                switch(blokada){
                    case 0:
                        jCheckBox78.setSelected(false);
                        jCheckBox108.setSelected(false);
                        break;
                    case 1:
                        jCheckBox78.setSelected(false);
                        jCheckBox108.setSelected(true);
                        break;
                    case 2:
                        jCheckBox78.setSelected(true);
                        jCheckBox108.setSelected(false);
                        break;
                    case 3:
                        jCheckBox78.setSelected(true);
                        jCheckBox108.setSelected(true);
                        break;
                }
                break;
            case 26:
                switch(blokada){
                    case 0:
                        jCheckBox80.setSelected(false);
                        jCheckBox110.setSelected(false);
                        break;
                    case 1:
                        jCheckBox80.setSelected(false);
                        jCheckBox110.setSelected(true);
                        break;
                    case 2:
                        jCheckBox80.setSelected(true);
                        jCheckBox110.setSelected(false);
                        break;
                    case 3:
                        jCheckBox80.setSelected(true);
                        jCheckBox110.setSelected(true);
                        break;
                }
                break;
            case 27:
                switch(blokada){
                    case 0:
                        jCheckBox79.setSelected(false);
                        jCheckBox109.setSelected(false);
                        break;
                    case 1:
                        jCheckBox79.setSelected(false);
                        jCheckBox109.setSelected(true);
                        break;
                    case 2:
                        jCheckBox79.setSelected(true);
                        jCheckBox109.setSelected(false);
                        break;
                    case 3:
                        jCheckBox79.setSelected(true);
                        jCheckBox109.setSelected(true);
                        break;
                }
                break;
            case 28:
                switch(blokada){
                    case 0:
                        jCheckBox81.setSelected(false);
                        jCheckBox111.setSelected(false);
                        break;
                    case 1:
                        jCheckBox81.setSelected(false);
                        jCheckBox111.setSelected(true);
                        break;
                    case 2:
                        jCheckBox81.setSelected(true);
                        jCheckBox111.setSelected(false);
                        break;
                    case 3:
                        jCheckBox81.setSelected(true);
                        jCheckBox111.setSelected(true);
                        break;
                }
                break;
            case 29:
                switch(blokada){
                    case 0:
                        jCheckBox82.setSelected(false);
                        jCheckBox112.setSelected(false);
                        break;
                    case 1:
                        jCheckBox82.setSelected(false);
                        jCheckBox112.setSelected(true);
                        break;
                    case 2:
                        jCheckBox82.setSelected(true);
                        jCheckBox112.setSelected(false);
                        break;
                    case 3:
                        jCheckBox82.setSelected(true);
                        jCheckBox112.setSelected(true);
                        break;
                }
                break;
            case 30:
                switch(blokada){
                    case 0:
                        jCheckBox84.setSelected(false);
                        jCheckBox114.setSelected(false);
                        break;
                    case 1:
                        jCheckBox84.setSelected(false);
                        jCheckBox114.setSelected(true);
                        break;
                    case 2:
                        jCheckBox84.setSelected(true);
                        jCheckBox114.setSelected(false);
                        break;
                    case 3:
                        jCheckBox84.setSelected(true);
                        jCheckBox114.setSelected(true);
                        break;
                }
                break;
            case 31:
                switch(blokada){
                    case 0:
                        jCheckBox83.setSelected(false);
                        jCheckBox113.setSelected(false);
                        break;
                    case 1:
                        jCheckBox83.setSelected(false);
                        jCheckBox113.setSelected(true);
                        break;
                    case 2:
                        jCheckBox83.setSelected(true);
                        jCheckBox113.setSelected(false);
                        break;
                    case 3:
                        jCheckBox83.setSelected(true);
                        jCheckBox113.setSelected(true);
                        break;
                }
                break;
            
        }
    }
    
    
    static void copy(String file1, String file2){
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(file1);
            os = new FileOutputStream(file2);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            Loguj1.loguj("BanGun: zapiszPlik: "+ex);
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException ex) {
                Loguj1.loguj("BanGun: zapiszPlik: "+ex);
            }
        }
    }
    
}
