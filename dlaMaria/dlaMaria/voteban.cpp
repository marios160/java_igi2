#include <iostream>
#include <string>
#include <fstream>
#include <stdlib.h>
#include <cstdlib>
#include <sys/time.h>
//#include <windows.h>		//do usuniecia

using namespace std;
	
	int liczba_graczy = 0;		//zmienna przechowujaca liczbe graczy
	int licznik_players = 0;	//zmienna przechowujaca liczbe graczy pozostala do przeczytania w bloku --Players
	string godzina;				//godzina w logu
	string prev_line;
	
	int vote_list[34];			//tablica przechowujaca ID zgloszonych graczy
	
	string  vban_ip[34];		//tablica przechowujaca IP zbanowanych graczy
	int 	vban_time[34];		//tablica przechwoujaca czas (w jednostce clock_t) wymagany do odbanowania

	//tablice przechowujace dane o graczach
	//numer komorki jest rowny ID gracza
		
	
	string 	tab_id[34];			// tabela przchowujaca ID gracza w formie stringu
	string 	tab_nick[34];		// tablea przechowujaca nick gracza
	string	tab_ip[34];			// tabela przechowujaca IP gracza
	int 	tab_rcon[34];		// tabela przechowujaca dane o tym, czy gracz o danym ID ma rcona
	int		tab_ascii[34][2];	// tabela przechowujaca polozenie gracza wyrazone jako 'n' oraz 'i'
	int 	tab_frag[34];		// score
	int 	tab_dead[34];		// score
	int 	tab_sk[34];			// spwan kille
	int		tab_voteban[34];	// ilosc glosow oddanych na gracza do zbanowania
	
	string 	ascii_nick[128][34];		// tablica przechowujaca nicki graczy poukladane alfabetycznie
	int 	ascii_rcon[128][34];		// tablica przechowujaca informacje czy odpowiadajacy polozeniem gracz ma rcona w postaci 0/1
	int 	ascii_length[128][34];		// tablica przechowujaca dlugosc nicku opowiadajacego gracza w tablici ascii_nick
	int 	ascii_players[128];			// tablica przechowuj¹ca informacje o tym, ilu jest graczy w tablicy ascii_rcon pod tym samym adresem
	int 	ascii_id[128][34];			// tablica przechowujaca id odpowiadajacego gracza w ascii_nick
	
	
	string killed_by[14] =
						{
							"illed by a shot in the chest by ",
							"illed by a shot in the arm by ",
							"illed by a shot in the leg by ",
							"illed in his spawn area by ",
							"illed by a shot in the groin by ",
							
							"shot in the head by ",
							"cowardly shot in the back and killed by ",
							"blown to smithereens by ",
							"stabbed to death by ",
							"pummeled to death by ",
							
							"blown to smithereens",
				
							"fell to his death",
							"suicided",
							"did a close inspection of a sentry gun",
						};




void lista_graczy()
{
	//WYPISYWANIE TABLIC GRACZY
		
	for(int i=1; i < 64; i++) 
	{
		if(tab_id[i] != "")
		{
			cout << "[" << tab_id[i] << "]";
			if(tab_rcon[i] == 1) cout << "*";
			else cout << " ";
			cout << tab_nick[i] << " [" << tab_ip[i] << "]" << endl;
		}
	}
}

void loguj(string text, string file)
{
	text += "\n";
	text = godzina + " " + text;
	ofstream plik(file.c_str(), ios::out | ios::app);
	plik.write(text.c_str(), text.length());
	plik.close();
}

string ii_wytnij(string txt, int poczatek, int koniec)
{
	if(koniec == 0) koniec = txt.length()-1;
	string txt2;
	for(int i=poczatek; i <= koniec; i++) txt2 += txt[i];
	return txt2;
}

string ic_wytnij(string txt, int poczatek, char koniec)
{
	string txt2;
	for(int i=poczatek; txt[i] != koniec; i++) txt2 += txt[i];
	return txt2;
}

string cc_wytnij(string txt, char poczatek, char koniec)
{
	string txt2;
	for(int i=0, stop=0; stop < 2; i++)
	{
		if(txt[i] == koniec) stop = 2;
		if(stop == 1) txt2 += txt[i];
		if(txt[i] == poczatek) stop = 1;
	} 
	return txt2;
}

