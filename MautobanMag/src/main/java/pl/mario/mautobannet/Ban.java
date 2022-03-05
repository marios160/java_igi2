/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.mautobannet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pl.mario.mautobannet.AutoBan.odkoduj;
import static pl.mario.mautobannet.AutoBan.time;
import static pl.mario.mautobannet.Strings.fcrasher;
import static pl.mario.mautobannet.Strings.*;

public class Ban extends Thread {

    static String ip="";
    
    private String cmd;
    private int method;
    private String ips;

    public Ban(String cmd, int method, String ips) {
        this.cmd = cmd;
        this.method = method;
        this.ips = ips; 
    }

    public Ban() {
    }
    
    
    
    

    public void run() {
        try {
            while (true) {
                synchronized (AutoBan.bans) {
                    if (!AutoBan.bans.isEmpty()) {
                        Ban ban = AutoBan.bans.poll();
                        System.out.println(ip);
                        if (!ban.ips.equals(ip) && !ban.ips.equals(AutoBan.ip)) {
                            //Runtime.getRuntime().exec(ban.cmd);
                            ip = ban.ips;
                            String ms = "";
                            switch (ban.method) {
                                case 1:
                                    ms = "Bugs port";
                                    break;
                                case 2:
                                    ms = "fs";
                                    break;
                                case 3:
                                    ms = "0 byte";
                                    break;
                                case 4:
                                    ms = "DDos";
                                    break;
                                case 5:
                                    ms = "Bugs time";
                                    break;
                            }
                            Console.setmessage(odkoduj(fcrasher) + "(" + ms + ")" + ban.ips);

                            FileWriter file = new FileWriter(odkoduj(bany), true);
                            PrintWriter plik = new PrintWriter(file);
                            plik.append(ban.cmd + "       #" + time() + odkoduj(powod) + "\n");
                            plik.close();
                        }
                    } else {
                        Thread.sleep(100);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Ban.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ban.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
