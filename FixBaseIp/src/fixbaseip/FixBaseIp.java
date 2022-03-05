/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixbaseip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateusz
 */
public class FixBaseIp {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        File base = new File("baseIP.txt");
        if (!base.exists()) {
            JOptionPane.showMessageDialog(null, "File baseIP.txt not found!");
            return;
        }
        Scanner record = new Scanner(base);
        while (record.hasNextLine()) {
            list.add(record.nextLine());
        }
        record.close();
        PrintWriter nbase = new PrintWriter(base);
        int i = 0;
        for (String s : list) {
            //System.out.println(i++);
            if (s.isEmpty() || s.length() < 8) {
                continue;
            }
            while (s.charAt(0) == ' ') {
                s = s.substring(1);
            }
            String ip = "";
            try {

                ip = s.substring(0, s.indexOf(" ")).trim();
            } catch (Exception e) {
                System.out.println(s);
            }
            if (checkIP(ip) == 0) {
                continue;
            }
            s = s.substring(ip.length());
            String nick = s.trim();
            String line = ip;
            while (line.length() < 17) {
                line = line.concat(" ");
            }
            line = line.concat(nick);
            nbase.append(line + "\n");

        }
        nbase.close();

    }

    static int checkIP(String ip) {

        String ip1 = "", ip2 = "", ip3 = "", ip4 = "";
        try {

            if (ip.length() > 15) {
                return 0;
            }
            char[] ipa = ip.toCharArray();
            int l = 0;
            for (char c : ipa) {
                if (c == '.') {
                    l++;
                }
            }
            int num = 0;
            switch (l) {
                case 0:
                    return 0;
                case 1:
                    ip1 = ip.substring(0, ip.indexOf("."));
                    num = 1;
                    if (ip.length() > ip1.length() + 1) {
                        ip2 = ip.substring(ip.indexOf(".") + 1);
                        num++;
                    }
                    break;
                case 2:
                    ip1 = ip.substring(0, ip.indexOf("."));
                    ip2 = ip.substring(ip.indexOf(".") + 1, ip.indexOf(".", 4));
                    num = 2;
                    if (ip.length() > ip1.length() + ip2.length() + 2) {
                        ip3 = ip.substring(ip.indexOf(".", 4) + 1);
                        num++;
                    }
                    break;
                case 3:
                    ip1 = ip.substring(0, ip.indexOf("."));
                    ip2 = ip.substring(ip.indexOf(".") + 1, ip.indexOf(".", 4));
                    ip3 = ip.substring(ip.indexOf(".", ip1.length() + 1) + 1, ip.lastIndexOf("."));
                    //System.out.println(ip3);
                    num = 3;
                    if (ip.length() > ip1.length() + ip2.length() + ip3.length() + 3) {
                        ip4 = ip.substring(ip.lastIndexOf(".") + 1);
                        num++;
                    }
                    break;
            }

            switch (num) {
                case 0:
                    return 0;
                case 1:
                    if (Integer.parseInt(ip1) > 0 && Integer.parseInt(ip1) < 256) {
                        return 1;
                    } else {
                        return 0;
                    }
                case 2:
                    if (Integer.parseInt(ip1) > 0 && Integer.parseInt(ip1) < 256) {
                        if (Integer.parseInt(ip2) >= 0 && Integer.parseInt(ip2) < 256) {
                            return 2;
                        } else {
                            return 0;
                        }
                    }
                    break;
                case 3:
                    if (Integer.parseInt(ip1) > 0 && Integer.parseInt(ip1) < 256) {
                        if (Integer.parseInt(ip2) >= 0 && Integer.parseInt(ip2) < 256) {
                            if (Integer.parseInt(ip3) >= 0 && Integer.parseInt(ip3) < 256) {
                                return 3;
                            } else {
                                return 0;
                            }
                        }
                    }
                    break;
                case 4:
                    if (Integer.parseInt(ip1) > 0 && Integer.parseInt(ip1) < 256) {
                        if (Integer.parseInt(ip2) >= 0 && Integer.parseInt(ip2) < 256) {
                            if (Integer.parseInt(ip3) >= 0 && Integer.parseInt(ip3) < 256) {
                                if (Integer.parseInt(ip4) >= 0 && Integer.parseInt(ip4) < 256) {
                                    return 4;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            //System.out.println(ip1+" "+ip2+" "+ip3);
            return 0;
        }
        return 0;
    }

}