string ci_wytnij(string txt, char poczatek, int koniec)
{
	string txt2;
	if(koniec == 0) koniec = txt.length()-1;
	for(int i=0, start=0; i <= koniec; i++)
	{
		if(start == 1) txt2 += txt[i];
		if(txt[i] == poczatek) start = 1;
	} 
	return txt2;
}

int find_repet(string line, char znak, int repet, int start_pos, bool if_back)
{
	int licznik=0;		//licznik powtorzen danego znaku
	
	if(!if_back)
	{
		for(int i=start_pos; i < line.length(); i++)
		{
			if(line[i] == znak) licznik++;
			if(licznik == repet) return i;
		}
	}
	
	if(if_back)
	{
		for(int i=line.length()-1; i > start_pos; i--)
		{
			if(line[i] == znak) licznik++;
			if(licznik == repet) return i;
		}
	}
	return 0;
}
void unban(string ip)
{
	string cmd="/sbin/iptables -D INPUT -s "+ip+" -j DROP";
	system(cmd.c_str());
}

void ban(string ip, string file)
{
    string cmd="/sbin/iptables -I INPUT -s "+ip+" -j DROP"; //tworzymy regu?ke
    //system(cmd.c_str());                                    //Wysy?amy regu?e do terminala czyli banujemy ip
    cout << cmd << endl;
    ofstream bany(file.c_str(),ios::out|ios::app);          //otwieramy banliste
    bany.write(cmd.c_str(),cmd.length());                   //dodajemy na koncu regu?ke
    bany.write("\n",1);
    bany.close();
}

bool szukaj(string line, string txt, int pos, int back)
{
	// line - linia z loga
	// txt - szukana fraza
	// pos - pozycja na ktorej szukana fraza ma sie zaczynac
	// back - czy masz szukac od tylu
	
	if(!back)
	{
		for(int i=pos; i < txt.length()+pos; i++) if(line[i] != txt[i-pos]) return false;
		return true;
	}
	else
	{
		for(int i=pos, j=txt.length()-1; j > -1; i--, j--) if(line[i] != txt[j]) return false;
		return true;
	}
}

int get_id(string nick)
{
	int n = nick[0];
	for(int i=0; i < ascii_players[n]; i++)
	{
		if(ascii_nick[n][i] == nick) return ascii_id[n][i];
	}
	return 0;
}



string pobierz_godzine(string line)
{
	string godzina;
	for(int i=0; i < 10; i++) godzina += line[i];
	return godzina;
}

void panel_admina(string line, int id)
{
	
}

void voteban_unban()
{
	//pobieramy aktualny czas
	clock_t time;
	time = clock();
	

	for(int i=0; vban_time[i] != 0; i++)
	{
		//jesli znajdzemy goscia ktorego juz trzeba odbanowac
		if(time >= vban_time[i])
		{
			unban(vban_ip[i]);
			
			//czyscimy miejsce w tabelkach dla nowych graczy
			vban_time[i] = 0;
			vban_ip[i] = "";
		}
	}
	
}
void voteban_check()
{
	//okreslamy jaka jest aktualnie wymagana ilosc glosow do zbanowania gracza
	
	int wymagana_ilosc;
	if(liczba_graczy > 0 && liczba_graczy < 6)  wymagana_ilosc = 2;
	if(liczba_graczy > 5 && liczba_graczy < 9)  wymagana_ilosc = 3;
	if(liczba_graczy > 8 && liczba_graczy < 12) wymagana_ilosc = 4;
	if(liczba_graczy > 11&& liczba_graczy < 15) wymagana_ilosc = 5;
	if(liczba_graczy > 14) 						wymagana_ilosc = 6;

	// sprawdzamy czy ktorys z graczy (odnotowanych) ma juz wystarczajaca liczbe glosow
	for(int i=0; tab_voteban[vote_list[i]] != 0; i++)
	{
		//jesli takiego znajdziemy
		if(tab_voteban[vote_list[i]] >= wymagana_ilosc)
		{	
			//szukamy dla niego pierwszego z brzegu miejsca
			int j=0;
			for(; vban_ip[j] != "" &&  vban_time[j] != 0; j++);
			
			//zapisujemy sobie
			vban_ip[j] = tab_ip[vote_list[i]];
			vban_time[j] = clock() + 15*60*CLOCKS_PER_SEC;
			
			//a teraz banujemy :P
			ban(tab_ip[vote_list[i]], "voteban.txt");
		}
	}
	
}

