/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.igi2_ranking;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateusz
 */
public class Koder {

    
    public static String zakoduj(String txt)
    {
        char[] tab = txt.toCharArray();
        char [] tab2 = new char[txt.length()*3];

        for(int i=0, j=tab2.length-1; i<txt.length(); i++,j-=3)
        {
            int x = (int) tab[i];
            x =(x+70)*5;
            String l = Integer.toString(x);
            char[] tab3 = l.toCharArray();
            tab2[j] = tab3[0];
            tab2[j-1] = tab3[1];
            tab2[j-2] = tab3[2];
        }
        return new String(tab2);
    }
   
    public static String odkoduj(String txt)
    {
        char[] tab = txt.toCharArray();
        char [] tab2 = new char[txt.length()/3];
        for(int i=0, j=txt.length()-1; i<tab2.length; i++,j-=3)
        {
            String l = tab[j]+""+tab[j-1]+""+tab[j-2];
            System.out.println(l);
            int x = Integer.parseInt(l);
            x = (int)Math.ceil(x/5.0);
            x=x-70;
            tab2[i] = (char)x; 
        }
        return new String(tab2);
    }
}
