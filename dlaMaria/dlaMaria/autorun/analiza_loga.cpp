#include "analiza_loga.h"
#include <string>
#include <stdio.h>
#include "wytnij.h"
using namespace std;


analiza_loga::analiza_loga()
{
    players_to_read = 0;
}

analiza_loga::~analiza_loga()
{
    delete all;
}

void analiza_loga::popped(string line, player *gracze, int *players)
{
    //pobieramy sobie ID i IP gracza
    string id = wytnij(line, 53, ']');
    string ip = wytnij(line, (int)(55+id.length()), ':');

    //sprawdzamy czy jest poprawne, zeby nie bylo bugow
    int id_i = atoi(id.c_str());
    if(id_i > 34 || id_i < 1)

    all->info->players++;
}

void analiza_loga::created_locally(string line, string prev_line, player *gracze)
{


}

void analiza_loga::DeleteHandler(string line, player *gracze)
{


}

void analiza_loga::players(string line, player *gracze, int *players_to_read)
{


}

void analiza_loga::czat(int id, string line, player *gracze, bool czy_liczyc_kille_z_loga)
{


}
