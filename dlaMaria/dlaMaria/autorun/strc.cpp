#include "strc.h"

strc::strc()
{
    ust = new ustawienia;
    alog =  new analiza_loga;
    info =  new _server_info;
    event = new events;
}

~strc::strc()
{
    delete ust;
    delete alog;
    delete info;
    delete event;
}
