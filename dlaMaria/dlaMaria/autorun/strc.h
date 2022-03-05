#ifndef STRC_H
#define STRC_H
#include "player.h"
#include "ustawienia.h"
#include "analiza_loga.h"
#include "_server_info.h"
#include "events.h"

class strc
{
public:
    strc();
    ~strc();

    static player gracze;              //lista graczy
    static ustawienia *ust;            //ustawienia serwera i programu
    static analiza_loga *alog;         //klasa z metodami analizowania loga
    static _server_info *info;         //klasa przechowujaca akutalny /status/
    static events *event;              //struktura zdarzen
};

#endif // STRC_H
