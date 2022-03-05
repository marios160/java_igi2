#include "player.h"
using namespace std;

player::player()
{
    id = "0";
    id_i = 0;
    nick = "Jones";
    ip = "0.0.0.0";
    team = -1;
    rcon = 0;
    warrnings = 0;
    votes = 0;
    frags = 0;
    deads = 0;
    votes_given[0] = 0;
    votes_given[1] = 0;
    votes_given[2] = 0;
    last_given = -1;
}

