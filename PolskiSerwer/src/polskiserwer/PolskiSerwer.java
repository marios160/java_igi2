package polskiserwer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static polskiserwer.Strings.*;

/**
 *
 * @author Mario PL
 */
public class PolskiSerwer {

    public static List<Server> lista;
    public static List<Server> oldList;
    static String version = "v1.0";
    static Data d;

    public static void main(String[] args) {
        try {
            String adres = Web.readHTML(odkoduj(adres1));
            if (adres.isEmpty()) {
                adres = Web.readHTML(odkoduj(adres2));
            } else if (adres.isEmpty()) {
                adres = Web.readHTML(odkoduj(adres3));
            } else if (adres.isEmpty()){
                JOptionPane.showMessageDialog(null, odkoduj(guiProblemAddress));
                return;
            }
            d = Data.getConfFile();
            Server s = new Server(odkoduj(adres));
            s.start();
            s.join();
            Gui gui = new Gui(s);
        } catch (InterruptedException ex) {
            System.out.println(PolskiSerwer.class.getName()+"\n"+ex);
        }
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
