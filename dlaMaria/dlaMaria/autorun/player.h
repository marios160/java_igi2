#ifndef PLAYER_H
#define PLAYER_H
#include <string>
using namespace std;

class player
{
public:
    player();

    string id;
    int id_i;
    string nick;
    string ip;
    int team;
    int rcon;
    int warrnings;
    int votes;
    int frags;
    int deads;
    int votes_given[3];
    int last_given;
};

#endif // PLAYER_H
