#ifndef USTAWIENIA_H
#define USTAWIENIA_H
#include <iostream>
#include "networkcfg.h"
#include "program_data.h"
using namespace std;

class ustawienia
{
public:
    ustawienia();
    networkcfg *serv;
    program_data *prog;
};

#endif // USTAWIENIA_H
