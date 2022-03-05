
package pl.mario.mautorun;

/**
 * Klasa z pomocnymi metodami do wycinania
 * @author Mario PL
 */
public class Wytnij {
    
    static String wytnij(String txt, int poczatek)
    {
	return txt.substring(poczatek);
    }
    
    static String wytnij(String txt, int poczatek, int koniec)
    {
	return txt.substring(poczatek, koniec);
    }
    
    static String wytnij(String txt, int poczatek, String koniec)
    {
        if(txt.indexOf(koniec,poczatek+1) < 0)
            System.out.println(txt);
        return txt.substring(poczatek,txt.indexOf(koniec,poczatek+1));
    }
    
    static String wytnij(String txt, String poczatek)
    {
	 return txt.substring(txt.indexOf(poczatek));
    }

    static String wytnij(String txt, String poczatek, String koniec)
    {
	 return txt.substring(txt.indexOf(poczatek),txt.indexOf(koniec,txt.indexOf(poczatek)+1));
    }

    static String wytnij(String txt, String poczatek, int koniec)
    {
	return txt.substring(txt.indexOf(poczatek),koniec);
    }
    
    static int count(String linia, char z)
    {
        int licz=0;
        linia = linia.trim();
        char[] lin = linia.toCharArray();
        for(char zm: lin)
        {
            if(zm == z)
                licz++;
        }
        return licz;
    }
    
}
