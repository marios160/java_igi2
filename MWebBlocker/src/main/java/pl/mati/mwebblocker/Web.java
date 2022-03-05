/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mati.mwebblocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateusz
 */
public class Web extends Thread {

    String adres;

    public Web(String adres) {
        this.adres = "http://" + adres;
    }

    public void run() {
        String zrodlo = readHTML(adres).toLowerCase();
        String key = "";
        String title = "";
        if (zrodlo.contains("meta name=\"keywords\"")) {
            key = zrodlo.substring(zrodlo.indexOf("meta name=\"keywords\""), zrodlo.indexOf("\">", zrodlo.indexOf("meta name=\"keywords\"")));
        }
        if (zrodlo.contains("<title>")) {
            title = zrodlo.substring(zrodlo.indexOf("<title>"), zrodlo.indexOf("</title>", zrodlo.indexOf("<title>")));

        }
        if (adres.contains("porn")
                || adres.contains("sex")
                || adres.contains("seks")
                || adres.contains("porn")
                || adres.contains("xxx")
                || key.contains("sex kamerki")
                || key.contains("sex chat")
                || key.contains("sex")
                || key.contains("seks")
                || key.contains("porn")
                || key.contains("masturbacja")
                || key.contains("eroty")
                || title.contains("seks")
                || title.contains("porn")
                || title.contains("sex kamerki")
                || title.contains("sex chat")
                || title.contains("sex")) {
            System.out.println(adres);
            Main.blokuj(adres);
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, "Zablokowalem pornograficzna strone : " + adres + "\n\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!"
                    + "\nJezusie Synu Dawida ulituj sie nade mna grzesznikiem!");

        }

    }

    public static String readHTML(String adres) {
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
            // System.out.println("HtmlReader-readHTML "+ ex.toString());
        }
        return content.toString();
    }

}
