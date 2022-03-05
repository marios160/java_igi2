/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Mario PL
 */
public class htmlReader extends Thread {
    public htmlReader(){
    }
 
    public static int visitHTML(String adres)
    {
        BufferedReader br = null;
        try {
            StringBuffer content = new StringBuffer();
            URL url = null;
            
            url = new URL(adres);
            
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            if (br != null) {
                br.close();
            }
        } catch (IOException  ex) {
            System.out.println("HtmlReader: Visit: "+ex);
        }
        return 123;
    }
    
   public static String readHTML(String adres)
   {
        StringBuffer content = new StringBuffer();
        try {
            
            URL url = null;
            
            url = new URL(adres);
            BufferedReader br = null;
            
            br = new BufferedReader(new InputStreamReader(url.openStream()));
 
            String line = null;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            if (br != null) {
                
                br.close();    
            }
        } catch (IOException ex) {
            System.out.println("HtmlReader: read: "+ex);
        }
         return content.toString();
    }

 
}

