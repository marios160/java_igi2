/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptiondecoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Mateusz
 */
public class ExceptionDecoder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("MautorunExeption.log");
        if (!file.exists()) {
            return;
        }
        Scanner read = new Scanner(file);
        int i = 0;
        PrintWriter log = new PrintWriter(new FileWriter("Exeption.log", true));
        while (read.hasNextLine()) {
            log.append(odkoduj(read.nextLine())+"\n");

        }
        log.close();
        read.close();
    }

    public static String odkoduj(String txt) {
        char[] tab = txt.toCharArray();
        char[] tab2 = new char[txt.length() / 3];
        for (int i = 0, j = txt.length() - 1; i < tab2.length; i++, j -= 3) {
            String l = tab[j] + "" + tab[j - 1] + "" + tab[j - 2];
            int x = Integer.parseInt(l);
            x = (int) Math.ceil(x / 5.0);
            x = x - 70;
            tab2[i] = (char) x;
        }
        return new String(tab2);
    }

}
