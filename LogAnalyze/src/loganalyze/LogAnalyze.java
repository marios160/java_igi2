/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyze;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Mixer.Info;
import static loganalyze.ListsPlayers.*;

/**
 *
 * @author Mario PL
 */
public class LogAnalyze extends Thread{

    
    
    @Override
    public void run() {
        LogAnalyze.analizuj();
    }

    public static void analizuj() {
        String linia = "";
        long dl;
        long poz = 0;
        int l = 0;
        String pom1, pom2 = "";
        RandomAccessFile czyt = null;

        try {
            czyt = new RandomAccessFile("Multiplayer.log", "r");
            while (true) {

                dl = czyt.length();
                czyt.seek(poz);
                while (poz < dl) {
                    linia = czyt.readLine();
                    poz = czyt.getFilePointer();

                    //new player in queue
                    if (linia.indexOf("Popped new networkID for joining client:") > -1) {
                        String id, ip;
                        id = Cut.substr(linia, 53, "]");
                        ip = Cut.substr(linia, linia.indexOf("][") + 2, ":");
                        poppedPlayers.add(new Player(id, ip));
                    } //new player joined to server
                    else if (linia.indexOf("Client[") > -1 && linia.indexOf("created locally") > -1) {
                        if (linia.indexOf("[]") > -1) {
                            continue;
                        }

                        String id = Cut.substr(linia, 18, "]");
                        String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "] cre");
                        for (Player player : poppedPlayers) {
                            if (player.getId().endsWith(id)) {
                                listPlayers.add(new Player(player.getId(), player.getIp(), nick));
                                poppedPlayers.remove(player);
                                break;
                            }
                        }
                    } else if (linia.indexOf("][") == 13 || linia.indexOf("][") == 14) {
                        //player left
                        if (linia.indexOf(" left the game") > -1) {
                            String id = Cut.substr(linia, 12, "]");
                            String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "]");
                            rmPlayer(id);
                        } //lost connection
                        else if (linia.indexOf(" removed: Connection lost") > -1) {
                            String id = Cut.substr(linia, 12, "]");
                            String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "]");
                            rmPlayer(id);
                        } //kicked
                        else if (linia.indexOf(" kicked out of the game") > -1) {
                            String id = Cut.substr(linia, 12, "]");
                            String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "]");
                            rmPlayer(id);
                        }
                    } else if (linia.indexOf("Client_Deletehandler: Removing ") > -1) {
                        String id = Cut.substr(linia, 43, "]");
                        if (getPlayerById(id) != null) {
                            String nick = linia.substring(43 + id.length() + 1);
                            rmPlayer(id);
                        }
                    }else if (linia.contains("Client_Deletehandler: Removing ") > -1) {
                        String id = Cut.substr(linia, 43, "]");
                        if (getPlayerById(id) != null) {
                            String nick = linia.substring(43 + id.length() + 1);
                            rmPlayer(id);
                        }
                    }

                    if (linia.contains(" was ") && !linia.contains("smithereens")) {
                        String nick = Cut.substr(linia, 11, " was");
                        Player player1 = getPlayerByNick(nick);
                        Player player2;
                        if (player1 != null) {
                            System.out.println(linia);
                            String nick2 = linia.substring(linia.lastIndexOf("by ")+3, linia.indexOf(" [", linia.lastIndexOf("by ") + 1));
                            player2 = getPlayerByNick(nick2);
                            if (player2 != null) {
                                String weapon = linia.substring(linia.indexOf(player2.getNick()) + player2.getNick().length() + 2, linia.indexOf("]["));
                                String Data = "Query=Rank_Up" + "\n" + "Player_Name=" + player2.getNick() + "\n" + "Gun=" + weapon;
                                String Data_Derank = "Query=Derank" + "\n" + "Player_Name=" + player1.getNick();
                                sendStat(Data);
                                sendStat(Data_Derank);
                            }
                        }
                    }

                }

                Thread.sleep(1000);

            }
        } catch (IOException | InterruptedException ex) {
            try {
                czyt.close();
            } catch (IOException ex1) {
                System.out.println(ex1);
            }

        }

    }

    public static void sendStat(String data) {
        try {
            InetAddress servAddr = InetAddress.getByName("localhost");
            byte[] buf = data.getBytes();
            DatagramSocket socket = new DatagramSocket();
            socket.send(new DatagramPacket(buf, buf.length, servAddr, 1000));
            socket.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
