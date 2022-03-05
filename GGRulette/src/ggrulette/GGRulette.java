/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggrulette;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Mateusz
 */
public class GGRulette {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sendPost("loguj","https://www.gg.pl",
                "login=63741607&password=rower160&doAction=");
//        sendPost("rulet","https://www.gg.pl/authorize/login?redirect=", "");
//        sendPost("rulet","https://www.gg.pl/api/roulettes/gg/pl:63741607.json", "age_ranges%5B%5D=13%2C16&age_ranges%5B%5D=16%2C18&age_ranges%5B%5D=18%2C22&age_ranges%5B%5D=22%2C30&age_ranges%5B%5D=30%2C50&age_ranges%5B%5D=50%2C80&gender=1&csrf_token=d72c36592cacb64946ca84cf87e2df3fa2e23d66");
        
    }
    
    // HTTP POST request
	static void sendPost(String plik, String www, String param) {

        try {
            String url = www;
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            
            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Referer", "https://login.gg.pl/loginForm?urlhashUri=https%3A%2F%2Fwww.gg.pl%2Finfo%2Ftransport.html&origin=https%3A%2F%2Fwww.gg.pl%2F&attempt_no=1&context=parent&forceSyncStatus=false&id=frame_6636&transport=PostMessage&client_id=13a8aec5f6e60b5880d83fdd18a314d5&useStatus=true");
            String urlParameters = param;
            
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            //print result
            PrintWriter log = new PrintWriter(new FileWriter(plik, true));
            log.append(response.toString());
            log.close();
            System.out.println(response.toString());
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        } catch (ProtocolException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

	}

}