void voteban_cmd(string line, string nick)
{
	if(szukaj(line, "/voteban ", 13+nick.length()+2,  0))
	{
		string voted_id;


		//pobieramy ID votowanego gracza
		int i= 13+nick.length()+9;
		for(; line[i] > 47 && line[i] < 58; i++) voted_id += line[i];	
		
		cout << nick << ": /voteban " << voted_id << endl;
		if(i != line.length()) return;						//jesli poza id jest tam cos jeszcze to konczymy
			
		int voted_id_int;
		voted_id_int = atoi(voted_id.c_str());
		if(voted_id_int > 34 && voted_id_int < 1) return;	//jesli ta liczba jest wieksza jak mozliwe ID, to konczymy
		
		if(tab_id[voted_id_int] != voted_id) return;		//jesli nie ma gracza o podanym ID, to konczymy
		
		//jesli wszytko jest ok
		tab_voteban[voted_id_int]++;		//doliczamy nowy glos na konto tego gracza
		
		
		//jesli jest to pierwszy glos na tego gracza - to przechodzimy dalej, jesli nie - to konczymy
		if(tab_voteban[voted_id_int] != 1) return;
		
		//dodajemy gracza na liste votowancyh graczy
		for(int i=0; i < 65; i++)
		{
			if(vote_list[i] == 0)
			{
				vote_list[i] = voted_id_int;	// zapisujemy sobie ID, zeby potem szybko je sprawdzac
				i = 65;							// wychodzimy z petli :)
			}
		}
	}
	
}
void popped(string line)
{
	//pobieramy sobie ID i IP gracza
	string id;
	string ip;
	int i=53;
	
	for(; line[i] != ']'; i++)  id += line[i];
	i+=2;
	for(; line[i] != ':'; i++)  ip += line[i];
	//if(i != line.length()-1) return;
	
	//zapisujemy go do tabeli
	tab_id[atoi(id.c_str())] = id;
	tab_ip[atoi(id.c_str())] = ip;
	cout << "Popped:          [" << id << "][" << ip << "]" << endl;
	//odnotowujemy nowego gracza na serwerze
	liczba_graczy++;
	
	voteban_check();
}

void created_locally(string line)
{
	//pobieranie nicku i ID gracza
	int i=18;
	string id;
	string nick;
	
	for(; line[i] != ']'; i++) id += line[i];
	i+=2;
	for(; line[i] != ']'; i++) nick += line[i];
	
	cout << "Created locally: [" << id << "] " << nick << endl;
	//sprawdzenie czy jest to fake player czy nie
	if(!szukaj(prev_line, nick + " has joined the game[", 11, 0)) ban(tab_ip[atoi(id.c_str())], "bany.txt");
	
	//zapisywanie nicku do tablicy alfabetycznej
	int n = nick[0];	//zmienna bedaca pierwsza litera nicku	
	int pos=0; 			//zmienna przechowujaca pozycje na ktorej ma zostac zapisany nowy nick w kolumnie
	
	//jezeli uzyto znaku spoza kodu ASCII (pozycje 32-126) to taki nick laduje do specjalnej ostatniej komorki :)
	if(n > 126) n = 127;		

	//odnajdujemy miejsce w korym powinien zosac zapisany nick, aby te, byly ulozone od najduzszych do najkrotszych
	for(; ascii_length[n][pos] >= nick.length(); pos++);

	//przesuwamy o 1 miejsce w dol wszystkie krotsze nicki od naszego
	for(int i=ascii_players[n]-1; i > pos; i--)
	{
		ascii_nick[n][i+1] = ascii_nick[n][i];
		ascii_rcon[n][i+1] = ascii_rcon[n][i];
		ascii_length[n][i+1] = ascii_length[n][i];
	}
	
	//zapisujemy nowego gracza
	ascii_nick[n][pos] = nick;
	ascii_rcon[n][pos] = 0;
	ascii_length[n][pos] = nick.length();
	ascii_players[n]++;
	ascii_id[n][pos] = atoi(id.c_str());
	
	tab_nick[atoi(id.c_str())] = nick;
	tab_ascii[atoi(id.c_str())][0] = n;
	tab_ascii[atoi(id.c_str())][1] = pos;
	
}

