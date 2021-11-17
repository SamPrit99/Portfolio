//main program for the simulation. Will simulate customers waiting in line
#include <iostream>
#include <vector>
#include "PullQueue.h"
#include "ServerGroup.h"
#include <ctime>
using namespace std;

//to be used for the customer
struct Point
{
	int x; //the transaction type
	int y; //the time stamp when they joined the queue
	//overlaoded operator for the pull function
	bool operator == (Point p)
	{
		if (x == p.x)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
};

int main()
{
	srand(0); //seed for the random number
	//float(rand()) / RAND_MAX;

	//asks the user to input the values needed and sets them all to variables to be used later
	int length; //for the length of the simulation
	int serverNumber; //for the number of servers
	bool specialPurpose; //if there is a special purpose server
	int numberTransactions; //how many transactions
	vector<int> transLength; //length of each transaction
	int avgTime; //the average time between each customer

	cout << "How long is the simulation?\n";
	cin >> length;
	cout << "How many general purpose servers are there?\n";
	cin >> serverNumber;
	cout << "Is there a special purpose server? Please type Y for yes and N for no.\n";
	char specServ;
	cin >> specServ;
	//if they said yes, set special purpose to true, if not, set to false
	if (specServ == 89)
		specialPurpose = true;
	else if(specServ == 78)
		specialPurpose = false;
	cout << "How many transactions?\n";
	cin >> numberTransactions;
	//updates the size of the number of Transitons based on what the user said.
	transLength.resize(numberTransactions); 

	for (int x = 0; x < transLength.size(); x++)
	{
		//goes and asks the user for each transaction's length and assigns it to the value in the vector
		cout << "Length of transaction " << x << " ";
		cin >> transLength[x];
	}
	//asks the average time between each customer and sets that equal to the avgTime
	cout << "What is the average time between each customer?\n";
	cin >> avgTime;
	
	//creates a ServerGroup object of the number of servers.
	ServerGroup servers(serverNumber);
	//makes the PullQueue object
	PullQueue<Point> line;
	double arrivalProb = 1.0 / avgTime; //the probablility that a customer will arrive to the queue

	double totalWaitTime = 0; //the total wait time for all the customers
	int customersServerd = 0; //the total number of customers served

	//the simulation loop
	//starts the clock at 0 and continues as long as the clock is less than or equal to the length
	for (int clock = 0; clock < length; clock++)
	{
		float chance = float(rand()) / RAND_MAX;

		//to add a new customer
		if (chance <= arrivalProb) //if the arrival add a customer
		{
			Point newCustomer;
			int transactionType = rand() % numberTransactions; //pulls a random transtion type
			newCustomer.x = transactionType; //sets their transaction type
			newCustomer.y = clock; //sets the time they joined the line
			line.enqueue(newCustomer); //adds them to the line
		}

		//to serve a customer
		if (specialPurpose == true) //checks if there was a special condition
		{
			if (servers.spServerFree() == true) //checks if the special server is free
			{
				Point pulledPoint; //the point that will be pulled from the queue
				Point check; //to check 
				check.x = 0; //0 indicates the special purpose
				if (line.pull(check, pulledPoint) == true) //pulls a point from the queue
				{
					double wait = clock - pulledPoint.y;
					totalWaitTime = totalWaitTime + wait; //updates the total wait time of the customers
					customersServerd = customersServerd + 1; //updates the customers served
					int timeNeeded = transLength[0]; //gets the time needed for the transaction
					servers.usespServer(timeNeeded); //uses the spServer for the time needed
				}
			}
		}

		if (servers.serverFree() == true) //checks if a server is free
		{
			//checks if the queue has customers
			if (line.isEmpty() == false)
			{
				Point pulledPoint; //the point pulled from the queue
				line.dequeue(pulledPoint); //pulls the first item from the queue
				double wait = clock - pulledPoint.y;
				totalWaitTime = totalWaitTime + wait; //updates the total wait time of the customers
				customersServerd = customersServerd + 1; //updates the customers served
				int timeNeeded = transLength[pulledPoint.x]; //gets the length of the transaction needed
				servers.useServer(timeNeeded); //uses the server for the time needed
			}
		}

		//decrements the servers
		servers.decServers();
	}

	//to calculate the average wait time
	double avgWaitTime = totalWaitTime / customersServerd;

	//output at the end of the simulation
	if (specialPurpose == true)
	{
		cout << "special purpose server enabled\n";
	}
	else
	{
		cout << "no special purpose server\n";
	}
	cout << "result:\n"
		"average time is:" << avgWaitTime;
	
	getchar();
	getchar();
	
}