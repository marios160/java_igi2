#include "ustawienia.h"
#include "networkcfg.h"
#include "program_data.h"
using namespace std;

ustawienia::ustawienia()
{
    serv = new networkcfg;
    prog = new program_data;
}
