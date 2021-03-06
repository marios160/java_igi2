/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyze;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario PL
 */
public class ListsPlayers {
    
    public static List<Player> listPlayers = new ArrayList<>();
    public static List<Player> poppedPlayers = new ArrayList<>();

    public static void rmPlayer(Player player){
        listPlayers.remove(player);
    }
    public static void rmPlayer(String id){
        Player rm = null;
        for (Player p : listPlayers) {
            if (p.getId().equals(id)){
                rm = p;
                break;
            }
        }
        if (rm != null){
            listPlayers.remove(rm);
        }
    }
    
    public static void addPlayer(Player player){
        listPlayers.add(player);
    }
    
    public static Player getPlayerById(String id){
        for (Player p : listPlayers) {
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
    
    public static Player getPlayerByNick(String nick){
        for (Player p : listPlayers) {
            if (p.getNick().equals(nick)){
                return p;
            }
        }
        return null;
    }

   
}
