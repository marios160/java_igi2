/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testinggooglevps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class Save extends Thread {

    Queue<String> pck;

    public Save(Queue<String> pck) {
        this.pck = pck;

    }

    public void run() {
        while (true) {
            try {
                synchronized (pck) {
                    if (pck.isEmpty()) {
                        Thread.sleep(3000);
                        continue;
                    }
                    File f = new File("pck.log");
                    if((f.length()/1024/1024) > 50){
                        f.renameTo(new File(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date())+".log"));
                    }

                    PrintWriter p = new PrintWriter(new FileWriter("pck.log", true));
                    while (!pck.isEmpty()) {
                        p.append(pck.poll());
                    }
                    p.close();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }
    
    

}
