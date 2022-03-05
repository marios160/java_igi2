#include "program_data.h"

program_data::program_data()
{
    players_list_read_from=1;
    all = new strc;
}

~program_data::program_data()
{
    delete all;
}

bool program_data::players_list_read_from_log()
{
    if(players_list_read_from == 1) return true;
    else return false;
}

bool program_data::players_list_read_from_svfinger()
{
    if(players_list_read_from == 0) return true;
    else return false;
}
