#ifndef EVENTS_H
#define EVENTS_H
#include <queue>
#include <time.h>
using namespace std;


class events
{
public:
    void add(int time, void(*f)(int, string*), int argc, string *argv, int repeat_t=0);		//dodaje nowe zdarzenie
	void check();			//sprawdza czas, i wykonuje zdarzenia
    void (*destruct_f)(int, string*);	//funkcja ktora jest bezwzglednie wykonywana przy niszczeniu obiektu - domyslnie unban
	
private:
	
	struct event
	{
		int time;		//godzina wykonania (jako CLOCKS)
		bool repeat;	//czy jest to komenda powtarzalna
		int repeat_t;	//czestotliwosc wykonywania tej komendy, jesli @up jest TRUE
        int argc;       //ilosc argumentow podawanych do funkcji
        string *argv;   //tablica z kolejnymi argumentami
        void (*funkcja)(int, string*);	//adres funkcji ktora ma zostac wykonana poprzez execute();
		
		void execute();	//metoda wykonujaca to zadanie np. odbanowuje gracza
	};
	
    //adapter kolejki priorytetowej, do porzadkowania zdarzen
	struct priority_queue_events_adapter
	{
   		bool operator ()( const event & a, const event & b )
   		{
			if(a.time > b.time) return true;
				else return false;
   		}
	};
	
	//kolejka priorytetowa przechowujaca kolejne zdarzenia
	priority_queue <event, vector<event>, priority_queue_events_adapter> events_queue;
	
public:
	~events();		//ten destruktor wykonauje wszystkie polecenia zawarte w kolejce, tzn odbanowuje wszystkich graczy
};

#include <iostream>

void events::add(int time, void(*f)(int, string*), int argc, string* argv, int repeat_t)
{
    event obj;
    obj.time = time + clock()/CLOCKS_PER_SEC;
    obj.repeat_t = repeat_t;
    obj.funkcja = f;
    obj.argc = argc;
    obj.argv = argv;
	
	events_queue.push(obj);
}

void events::check()
{
	clock_t time = clock() / CLOCKS_PER_SEC;	//aktualny czas
	
	while(!events_queue.empty() && events_queue.top().time <= time )
	{
		event obj = events_queue.top();
		events_queue.pop();	//zdejmujemy zdarzenie
		obj.execute();		//wykonaj zdarzenie
		
		if(obj.repeat_t != 0)
		{
			obj.time += obj.repeat_t;		//nowe zdarzenie wykona sie za repeat_t sekund
			events_queue.push(obj);
		}
	} 
}


void events::event::execute()
{
    funkcja(argc, argv);
}


events::~events()
{
	while(!events_queue.empty())
	{
        if(events_queue.top().funkcja == destruct_f && events_queue.top().repeat == 0)		//jesli nie ma sie ona powtarzac
		{
                event obj = events_queue.top();		//wykonujemy to zdarzenie
				obj.execute();
		}	
        events_queue.pop();	//wywalamy to zdarzenie
	}
}


#endif
