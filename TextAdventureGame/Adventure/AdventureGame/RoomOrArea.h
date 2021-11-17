#pragma once

#include <string>
#include <iostream>
#include <vector>
using namespace std;
/*******************************************************************************
	Class:  RoomOrArea -- Text adventure for a room.  Contains a description
	options and connecting rooms or areas
********************************************************************************/
class RoomOrArea
{
public:
	RoomOrArea();								//Default Constructor
	RoomOrArea(string pDescription);			//Overloaded Constructor to take a Description
	void displayDescription();					//Display the description of the room
	void displayOptions(int pLocation, int pTimer, int pRam, int pCrowbar, int pRope, int pShield, int pNote); 
	//Display the list of options for the room takes in parameters to determine when certain rooms need the have items to do things differently. Such as having the items needed to win the game
	void addOptions(string pOptions);	//Sets a vector of options
	void setConnections(vector<int>connections);//Sets a vector of connections
	void setDescription(string pDescription);
	string getDescription();					//Get the description for the room
	void addConnection(int pConnection);  //Read a connection from the Connections.txt
	int getConnections(int pSelection); //gets the connections
	int getConnecitonSize(); //to determine how many connections a room has
private:
	string description;			//Description of the room
	vector<string> options;		//A vector of options for each room
	vector<int> connections;	//A vector for the connections to other rooms	
};