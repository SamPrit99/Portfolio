#include "pch.h"
#include <fstream>
#include <iostream>
#include "Player.h"
#include "RoomOrArea.h"

using namespace std;

//Adventure Game Created By: Sam Pritchard
//This is an escape game in which the hero must escape from a dragon's lair or slay the dragon. There are three ways to win.
//There are also two ways to die. The hero must travel through the castle and find clues and items to either escape or 
//defeat the dragon.

class Game
{
private:
	//all the constants and items needed for the game
	vector<RoomOrArea> areas;
	const int NUM_AREAS = 23;
	const int START_ROOM = 0;
	Player thePlayer;
	bool endOfGame;
	int timer = 0;
	int ram = 0;
	int crowbar = 0;
	int rope = 0;
	int shield = 0;
	int note = 0;
public:

	//creates the player
	void CreatePlayer()
	{
		//allows the player to type their own name
		cout << "Please enter your name:";
		string userName;
		getline(cin, userName);
		thePlayer.setName(userName); //Set the player's name
		thePlayer.setCurrentLocation(START_ROOM);
		thePlayer.setIsAlive(true);
		//cin.ignore(256, '\n'); //makes sure the buffer is clear

	}

	//creates the choices, the options a player can pick
	void CreateAreaChoices()
	{
		//creates the reader and the variables
		ifstream choicesFile;
		choicesFile.open("Choices.txt");
		string lineOfText;
		string currentChoices;

		//goes through for the number of areas that exist in the game
		for (int i = 0; i < NUM_AREAS; i++)
		{
			//starts at a line, so the while loop will work
			getline(choicesFile, lineOfText);
			//as long as not a blank line or the end will loop through and add options to the vector
			while (lineOfText != "" && !choicesFile.eof())
			{
				areas[i].addOptions(lineOfText);
				getline(choicesFile, lineOfText);
			}
		}
	}

	//creates the descriptions for the rooms
	void CreateAreaDescriptions()
	{
		//creates the reader and the variables
		ifstream storyFile; //stream reader to read the story
		storyFile.open("story.txt");
		string lineOfText;
		string currentStory;

		for (int i = 0; i < NUM_AREAS; i++)
		{
			//starts with a line so the while loop will work
			getline(storyFile, lineOfText);
			//as long as not at the end and not empty, will add to the string
			while (!storyFile.eof() && lineOfText != "")
			{
				currentStory = currentStory + lineOfText + "\n";
				getline(storyFile, lineOfText);

			}
			//adds to the vector the current string and clears it for the next room
			areas.push_back(RoomOrArea(currentStory));
			currentStory = "";
		}
		//closes the file
		storyFile.close();
	}

	//creates the connections for the rooms
	void CreateAreaConnections()
	{
		//makes the reader and the variables
		ifstream connectionsFile; //stream reader to read the story
		connectionsFile.open("Connections.txt");
		string lineOfText;
		string currentStory;

		for (int i = 0; i < NUM_AREAS; i++)
		{
			//starts at a line, so the while loop will work
			getline(connectionsFile, lineOfText);

			while (lineOfText != "" && !connectionsFile.eof())
			{
				areas[i].addConnection(stoi(lineOfText));
				//stoi to change string to character
				getline(connectionsFile, lineOfText);
			}
		}
	}