void DeleteHandler(string line)
{
	liczba_graczy--;

	//pobieramy ID usuwanego gracza
	string s_id = ii_wytnij(line, 64, 0);
	int id = atoi(s_id.c_str());

	cout << "Usuwam [" << id << "]" << endl; 
	//czyscimy wszytkie tabelki z poprzednich danych
	tab_id[id] = "";
	tab_nick[id] = "";
	tab_rcon[id] = 0;
	tab_ip[id] = "";
	
	//pobieramy polozenie gracza w tabelach ascii
	int n = tab_ascii[id][0];
	int i = tab_ascii[id][1];
	
	
	//przesuwamy o 1 miejsce w gore wszystkie krotsze nicki od naszego
	int pos=i;
	for(int i=ascii_players[n]-1; i > pos; i--)
	{
		ascii_nick[n][i-1] = ascii_nick[n][i];
		ascii_rcon[n][i-1] = ascii_rcon[n][i];
		ascii_length[n][i-1] = ascii_length[n][i];
		ascii_id[n][i-1] = ascii_id[n][i];
	}
	ascii_players[n]--;
}

void Players(string line)
{
	string id;
	int rcon=0;
	int i=12;
	
	//zdobywamy ID
	for(; line[i] != ']'; i++)	id += line[i];
	
	//zdobywamy rcona
	i++;
	if(line[i] == '*') rcon = 1; 
	
	//zapisujemy rcona	
	tab_rcon[atoi(id.c_str())] = rcon;
	
	//logujemy dodanie admina
	if(tab_rcon[atoi(id.c_str())] == 0 && rcon == 1)
	{
		string txt = "Dodano Admina: [" + id + "] " + tab_nick[atoi(id.c_str())] + " [" + tab_ip[atoi(id.c_str())] + "]";
		loguj(txt, "log_reader.txt");
	}

	//odnotowanie faktu przeczytania kolejnej linijki bloku --Players
	licznik_players--;
}
void killed(string line, int id)
{
	string ofiara = tab_nick[id];
	string zabojca;
	int start;
	int koniec;
	int pos;
	
	if(szukaj(line, " was ", 11+ofiara.length(), 0))
	{
		if(szukaj(line, "k", 16+ofiara.length(), 0)) 
		{
			//   nick was killed ... by
			start = 0;	
			koniec = 5;
			pos = 17+ofiara.length();
		} 
		else
		{
			// nick was ... by (jest 1 wyjatek - was blow to smithereens )
			
			start = 5;
			koniec = 11;
			pos = 16+ofiara.length();
		}
	}
	else
	{
		// nick ...
		start = 11;
		koniec = 14;
		pos = 12+ofiara.length();
	}

	
	for(int i=start; i < koniec; i++)
	{
		if(szukaj(line, killed_by[i], pos, 0)) 
		{
			if(pos == 17+ofiara.length() || pos == 16+ofiara.length())			// was ... by lub was killed by
			if(i != 10)															// WYJATEK - was blown to smithereens
			{
				int poczatek_nicku = pos+ofiara.length() + killed_by[i].length() + 1;
				int koniec_nicku = find_repet(line, '[', 2, 11, 0);
				
				zabojca = ii_wytnij(line, poczatek_nicku, koniec_nicku);
				
				int zabojca_id = get_id(zabojca);
				
				tab_frag[zabojca_id]++;
				tab_dead[id]++;
				return;
			}
			else		// Obsluga wyjatka - was blown to smithereens
			{
				tab_dead[id]++;
				return;
			}
			
			if(pos == 12+ofiara.length())
			{
				tab_dead[id]++;
				return;
			}
		}
	}
}

