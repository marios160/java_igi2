
package ranking;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Mateusz
 */
public class Ranking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        NewJFrame x = new NewJFrame();
        while(true)
        {
            Thread.sleep(1000);
        }
       
    }
    
   static void wyslij(String url) throws MalformedURLException, IOException{    
        
		
		URL obj = new URL(url);
		URLConnection con =  obj.openConnection();

		//add reuqest header
		//con.("POST");
		con.setRequestProperty("User-Agent", "Ranking");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		
		
               String content;
               
               content = new String(Files.readAllBytes(Paths.get("test.txt")));
               String   bytesEncoded = Base64.encode(content .getBytes());
              
                       String urlParameters = "nick=Dominik&file="+bytesEncoded;
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode =1;// con.getResponseCode();
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
		System.out.println(response.toString());

	}

    }
    
    
    
