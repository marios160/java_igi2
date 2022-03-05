#ifndef WYTNIJ_H
#define WYTNIJ_H
#include <string>
using namespace std;

string wytnij(string txt, int poczatek, int koniec=0)
{
	if(koniec == 0) koniec = txt.length()-1;
	string txt2;
	for(int i=poczatek; i <= koniec; i++) txt2 += txt[i];
	return txt2;
}

string wytnij(string txt, int poczatek, char koniec)
{
	string txt2;
	for(int i=poczatek; txt[i] != koniec; i++) txt2 += txt[i];
	return txt2;
}

string wytnij(string txt, char poczatek, char koniec)
{
	string txt2;
    for(int i=0, stop=0; i < (int)txt.length(); i++)
	{	
		if(txt[i] == poczatek && stop == 0)
		{
			stop = 1;
			continue;
		}
		if(txt[i] == koniec) break;
		if(stop == 1) txt2 += txt[i];
	} 
	return txt2;
}

string wytnij(string txt, char poczatek, int koniec=0)
{
	string txt2;
	if(koniec == 0) koniec = txt.length()-1;
	for(int i=0, start=0; i <= koniec; i++)
	{
		if(start == 1) txt2 += txt[i];
		if(txt[i] == poczatek) start = 1;
	} 
	return txt2;
}

#endif
