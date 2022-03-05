/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class UpdatePlayers extends Thread {

    PlayersGui g;

    public UpdatePlayers(PlayersGui g) {
        this.g = g;
    }

    public void run() {

        try {
            while (true) {
                g.updateTable();
                Thread.sleep(3000);
            }
        } catch (InterruptedException ex) {
            System.out.println(UpdatePlayers.class.getName() + ex);
        }
    }

}
