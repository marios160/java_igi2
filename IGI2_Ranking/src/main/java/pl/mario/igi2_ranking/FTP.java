/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.igi2_ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;





/**
 *
 * @author Mateusz
 */
public class FTP {


    public static boolean ftpSend(String remoteF, String localF) throws IOException
    {
        
		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(MarioEncoding.odkoduj("098019085538048548085006578568578575568009578588009538029"));
			ftpClient.login(MarioEncoding.odkoduj("098019085538048548085006578568578575568009578588009538029076568009578588009538029"), 
                                MarioEncoding.odkoduj("057077007568009578588009538067"));
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(0);
			File firstLocalFile = new File(localF);
			String firstRemoteFile = "pliki/"+Main.player.getLogin()+"/"+remoteF;
			InputStream inputStream = new FileInputStream(firstLocalFile);
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
                        System.out.println(ftpClient.getReplyString()); 
			inputStream.close();
                        ftpClient.logout();
			if (done) {
                            
				return true;
			}
                        
                }   catch (NoClassDefFoundError | IOException ex) {
                    System.out.println("ftpSend: "+ex);
                }
        return false;
    }
    public static boolean ftpGet(String remoteF, String localF) throws IOException
    {

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(MarioEncoding.odkoduj("098019085538048548085006578568578575568009578588009538029"));
			ftpClient.login(MarioEncoding.odkoduj("098019085538048548085006578568578575568009578588009538029076568009578588009538029"), 
                                MarioEncoding.odkoduj("057077007568009578588009538067"));
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(0);
                        FileOutputStream localFile = new FileOutputStream(new File(localF));
			boolean done = ftpClient.retrieveFile(remoteF, localFile);
                        System.out.println(ftpClient.getReplyString()); 
			localFile.close();
                        ftpClient.logout();
			if (done) {
                            
				return true;
			}
                        
                }   catch (NoClassDefFoundError | IOException ex) {
                    System.out.println("ftpGet: "+ex);
                }
        return false;
    }
    
    public static boolean makeDir() throws IOException
    {

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(MarioEncoding.odkoduj("098019085538048548085006578568578575568009578588009538029"));
			ftpClient.login(MarioEncoding.odkoduj("098019085538048548085006578568578575568009578588009538029076568009578588009538029"), 
                                MarioEncoding.odkoduj("057077007568009578588009538067"));
                        boolean done = ftpClient.makeDirectory(Main.player.getLogin());
                        ftpClient.logout();
                        if (done) {
				return true;
			}
                        
                }   catch (NoClassDefFoundError | IOException ex) {
                    System.out.println("ftpGet: "+ex);
                }
        return false;
    }
}
