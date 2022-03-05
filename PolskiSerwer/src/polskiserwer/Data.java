/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polskiserwer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static polskiserwer.Strings.*;

/**
 *
 * @author Mateusz
 */
public class Data implements Serializable{
    String nick;
    static final long serialVersionUID = 1000000L;

    public Data(String nick) {
        this.nick = nick;
    }
    
    static public Data getConfFile()
    {
        File file2 = new File(odkoduj(polskiserwermat));
        Data c = null;
        
        if(file2.exists())
        {
            ObjectInputStream pl2=null;
            try {
                pl2=new ObjectInputStream(new FileInputStream(odkoduj(polskiserwermat)));
                c = (Data)pl2.readObject();
                pl2.close();
            } catch (IOException | ClassNotFoundException ex) {System.out.println("Get "+ ex);}
        }
        else
            c = new Data(odkoduj(nickpl));
        
        return c;
    }
    
    static public void setClassFile(Data c)
    {
        ObjectOutputStream pl=null;
        try {
            pl = new ObjectOutputStream(new FileOutputStream(odkoduj(polskiserwermat)));
            pl.writeObject(c);
            pl.flush();
            pl.close();
        } catch (FileNotFoundException ex) {System.out.println("Set "+ ex);}
         catch (IOException ex) { System.out.println("Set "+ ex);}
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
