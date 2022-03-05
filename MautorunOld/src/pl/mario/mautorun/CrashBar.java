/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.mautorun;

import static pl.mario.mautorun.Gui.crashbar;


/**
 *
 * @author Mateusz
 */
public class CrashBar extends Thread{
    static boolean var;
    
    @Override
    public void run() {
        var = true;
        for(int i=0; i<1000 && var; i++)
        {
            try {
                if(!Glowna.crash)
                    return;
                crashbar.setValue(i);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}
