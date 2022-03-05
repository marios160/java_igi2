#ifndef ANALIZA_LOGA_H
#define ANALIZA_LOGA_H
#include <string>
#include "player.h"
#include "strc.h"
using namespace std;

class analiza_loga
{
public:
    analiza_loga();
    ~analiza_loga();

    void popped(string line, player *gracze, int *players);
    void created_locally(string line, string prev_line, player *gracze);
    void DeleteHandler(string line, player *gracze);
    void czat(int id, string line, player *gracze);
    void players(string line, player *gracze, int *players_to_read);

    int players_to_read;

private:
    strc *all;
};

#endif // ANALIZA_LOGA_H
