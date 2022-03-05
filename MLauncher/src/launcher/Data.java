/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Mateusz
 */
public class Data implements Serializable{
    String nick;
    String masterServer;
    static final long serialVersionUID = 1000000L;

    public Data(String nick, String server) {
        this.nick = nick;
        this.masterServer = server;
    }
    
    static public Data getConfFile()
    {
        File file2 = new File("mlauncher.mat");
        Data c = null;
        
        if(file2.exists())
        {
            ObjectInputStream pl2=null;
            try {
                pl2=new ObjectInputStream(new FileInputStream("mlauncher.mat"));
                c = (Data)pl2.readObject();
                pl2.close();
            } catch (IOException | ClassNotFoundException ex) {System.out.println("Get "+ ex);}
        }
        else
            c = new Data("David Jones [QML]","http://igi2uc.000webhostapp.com/igi2serverip.txt");
        
        return c;
    }
    
    static public void setClassFile(Data c)
    {
        ObjectOutputStream pl=null;
        try {
            pl = new ObjectOutputStream(new FileOutputStream("mlauncher.mat"));
            pl.writeObject(c);
            pl.flush();
            pl.close();
        } catch (FileNotFoundException ex) {System.out.println("Set "+ ex);}
         catch (IOException ex) { System.out.println("Set "+ ex);}
    }
    
    
}