bool czat(string line)
{
	int n = line[11];
	
	//sprawdzamy kolejnych graczy w tabeli
	for(int i=0; i < ascii_players[n]; i++)
	{
		bool stop = false;
		string nick = ascii_nick[n][i];
		
		//sprawdzamy czy nick na liscie to ten ktorego poszukujemy
		for(int j=11; j < nick.length()+11; j++) 
			if(line[j] != nick[j-11])
			{
				stop = true;		//jesli natrafilismy na niezgodnosc to przerywamy
				break;
			}
			
		if(stop == true) continue;		//przerywamy
		else if(line[11+nick.length()] == ':' && line[12+nick.length()] == ' ')			//jesli po nicku znajduje sie ": " to logujemy taka rozmowe					
			{
				string txt = ii_wytnij(line, 13+nick.length(), 0);
				loguj(nick + ": " + txt, "log_reader.txt");
				
				if(ascii_rcon[n][i] == 1 && line[13+nick.length()] == '/') panel_admina(txt, ascii_id[n][i]);
				else if(line[13+nick.length()] == '/') voteban_cmd(line, nick);
				
				return true;
			}
			else killed(line, ascii_id[n][i]);											//jesli nie, to patrzymy czy jest to tekst o zabojstwie
	}
	return false;
}

//#######################################################################################################################################################################################
int main()
{

	int obecnapoz=0, poprzedniapoz=0;

	string line;			//zmienna przechowujaca obecna linie pobrana z loga
	int players = 0;		//zmienna przchowjaca dane o tym czy natrafiono na --Players, czyli o tym, czy ma zapisaywac dane do tabel.

	
	if(1)
	//while(1)
	{
		
		//////////////////////////////    FRAGMENT ALUIGI    ////////////////////////////////////
		
		ifstream plik("Multiplayer.log");                   //otwarcie loga

       // if (!plik) continue;                                       //jesli nie ma pliku
        

        
        plik.seekg(0,ios::end);                             //ustawia pozycje czytania na koncu pliku
        obecnapoz=plik.tellg();                             // zapisuje do zmiennej pozycje w pliku
        if (obecnapoz>poprzedniapoz)                        //jesli pozycja aktualna jest wieksza niz poprzednia
            plik.seekg(poprzedniapoz);                      //to ustawia pozycje na poprzednia
        else       
            plik.seekg(0,ios::end);                         //a jesli nie to na koncu pliku
        poprzedniapoz=obecnapoz;                            // przepisuje aktualna pozycje do pozycji poprzedniej
        

        
        
        //////////////////////////// ANALIZA LOGA  ///////////////////////////////////////
        
        while(!plik.eof())
        {
        	getline(plik, line);
        	//if(line.length() > 0) cout << line << endl;
        
        	godzina = pobierz_godzine(line);			//pobieramy godzine z loga

			//voteban
			voteban_unban();

			// romowy graczy na czacie  +  score  +  komendy
        	if(ascii_players[line[11]] > 0) 
				if(czat(line)) continue;


        	// --Players
        	if(players == 1)												//jezeli --Players juz zostalo znalezione
			if(licznik_players > 0)											//jeszcze nie doszlismy do konca z odczytywaniem graczy 
        	if(line[11] == '[' && line[13] == ']' || line[14] == ']')		//jezeli linijka zaczyna sie od ID.
			{
				Players(line);
				continue;		//jesli jestesmy w bloku --Players to po co sprawdzac cala reszte ?
      		}
      		
      		
      		
      		
      		
        	if(szukaj(line, "Popped new networkID for joining client: [", 11, 0)) popped(line);
        	if(szukaj(line, "] created locally", line.length()-1, 1)) created_locally(line);
			if(szukaj(line,"Client_DeleteHandler: Removed from network queues,id=", 11, 0)) DeleteHandler(line);

      		
        	if(szukaj(line, "--Players", 11, 0) == true && line.length() == 20) 
			{
				players = 1;		//ta zmienna uruchamia czesc zapisujaca dane graczy
				licznik_players = liczba_graczy;	// ustawia licznik, zeby mozna bylo sobie odejmowac od niego :)
        	}
			
			prev_line = line;
		}
	}
}

