#pragma once

//Server group class
using namespace std;
#include <vector>
#include <iostream>

class ServerGroup
{
	vector<int> servers; //vector for the servers
	int freeServer; //if the server is free
	int spServer; //

public:

	bool spServerFree()
	{
		//checks if spSerevr is 0 returns true if it is
		if (spServer == 0)
		{
			return true; 
		}
		//if not 0, returns false
		else
		{
			return false;
		}
	}

	void useServer(int avTransTime)
	{
		//sets the value in the vector at freeServer to be equal to avTransTime
		servers[freeServer] = avTransTime;
	}

	void usespServer(int avTransTime)
	{
		//sets spServer equal to avTransTime
		spServer = avTransTime;
	}

	////temp for test purposes
	//void printServers()
	//{
	//	cout << "Servers:";
	//	for (int server : servers)
	//		cout << server << " ";
	//	cout << endl << "special purpose server: " << spServer << endl;
	//}

	ServerGroup(int pSize);
	bool serverFree();
	void decServers();

};

ServerGroup::ServerGroup(int pSize)
{
	//the constructor
	//resizes the vector to be the number decided by the client.
	servers.resize(pSize);
	spServer = 0; //sets spSever to 0

	//will loop through and set each value of the servers vector to 0
	for (int i = 0; i < servers.size(); i++)
	{
		servers[i] = 0;
	}
}

bool ServerGroup::serverFree()
{
	{
		//serverFree function
		//loops through the vector and retruns true if it finds a zero and breaks loop
		for (int i = 0; i < servers.size(); i++)
		{
			if (servers[i] == 0)
			{
				//sets freeSever to the index of the 0
				freeServer = i;
				return true;
				break;
			}
		}

		//if no zero found, returns flase
		return false;
	}
}

void ServerGroup::decServers()
{
	//used to decrement the servers
		//if spServer is not 0, decrease by 1
	if (spServer != 0)
	{
		spServer -= 1;
	}
	//loops through the servers vector
	for (int i = 0; i < servers.size(); i++)
	{
		//if value at i is not zero, decrease
		if (servers[i] != 0)
		{
			servers[i] -= 1;
		}
		else {} //else do nothing
	}
}

