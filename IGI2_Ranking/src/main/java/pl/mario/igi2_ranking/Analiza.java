
package pl.mario.igi2_ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 *
 * @author Mateusz
 */
public class Analiza {
    
   
    public Analiza() throws FileNotFoundException {
        PrintWriter p = new PrintWriter(new File("Multiplayer.log"));
    }

}
