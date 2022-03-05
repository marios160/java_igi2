/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.igi2_ranking;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public class Links {
    
    static String score;    
    static String profile;    
    static String staticprofile;
    static String donate;
    static String ip;
    static String heartbeat;
    static String login;
    static String register;
    static String checksum;
    static String mod;

    public Links() throws IOException {
        
        List<String> tab = Web.readTabHTML(Main.web+"links.txt");
        
        this.score = MarioEncoding.odkoduj(tab.get(0));
        this.profile = MarioEncoding.odkoduj(tab.get(1));
        this.donate = MarioEncoding.odkoduj(tab.get(2));
        this.ip = MarioEncoding.odkoduj(tab.get(3));
        this.heartbeat = MarioEncoding.odkoduj(tab.get(4));
        this.login = MarioEncoding.odkoduj(tab.get(5));
        this.register = MarioEncoding.odkoduj(tab.get(6));
        Links.checksum = MarioEncoding.odkoduj(tab.get(7));
        //this.mod = MarioEncoding.odkoduj(tab.get(8));
    }
    
    
}
