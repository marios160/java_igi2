#include "mainwindow.h"
#include <QApplication>

#include <iostream>
#include <cstdio>
#include <string>
#include <fstream>
#include <cstdlib>

#include "wytnij.h"
#include "events.h"
#include "player.h"
#include "ustawienia.h"
#include "analiza_loga.h"
#include "_server_info.h"
#include "funkcje_dodatkowe.h"
using namespace std;


player gracze[35];                  //lista graczy
ustawienia *ust = new ustawienia;   //ustawienia serwera i programu
analiza_loga alog;                  //klasa z metodami analizowania loga
_server_info info;                  //klasa przechowujaca akutalny /status/
events event;                       //struktura zdarzen



int main(int argc, char *argv[])
{
    //budujemy okienko
    QApplication a(argc, argv);
    MainWindow w;
    w.show();

    //analiza loga, czyli glowna petla programu

    int obecnapoz=0, poprzedniapoz=0;
    while(1)
    {
        string line;
        string prev_line;

        //ewentualne zatrzymanie pracy programu
        //if(gen.stop == true) continue;

        event.check();  //sprawdzamy czy nie ma zdarzen do wykonania

        ifstream plik("Multiplayer.log");	//otwarcie loga
        if (!plik) continue;				//jesli nie ma pliku

        plik.seekg(0,ios::end);				//ustawia pozycje czytania na koncu pliku
        obecnapoz = plik.tellg();			// zapisuje do zmiennej pozycje w pliku
        if(obecnapoz > poprzedniapoz)		//jesli pojawily sie nowe linijki
        {
            plik.seekg(poprzedniapoz);		//to ustawia pozycje na poprzednia
            poprzedniapoz=obecnapoz;		// przepisuje aktualna pozycje do pozycji poprzedniej
        }
        if(obecnapoz < poprzedniapoz)		//jesli linijek ubylo - nowy log - crash serwera
        {
            poprzedniapoz=obecnapoz;		// przepisuje aktualna pozycje do pozycji poprzedniej

            continue;
        }
        if(obecnapoz == poprzedniapoz)		//jesli nie pojawily sie zadne nowe linijki
        {
            plik.seekg(0,ios::end);			//a jesli nie to na koncu pliku
            poprzedniapoz=obecnapoz;		// poprzedniapoz=obecnapoz;
            continue;
        }


        //################################################ ANALIZA LOGA ######################################################//

        while(!plik.eof())
        {
            event.check();  //sprawdzamy czy nie ma zdarzen do wykonania

            getline(plik, line);				//pobieramy kolejna linie loga
            if(line.length() < 1) continue;		//jesli pobralismy jakas niedojebana linie to nie sprawdzamy jej

            //--Players, jesli czytamy liste graczy z loga
            if(ust->prog->players_list_read_from_log() && alog.players_to_read > 0)
            {
                alog.players(line, gracze, &alog.players_to_read);
                continue;   //po co sprawdzac cala reszte ?
            }

            //czat
            for(int i=1; i < 35; i++)
                if(find_pos(line, gracze[i].nick, 11))
                    alog.czat(i, line, gracze);


            if(find_pos(line, "Popped new networkID for joining client: [", 11))
                alog.popped(line, gracze, &info.players);

            if(find_pos(line, "] created locally", line.length()-1, 1))
                alog.created_locally(line, prev_line, gracze);

            if(find_pos(line, "Client_DeleteHandler: Removed from network queues,id=", 11))
                alog.DeleteHandler(line, gracze);

            if(find_pos(line, "--Players", 11) && line.length()==20 && ust->prog->players_list_read_from_log())
                alog.players_to_read = info.players;
        }

    }

    return a.exec();
}
