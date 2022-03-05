#ifndef PROGRAM_DATA_H
#define PROGRAM_DATA_H
#include "strc.h"
using namespace std;

class program_data
{
public:
    program_data();
    ~program_data();

    bool players_list_read_from_log();
    bool players_list_read_from_svfinger();

private:
    bool players_list_read_from;        //finger or log
    strc *all;
};

#endif // PROGRAM_DATA_H
