/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;





/**
 *
 * @author Mateusz
 */
public class FTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         
        String server = "igi2.xaa.pl";
		int port = 21;
		String user = "igi";
		String pass = "RH7I4T";

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			File firstLocalFile = new File("D:\\GRY\\igi2\\bany.txt");
			String firstRemoteFile = "/public_html/aaluigi/bany.txt";
			InputStream inputStream = new FileInputStream(firstLocalFile);

			System.out.println("Start uploading first file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (done) {
				System.out.println("The first file is uploaded successfully.");
			}
                        
                        
			// APPROACH #2: uploads second file using an OutputStream
			File secondLocalFile = new File("D:\\GRY\\igi2\\bany.txt");
			String secondRemoteFile = "/public_html/aaluigi/bany.txt";
			inputStream = new FileInputStream(secondLocalFile);

			System.out.println("Start uploading second file");
			OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
                        byte[] bytesIn = new byte[4096];
                        int read = 0;

                        while ((read = inputStream.read(bytesIn)) != -1) {
                                outputStream.write(bytesIn, 0, read);
                        }
                    inputStream.close();
                    outputStream.close();

	        boolean completed = ftpClient.completePendingCommand();
			if (completed) {
				System.out.println("The second file is uploaded successfully.");
			}
                    
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    
    }
}
