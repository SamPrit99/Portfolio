#pragma once
using namespace std;
#include <vector>
#include <iostream>
#include <memory>

//an overloaded operator == is needed for the PullQueue for use in the Pull function

//struct for the Nodes of the list that makes up the queue. The Nodes act as each member of the list and each node points to the next
template <class DataType>
struct Node
{
	DataType info; //for the information the node has
	shared_ptr<Node<DataType>> next; //smart pointer to the next node
};

//class template for the actual PullQueue class
//template<class DataType> struct Node;
template <class DataType>
class PullQueue
{
	shared_ptr<Node<DataType>> front{ new Node<DataType> }; //pointer to the front of the list
	shared_ptr<Node<DataType>> back{ new Node<DataType> }; //pointer to the back of the list
	shared_ptr<Node<DataType>> head{ new Node<DataType> }; //header node

public:

	PullQueue()
	{
	}

	//dequeue to pull an item from the front of the queue
	bool dequeue(DataType & element)
	{
		shared_ptr<Node<DataType>> ptr{ front };
		//makes sure list isn't empty
		if (ptr != nullptr)
		{
			if (front->next == nullptr)
			{
				element = ptr->info;
				shared_ptr<Node<DataType>> temp{ new Node<DataType> };
				//front->info = temp->info;
				//front->next = nullptr;
				front = nullptr;
				head->next = nullptr;
			}
			else
			{
				//pulls the fist node off and returns to element by reference
				element = ptr->info;
				//removes it from the list
				front = front->next;
			}

			return true;
		}
		//returns false if there was a problem
		return false;
	}

	//to add an elemnt to the end of the queue
	void enqueue(DataType & element)
	{
		//makes a new node and sets the info of the node to be the passed in elemnt
		shared_ptr<Node<DataType>> newNode{ new Node<DataType> };
		newNode->info = element;
		//checks of the list is empty and updates the first node if it is
		if (head->next == nullptr)
		{
			//sets it to the first node if empty
			back->info = newNode->info;
			back->next = newNode->next;
			front = back;
			head->next = front; //makes the header node point to the first node
		}

		//all other nodes
		else
		{
			//moves the back pointer to point to the newNode
			back->next = newNode;
			back = newNode;
		}

	}


	//to pull the special item from the queue returns as the element passed in by reference uses the item to find the node to pull
	bool pull(DataType & item, DataType & pulled)
	{
		shared_ptr<Node<DataType>> ptr(front);
		/*uses a pointer to walk through the */
		while (ptr != nullptr)
		{
			//if the first node is what we're looking for
			if (item == ptr->info)
			{
				pulled = ptr->info;
				//if first node can just deqeue just like removing the front node any other time
				return dequeue(pulled);
			}
			//if the one node in the list is not the one you're looking for
			else if (ptr->next == nullptr)
			{
				return false; //item not found
			}
			//if any other node if the one we're looking for
			else if (item == ptr->next->info)
			{
				//sets pulled to be the node that will be removed
				pulled = ptr->next->info;

				//need to take into account the back node being removed
				if (ptr->next->next == nullptr)
				{
					//makes back the current ptr if removing the back node thus removing the node
					back = ptr;
					back->next = nullptr;
					return true; //success
				}
				//if not the back node
				else
				{
					//removes item from the list
					ptr->next = ptr->next->next;
					return true; //success
				}
			}
			//to progress to the next item in the list
			ptr = ptr->next;
		}
		//if no item meets the special condition return false
		return false;
	}

	//determines if the queue is empty
	bool isEmpty()
	{
		//if the header pointer points to nullptr, queue is empty
		return head->next == nullptr;

	}

	//to peek at the first node, but not remove the first node
	bool peek(DataType & element)
	{
		shared_ptr<Node<DataType>> ptr(front);
		//makes sure list isn't empty
		if (ptr != nullptr)
		{
			//returns to element by reference
			element = ptr->info;
			return true;
		}
		return false;
	}

	//to empty the queue
	void makeEmpty()
	{
		while (head->next != nullptr)
			head->next = head->next->next;
	}


	//temp for test purposes
	//void printPullQueue()
	//{
	//	cout << "pull queue:";
	//	shared_ptr<Node<DataType>> ptr(front);
	//	while (ptr != nullptr)
	//	{
	//		cout << "(" << ptr->info.x << "," << ptr->info.y << ")" << "->";
	//		ptr = ptr->next;
	//	}
	//	cout << "nullptr" << endl;
	//}

};