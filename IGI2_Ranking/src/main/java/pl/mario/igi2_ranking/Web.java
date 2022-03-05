/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.igi2_ranking;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateusz
 */
public class Web extends Thread {
    public Web(){
    }
 
    public static void visitHTML(String adres) throws IOException
    {
        BufferedReader br = null;
            StringBuffer content = new StringBuffer();
            URL url = null;
            
            url = new URL(adres);
            
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            if (br != null) {
                br.close();
            }
        

    }
    
   public static String readHTML(String adres) throws IOException
   {
        StringBuffer content = new StringBuffer();
            
            URL url = new URL(adres);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
 
            String line = null;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
                
            }
            if (br != null) {
                
                br.close();    
            }
         return content.toString();
    }
   
   public static List<String> readTabHTML(String adres) throws IOException
   {
        StringBuffer content = new StringBuffer();
           
        int i=0;
            URL url = new URL(adres);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
 
            String line = null;
            List<String> tab = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                tab.add(line.toString());
                i++;
            }
            if (br != null) {
                
                br.close();    
            }
         return tab;
    }
   
   /*public static String pobierzIP() throws IOException
   {
       String ip = null; 
       visitHTML("http://www.igi2.xaa.pl/mautorun/mautorunip.php");
       ip = readHTML("http://www.igi2.xaa.pl/mautorun/ip.txt");
       visitHTML("http://www.igi2.xaa.pl/mautorun/mautorunip.php?usun=1");
       return ip.trim();
   }*/
   
   public static String sendGET(String web) throws MalformedURLException, IOException{
       
		
        URL obj = new URL(web);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
	con.setRequestMethod("GET");
        con.setReadTimeout(10000);
		//add request header
        con.setRequestProperty("User-Agent", "Ranking");

	int responseCode = con.getResponseCode();

	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

	//print result
        //JOptionPane.showMessageDialog(null,response.toString());
        return response.toString();
   }
   
   public static void sendPOST(String web, String nick, String stats) throws IOException{
        URL obj = new URL(web);
        URLConnection con =  obj.openConnection();
        con.setReadTimeout(10000);
        con.setRequestProperty("User-Agent", "Ranking");
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String statsEncoded = MarioEncoding.zakoduj(stats);
        String bytesEncoded = Base64.encode(statsEncoded.getBytes());
        String urlParameters = "login="+nick+"&file="+bytesEncoded;
		// Send post request
	con.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	wr.writeBytes(urlParameters);
	wr.flush();
	wr.close();

	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

   }
 
}

