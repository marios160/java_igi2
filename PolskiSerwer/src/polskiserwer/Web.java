/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polskiserwer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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

    public Web() {
    }

    public static boolean visitHTML(String adres) {
        BufferedReader br = null;
        try {
            StringBuffer content = new StringBuffer();
            URL url = null;

            url = new URL(adres);

            br = new BufferedReader(new InputStreamReader(url.openStream()));
            if (br != null) {
                br.close();
            }
        } catch (MalformedURLException ex) {
            System.out.println("HtmlReader-visitHTML" + ex.toString());
        } catch (IOException ex) {
            System.out.println("HtmlReader-visitHTML" + ex.toString());
            return false;
        }
        return true;

    }

    public static String readHTML(String adres) {
        String line = null;
        try {

            URL url = null;

            url = new URL(adres);
            BufferedReader br = null;

            br = new BufferedReader(new InputStreamReader(url.openStream()));


            line = br.readLine();
            if (br != null) {

                br.close();
            }
        } catch (IOException ex) {
            System.out.println("HtmlReader-readHTML" + ex.toString());
        }
        return line;
    }

}
