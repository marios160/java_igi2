/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aranking;

/**
 *
 * @author Mateusz
 */

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

public class ARanking {

    public static void main(String args[]) {

        TailerListener listener = new MyListener();
        Tailer tailer = new Tailer(new File("Multiplayer.log"), listener, 5);
        tailer.run();

    }

    public static class MyListener extends TailerListenerAdapter {

        @Override
        public void handle(String line) {

            // Variables
            String Died = "";
            String Killed = "";
            String Gun = "";

            // Gun Names
            String AK47 = "AK-47";
            String AUG = "AUG";
            String M82A1 = "M82A1";
            String Magnum = "Magnum";
            String DEagle = "D-Eagle";
            String Dragunov = "Dragunov";
            String Flashbang = "Flashbang";
            String G11 = "G11";
            String G36 = "G36";
            String G17SD = "G-17 SD";
            String M1014 = "M1014";
            String Jackhammer = "Jackhammer";
            String M16A2 = "M16 A2";
            String MAC10 = "MAC-10";
            String Makarov = "Makarov";
            String Minimi = "Minimi";
            String MP5A3 = "MP5A3";
            String PSG1 = "PSG-1";
            String SMG2 = "SMG-2";
            String Socom = "Socom";
            String Spas12 = "Spas-12";
            String Type64SMG = "Type 64 SMG";
            String Uzi = "Uzi";
            String TwinUzi = "Twin Uzi";

            {     // Normal Kill

                String Log = line;

                if (line.contains("killed by ") && line.contains(AK47)) {
                    // AK-47

                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = AK47;

                }

                if (line.contains("killed by ") && line.contains(AUG)) {
                    // AUG

                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = AUG;

                }

                if (line.contains("killed by ") && line.contains(M82A1)) {
                    // M82A1

                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = M82A1;
                }

                if (line.contains("killed by ") && line.contains(Magnum)) {
                    // Magnum
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Magnum;
                }
                if (line.contains("killed by ") && line.contains(DEagle)) {
                    // DEagle
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = DEagle;
                }

                if (line.contains("killed by ") && line.contains(Dragunov)) {
                    // Dragunov
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Dragunov;
                }

                if (line.contains("killed by ") && line.contains(Flashbang)) {
                    // Flashbang
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Flashbang;
                }

                if (line.contains("killed by ") && line.contains(G11)) {
                    // G11
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = G11;
                }

                if (line.contains("killed by ") && line.contains(G36)) {
                    // G36
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = G36;
                }

                if (line.contains("killed by ") && line.contains(G17SD)) {
                    // G17SD
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = G17SD;
                }
                if (line.contains("killed by ") && line.contains(M1014)) {
                    // M1014
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = M1014;
                }

                if (line.contains("killed by ") && line.contains(Jackhammer)) {
                    // JackhammerLog = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Jackhammer;
                }
                if (line.contains("killed by ") && line.contains(M16A2)) {
                    // M16A2
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = M16A2;
                }

                if (line.contains("killed by ") && line.contains(MAC10)) {
                    // MAC10
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = MAC10;
                }

                if (line.contains("killed by ") && line.contains(Makarov)) {
                    // Makarov
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Makarov;
                }

                if (line.contains("killed by ") && line.contains(Minimi)) {
                    // Minimi
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Minimi;
                }
                if (line.contains("killed by ") && line.contains(MP5A3)) {
                    // MP5A3
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = MP5A3;
                }

                if (line.contains("killed by ") && line.contains(PSG1)) {
                    // PSG1
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = PSG1;
                }
                if (line.contains("killed by ") && line.contains(SMG2)) {
                    // SMG2
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = SMG2;
                }

                if (line.contains("killed by ") && line.contains(Socom)) {
                    // Socom
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Socom;
                }

                if (line.contains("killed by ") && line.contains(Spas12)) {
                    // Spas12
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Spas12;
                }

                if (line.contains("killed by ") && line.contains(Type64SMG)) {
                    // Type64SMG
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Type64SMG;
                }
                if (line.contains("killed by ") && line.contains(Uzi)) {
                    // Uzi
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Uzi;
                }

                if (line.contains("killed by ") && line.contains(TwinUzi)) {
                    // TwinUzi
                    Log = Log.replaceAll("\\s*\\bkilled by\\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = TwinUzi;
                }
            }

            {     // Coward Kill

                String Log = line;

                if (line.contains("cowardly ") && line.contains(AK47)) {
                    // AK-47
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = AK47;
                }

                if (line.contains("cowardly ") && line.contains(AUG)) {
                    // AUG
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = AUG;
                }

                if (line.contains("cowardly ") && line.contains(M82A1)) {
                    // M82A1
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = M82A1;
                }

                if (line.contains("cowardly ") && line.contains(Magnum)) {
                    // Magnum
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Magnum;
                }
                if (line.contains("cowardly ") && line.contains(DEagle)) {
                    // DEagle
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = DEagle;
                }

                if (line.contains("cowardly ") && line.contains(Dragunov)) {
                    // Dragunov
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Dragunov;
                }

                if (line.contains("cowardly ") && line.contains(Flashbang)) {
                    // Flashbang
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Flashbang;
                }

                if (line.contains("cowardly ") && line.contains(G11)) {
                    // G11
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = G11;
                }

                if (line.contains("cowardly ") && line.contains(G36)) {
                    // G36
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = G36;
                }

                if (line.contains("cowardly ") && line.contains(G17SD)) {
                    // G17SD
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = G17SD;
                }
                if (line.contains("cowardly ") && line.contains(M1014)) {
                    // M1014
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = M1014;
                }

                if (line.contains("cowardly ") && line.contains(Jackhammer)) {
                    // Jackhammer
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Jackhammer;
                }
                if (line.contains("cowardly ") && line.contains(M16A2)) {
                    // M16A2
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = M16A2;
                }

                if (line.contains("cowardly ") && line.contains(MAC10)) {
                    // MAC10
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = MAC10;
                }

                if (line.contains("cowardly ") && line.contains(Makarov)) {
                    // Makarov
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Makarov;
                }

                if (line.contains("cowardly ") && line.contains(Minimi)) {
                    // Minimi
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Minimi;
                }
                if (line.contains("cowardly ") && line.contains(MP5A3)) {
                    // MP5A3
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = MP5A3;
                }

                if (line.contains("cowardly ") && line.contains(PSG1)) {
                    // PSG1
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = PSG1;
                }
                if (line.contains("cowardly ") && line.contains(SMG2)) {
                    // SMG2
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = SMG2;
                }

                if (line.contains("cowardly ") && line.contains(Socom)) {
                    // Socom
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Socom;
                }

                if (line.contains("cowardly ") && line.contains(Spas12)) {
                    // Spas12
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Spas12;
                }

                if (line.contains("cowardly ") && line.contains(Type64SMG)) {
                    // Type64SMG
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Type64SMG;
                }
                if (line.contains("cowardly ") && line.contains(Uzi)) {
                    // Uzi
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = Uzi;
                }

                if (line.contains("cowardly ") && line.contains(TwinUzi)) {
                    // TwinUzi
                    Log = Log.replaceAll("\\s*\\bcowardly \\b\\s*", "");

                    // Killed By [Killer]
                    String Filter_Killed_By = (Log.substring(Log.indexOf("by ") + 3));
                    String Killed_By = Filter_Killed_By.substring(0, Filter_Killed_By.indexOf("[") - 1);

                    // Died [Killed]
                    String Filter_Killed = Log.substring(0, Log.indexOf(" was"));
                    String Died_Killed = (Filter_Killed.substring(Filter_Killed.indexOf("]") + 2));

                    // Set Values
                    Died = Died_Killed;
                    Killed = Killed_By;
                    Gun = TwinUzi;
                }
            }

            { // Final Ranking

                if (Killed.equals("") || Died.equals("") || Gun.equals("")) {

                } else {
                    { // Rank Up Killer

                        String Data = "Query=Rank_Up" + "\n" + "Player_Name=" + Killed + "\n" + "Gun=" + Gun;

                        try {
                            InetAddress servAddr = InetAddress.getByName("localhost");
                            byte[] buf = Data.getBytes();
                            DatagramSocket socket = new DatagramSocket();
                            socket.send(new DatagramPacket(buf, buf.length, servAddr, 1000));
                            socket.close();
                        } catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
/*
                    { // Derank Death

                        String Data = "Query=Derank_Death" + "\n" + "Player_Name=" + Died;

                        try {
                            InetAddress servAddr = InetAddress.getByName("localhost");
                            byte[] buf = Data.getBytes();
                            DatagramSocket socket = new DatagramSocket();
                            socket.send(new DatagramPacket(buf, buf.length, servAddr, 1000));
                            socket.close();
                        } catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
*/
                    System.out.println("------------------");
                    System.out.println(Killed + " killed " + Died + " using " + Gun + "\n");
                    System.out.println("------------------");

                }
            }
        }
        
    }

}