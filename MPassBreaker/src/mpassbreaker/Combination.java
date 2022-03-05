package mpassbreaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Combination extends Thread implements Serializable {

    public List<String> lista;
    public List<Character> lista2;
    public static char[] tab;

    public Combination() {
        this.lista = new ArrayList<>();
        this.lista2 = new ArrayList<>();
    }

    public Combination(int var) {
        Combination c = getClassFile();
        this.lista = c.lista;
        this.lista2 = c.lista2;
    }

    public void run() {
        tab = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        add("", 0);
        if (!BreakPass.responsed) {
            MPassBreaker.save = true;
        }
        System.out.println("Combination generated!");
    }

    public void add(String pass, int length) {
        if (length >= MPassBreaker.length) {
            return;
        }
        int size;
        synchronized (lista) {
            size = lista.size();
        }
        if (size > 1000000) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Combination.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!lista2.isEmpty()) {
            for (char letter : tab) {
                if (!lista2.isEmpty() && letter != lista2.get(0)) {
                    continue;
                }
                System.out.println(lista2.get(0));
                lista2.remove(0);
                System.out.println("xxx: " + pass);
                String passs = pass;
                passs += letter;
                synchronized (lista) {
                    lista.add(passs);
                }
                add(passs, length + 1);
            }
            return;
        }
        if(!lista2.isEmpty())
            return;

        for (char letter : tab) {
            if (!BreakPass.responsed) {
                if (!lista2.isEmpty()) {
                    return;
                }
                char[] x = pass.toCharArray();
                for (char c : x) {
                    synchronized (lista2) {
                        lista2.add(c);
                        System.out.println("lista2: " + c);
                    }

                }
                System.out.println(pass);
                return;
            }
            String passs = pass;
            passs += letter;
            synchronized (lista) {
                lista.add(passs);
            }
            add(passs, length + 1);
        }

    }

    public static Combination getClassFile() {
        File file2 = new File("passbreaker.cmb");
        Combination c = null;

        if (file2.exists()) {
            ObjectInputStream pl2 = null;
            try {
                pl2 = new ObjectInputStream(new FileInputStream("passbreaker.cmb"));
                c = (Combination) pl2.readObject();
                pl2.close();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error in getClassFile: " + ex);
            }
        } else {
            c = new Combination();
        }

        return c;
    }

    public static void saveClassFile(Combination c) {
        ObjectOutputStream pl = null;
        try {
            pl = new ObjectOutputStream(new FileOutputStream("passbreaker.cmb"));
            pl.writeObject(c);
            pl.flush();
            pl.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error in saveClassFile: " + ex);
        } catch (IOException ex) {
            System.out.println("Error in saveClassFile: " + ex);
        }
    }

}
