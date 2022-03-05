#ifndef _SERVER_INFO_H
#define _SERVER_INFO_H
#include <string>
using namespace std;

class _server_info
{
public:
    _server_info();

    string name;
    string mapa;
    string timeleft;
    string passwd;
    int uptime;
    int players;
    int maxplayers;
    int crashe;
};

#endif // _SERVER_INFO_H