	//will allow the menu to display and the player to make a selection
	int * DisplayMenu()
	{
		//creates the choice variable
		int playerChoice[2];
		//checks the players current location and displays the correct description and options
		areas[thePlayer.getCurrentLocation()].displayDescription();
		//displays the area's options using the needed paramiters
		areas[thePlayer.getCurrentLocation()].displayOptions(thePlayer.getCurrentLocation(), timer, ram, crowbar, rope, shield, note);

		//to change the values that may be needed. when the player enters certain rooms they get certain items
		if (thePlayer.getCurrentLocation() == 6)
		{
			timer = 1;
		}
		else if (thePlayer.getCurrentLocation() == 7)
		{
			crowbar = 1;
		}
		else if (thePlayer.getCurrentLocation() == 13)
		{
			rope = 1;
		}
		else if (thePlayer.getCurrentLocation() == 15)
		{
			note = 1;
		}
		else if (thePlayer.getCurrentLocation() == 16)
		{
			ram = 1;
		}
		else if (thePlayer.getCurrentLocation() == 18)
		{
			shield = 1;
		}
		//checks if the player should die or the game should end
		else if (thePlayer.getCurrentLocation() == 19)
		{
			endOfGame = true;
		}
		else if (thePlayer.getCurrentLocation() == 20)
		{
			endOfGame = true;
		}
		else if (thePlayer.getCurrentLocation() == 22)
		{
			endOfGame = true;
		}
		else if (thePlayer.getCurrentLocation() == 11)
		{
			endOfGame = true;
			thePlayer.setIsAlive(false);
		}
		else if (thePlayer.getCurrentLocation() == 21)
		{
			endOfGame = true;
			thePlayer.setIsAlive(false);
		}
		else
		{
		}



		//as long as the game isnt over
		if (!endOfGame)
		{
			//tells them to make a selection
			cout << thePlayer.getName() << ", what will you choose to do?";
			//puts their choice in the int
			//playerChoice[0] is the players actual input
			cin >> playerChoice[0];

			//to ensure the inputed value is a number
			while (cin.fail())
			{
				cin.clear(); // clear input buffer to restore cin to a usable state
				cin.ignore(INT_MAX, '\n'); // ignore last input
				cout << "You can only enter numbers.\n";
				cout << "Enter a valid number.\n";
				cin >> playerChoice[0];
			}

			//checks the conditions of the room the player is in
			if (thePlayer.getCurrentLocation() == 1 && ram == 1 && timer == 1)
			{
				//converts the choice to the right array value

				//playerChoice[0] is the players actual input
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 3;
				}
				else 
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 3;
			}
			else if (thePlayer.getCurrentLocation() == 1 && ram == 1)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 2;
				}
				else 
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 2;
			}
			else if (thePlayer.getCurrentLocation() == 1)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else 
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 1;
			}
			else if (thePlayer.getCurrentLocation() == 2 && rope == 1 && crowbar == 1)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 2;
				}
				else //if (playerChoice < 1 || playerChoice > 3)
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 2;
			}
			else if (thePlayer.getCurrentLocation() == 2)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else //if (playerChoice < 1 || playerChoice > 2)
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 1;
			}
			else if (thePlayer.getCurrentLocation() == 9 && shield == 1 && note == 1)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 2;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 3;
				}
				else if (playerChoice[0] == 4)
				{
					playerChoice[0] = 4;
				}
				else if (playerChoice[0] == 5)
				{
					playerChoice[0] = 5;
				}
				else //if (playerChoice < 1 || playerChoice > 5)
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 5;
			}
			else if (thePlayer.getCurrentLocation() == 9 && note == 1)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 3;
				}
				else if (playerChoice[0] == 4)
				{
					playerChoice[0] = 4;
				}
				else if (playerChoice[0] == 5)
				{
					playerChoice[0] = 5;
				}
				else
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 5;
			}
			else if (thePlayer.getCurrentLocation() == 9)
			{
				//converts the choice to the right array value
				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 3;
				}
				else if (playerChoice[0] == 4)
				{
					playerChoice[0] = 4;
				}
				else 
				{
				}
				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = 3;
			}

			else if (thePlayer.getCurrentLocation() == 10 && shield == 1 && note == 1)
			{
			//converts the choice to the right array value
			if (playerChoice[0] == 1)
			{
				playerChoice[0] = 1;
			}
			else if (playerChoice[0] == 2)
			{
				playerChoice[0] = 2;
			}
			else if (playerChoice[0] == 3)
			{
				playerChoice[0] = 3;
			}
			else if (playerChoice[0] == 4)
			{
				playerChoice[0] = 4;
			}
			else //if (playerChoice < 1 || playerChoice > 5)
			{
			}
			//playerChoice[1] is the maximum (modified) input player can have
			playerChoice[1] = 4;
			}
			else if (thePlayer.getCurrentLocation() == 10 && note == 1)
			{
			//converts the choice to the right array value
			if (playerChoice[0] == 1)
			{
				playerChoice[0] = 0;
			}
			else if (playerChoice[0] == 2)
			{
				playerChoice[0] = 2;
			}
			else if (playerChoice[0] == 3)
			{
				playerChoice[0] = 3;
			}
			else if (playerChoice[0] == 4)
			{
				playerChoice[0] = 4;
			}
			else
			{
			}
			//playerChoice[1] is the maximum (modified) input player can have
			playerChoice[1] = 3;
			}
			else if (thePlayer.getCurrentLocation() == 10)
			{
			//converts the choice to the right array value
			if (playerChoice[0] == 1)
			{
				playerChoice[0] = 0;
			}
			else if (playerChoice[0] == 2)
			{
				playerChoice[0] = 2;
			}
			else if (playerChoice[0] == 3)
			{
				playerChoice[0] = 3;
			}
			else
			{
			}
			//playerChoice[1] is the maximum (modified) input player can have
			playerChoice[1] = 2;
			}
			else
			{
				//takes the user input and makes sure that its attached to the correct values from the array

				if (playerChoice[0] == 1)
				{
					playerChoice[0] = 0;
				}
				else if (playerChoice[0] == 2)
				{
					playerChoice[0] = 1;
				}
				else if (playerChoice[0] == 3)
				{
					playerChoice[0] = 2;
				}
				else if (playerChoice[0] == 4)
				{
					playerChoice[0] = 3;
				}
				else if (playerChoice[0] == 5)
				{
					playerChoice[0] = 4;
				}
				else if (playerChoice[0] == 6)
				{
					playerChoice[0] = 5;
				}


				//playerChoice[1] is the maximum (modified) input player can have
				playerChoice[1] = areas[thePlayer.getCurrentLocation()].getConnecitonSize() - 1;
				
			}

		}
		//returns choice
		return playerChoice;
	}

	void GameLoop()
	{
		while (!endOfGame && thePlayer.getIsAlive())
		{

			//displays the menu
			int * retUserSelection = DisplayMenu();
			//sets the players new location
			if (retUserSelection[0] > retUserSelection[1] || retUserSelection[0] < 0)
			{
				cout << thePlayer.getName() << "Please enter a valid input. \n";
			}
			else
			{
				thePlayer.setCurrentLocation(areas[thePlayer.getCurrentLocation()].getConnections(retUserSelection[0]));
			}



			//get location the description

		}
		if (endOfGame)
		{
			string end;
			cout << "THE END";
			cin >> end;
		}
	}
};

//Main function must be outside of the class will go through and call all the needed functions to get the game working
int main()
{
	Game theGame;
	theGame.CreatePlayer();
	theGame.CreateAreaDescriptions();
	theGame.CreateAreaConnections();
	theGame.CreateAreaChoices();
	theGame.GameLoop();
}