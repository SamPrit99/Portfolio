#include "pch.h"
#include "RoomOrArea.h"


RoomOrArea::RoomOrArea()
{
	description = "";
	
}
RoomOrArea::RoomOrArea(string pDescription)
{
	description = pDescription;

}
void RoomOrArea::displayDescription()
{
	cout << description << endl;
}
void RoomOrArea::displayOptions(int pLocation, int pTimer, int pRam, int pCrowbar, int pRope, int pShield, int pNote)
{
	//room one with both items
	if (pLocation == 1 && pTimer == 1 && pRam == 1)
	{
		//if in room one and have both things they will have three otions. One causes victory
		cout << options[0] << endl;
		cout << options[1] << endl;
		cout << options[3] << endl;
	}
	//room one only battering ram
	else if(pLocation == 1 && pRam == 1)
	{
		//if in room one and have both things they will have three otions. One causes failure
		cout << options[0] << endl;
		cout << options[1] << endl;
		cout << options[2] << endl;
	}
	//if in room one with no items
	else if (pLocation == 1)
	{
		//if in room one and have none of the items they cannot escape.
		cout << options[0] << endl;
		cout << options[1] << endl;
	}
	//room two with both items
	else if (pLocation == 2 && pCrowbar == 1 && pRope == 1)
	{
		//if in room room two and have both items they can win
		cout << options[0] << endl;
		cout << options[1] << endl;
		cout << options[2] << endl;
	}
	//room 2 no items
	else if (pLocation == 2)
	{
		//if in room two and they dont have the items they cant win
		cout << options[0] << endl;
		cout << options[1] << endl;
	}
	//room nine with both items
	else if (pLocation == 9 && pShield == 1 && pNote == 1)
	{
		//if they have the shield. they will have only the option that allows them to win
		cout << options[0] << endl;
		cout << options[2] << endl;
		cout << options[3] << endl;
		cout << options[4] << endl;
		cout << options[5] << endl;
	}
	//room nine with only the note
	else if (pLocation == 9 && pNote == 1)
	{
		//if they have the shield. they will have only the option that allows them to get the shield and also die
		cout << options[0] << endl;
		cout << options[1] << endl;
		cout << options[3] << endl;
		cout << options[4] << endl;
		cout << options[5] << endl;
	}
	//room nine with no note
	else if (pLocation == 9)
	{
		//if they have the shield. they will have only the option that allows them to get the shield and also die
		cout << options[0] << endl;
		cout << options[1] << endl;
		cout << options[3] << endl;
		cout << options[4] << endl;
	}
	//room ten with note and shield
	else if (pLocation == 10 && pShield == 1 && pNote == 1)
	{
		//if they have the shield. they will have only the option that allows them to win
		cout << options[1] << endl;
		cout << options[2] << endl;
		cout << options[3] << endl;
		cout << options[4] << endl;
	}
	//room ten only note
	else if (pLocation == 10 && pNote == 1)
	{
		//if they have the shield. they will have only the option that allows them to get the shield and also die
		cout << options[0] << endl;
		cout << options[2] << endl;
		cout << options[3] << endl;
		cout << options[4] << endl;
	}
	//room ten nothing
	else if (pLocation == 10)
	{
		//if they have the shield. they will have only the option that allows them to get the shield and also die
		cout << options[0] << endl;
		cout << options[2] << endl;
		cout << options[3] << endl;
	}
	//for all other rooms
	else
	{
		for (int i = 0; i < options.size(); i++)
		{
			cout << options[i] << endl;
		}
	}
}
void RoomOrArea::addOptions(string pOptions)
{
	options.push_back(pOptions);
}
void RoomOrArea::setConnections(vector<int> pConnections)
{
	connections = pConnections;
}

void RoomOrArea::setDescription(string pDescription)
{
	description = pDescription;
}

string RoomOrArea::getDescription()
{
	return description;
}

void RoomOrArea::addConnection(int pConnection)
{
	connections.push_back(pConnection);
}

int RoomOrArea::getConnections(int pSelection)
{
	return connections[pSelection];
}

int RoomOrArea::getConnecitonSize()
{
	return connections.size();
}