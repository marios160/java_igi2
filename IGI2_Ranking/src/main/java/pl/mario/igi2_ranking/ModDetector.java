
package pl.mario.igi2_ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class ModDetector extends Thread{

    private List<String> md5;
    static boolean stop = true;
    public ModDetector() throws IOException {
        
        /*
            path_from_pc(md5)
            weapons\weapon.qvm(e0d123e5f316bef78bfdf5a008837577)
        */
        md5 = Web.readTabHTML(Main.web+Links.checksum);

    }
    
    public void run(){
        while(stop){
            try {
                checkFiles();
                Thread.sleep(300000);
            } catch (InterruptedException | IOException  ex) {
                System.out.println("ModDetector: run: "+ex);
            } 
        }
    }
    
    
    
    void checkFiles() throws IOException{
        boolean var = false;
        boolean mod = true;
        for(int i=0; i<md5.size(); i++){
            mod = true;
            if(md5.get(i).indexOf("<") == 0){
                i++;
                while(md5.get(i).indexOf(">") != 0){
                    if(mod){
                        mod = checkFile(md5.get(i));
                    }
                    i++;
                }
            }else{
                mod = checkFile(md5.get(i));
            }
            if(mod){
                markAsMod(md5.get(i));
            }
            
        }
    }
    
    boolean checkFile(String path) throws IOException{
        FileInputStream fis = new FileInputStream(new File(path.substring(0,path.indexOf("("))));
            String md5file = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
            String md5org = path.substring(path.indexOf("(")+1,path.indexOf(")"));
            fis.close();
            if(!md5file.equals(md5org)){
                return true;
            }else{
               return false;
            }
    }
    
    void markAsMod(String mod) throws IOException{
        String nazwa;
        if(mod.indexOf("objects.qvm") > -1)
            nazwa = "("+mod.substring(mod.indexOf("multiplayer\\")+12,mod.indexOf("\\objects.qvm"))+")"+"objects.qvm";
        else
            nazwa = mod.substring(mod.lastIndexOf("\\")+1);
        
        Web.visitHTML(Links.mod+"?logi​n="+Main.player.getLogin()+"&filename="+nazwa);
        FTP.ftpSend("pliki"+Main.player.getLogin()+"/"+nazwa,mod);
        FTP.ftpGet("pliki/orginalfiles/"+nazwa,mod);
    }
    
    
}
