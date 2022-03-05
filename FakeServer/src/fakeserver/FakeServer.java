/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakeserver;
import static fakeserver.Pakiety1.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Mateusz
 */
public class FakeServer {
    static Pakiety1 tab[];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            //Okno o = new Okno();
            //tab = new Pakiety1[100];
            for (int i = 0; i < 1; i++) {
                Pakiety1 x = new Pakiety1("Polski Serwer [TEST]", "Sandstorm", "0","16" ,"1.2");
                x.start();
            }
            //Pakiety1 p = new Pakiety1(26001);
           
            
            
          
        /*while(true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FakeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        
        
        
    }
    
    static void start(int il)
    {
        
        for(int i=0; i<il; i++){
            tab[i].start();
        }
         JOptionPane.showMessageDialog(null, "Servers ON");
        
    }
    
}
