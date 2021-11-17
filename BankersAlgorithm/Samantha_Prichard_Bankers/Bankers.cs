using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

/***
 * Samantha Pritchard
 * March 26, 2021
 * This is the bankers algorithm. It takes a process and resources from the user, reads in the needed information from a text file and determines if 
 * the processes can finish in a safe state based on the resouces asked for by the process the user selected.
 * It displays a safe sequence if one exists and tells the user there is no safe sequence if none exist.
 */

namespace Samantha_Prichard_Bankers
{
    public partial class Bankers : Form
    {
        private List<string> fileLines = new List<string>(); //list that will hold all of the lines read in from the textbox
        private int numProcesses; //the number of processes
        private int numResourceType; //the number of resource types
        private int[] instances; //the instances for each resource
        private int[,] allocation; //array of the allocation values
        private int[,] max; //array of the max values
        private int[,] need; //array of the need values
        private int[] available = new int[3]; //array of the available resources of each type
        bool[] finish = new bool[5];
        private bool isSafe = true;
        private string safeSate; //the safe state that will be given to the user

        public Bankers()
        {
            InitializeComponent();
        }

        private void btnFile_Click(object sender, EventArgs e)
        {
            //clear all the list boxes
            ClearListBoxes();
            safeSate = "";
            txtOutput.Text = "";
            isSafe = true;

            try
            {
               //make a streamreader to read the content of the text file
                using (StreamReader sr = new StreamReader("data.txt"))
                {
                    string line; //to store the line read in
                    
                    //go throuh the text file until it reaces the end
                    while ((line = sr.ReadLine()) != null)
                    {
                        fileLines.Add(line); //add the line read in to the list we have 
                    }
                }
            }
            //incase there was a probelm
            catch (Exception ex)
            {
                //display the error message
                MessageBox.Show("The file could not be read:\n" + ex.Message);
            }

            //once it has read in everything we need from the file, seperate the different components as needed
            //first two entries in the list are the number of processes and the number of resource types respectively 
            numProcesses = int.Parse(fileLines[0]);
            numResourceType = int.Parse(fileLines[1]);

            //display in list box for debugging
            lstFile.Items.Add("Processes: " + numProcesses);
            lstFile.Items.Add("Resource Types: " + numResourceType);

            //instances for R0, R1, and R2
            string[] tempInstances = fileLines[2].Split(' '); //split the row read in on spaces
            instances = new int[tempInstances.Length]; //int array of instances
            //got through the string array
            for(int i = 0; i<tempInstances.Length; i++)
            {
                instances[i] = int.Parse(tempInstances[i]); //asssign the numbers to the int array as they were in string array
            }

            //display in list box for debugging
            for (int i = 0; i < instances.Length; i++)
            {
                lstFile.Items.Add("Total of resource " + i + " is: " + instances[i].ToString());
            }

            //get the allocation values for the different resources. This will be a 2D array that comes from lines 3 through 7 in the list of strings
            //array is set up such that the first index of the array in the process id, and the second is the resource id
            //first need to make an int array of all the numbers that were read in from the file so they can be assigned in the 2d array

            //goes through and creates the array for the needed parts
            allocation = new int[5, 3]; //array that will be used for the allocation 

            int stringLine = 3; //the line that will be used to get the value 
            for (int i = 0; i < 5; i++)
            {              
                for (int j = 0; j < 3; j++)
                {
                    string[] allocationLine = fileLines[stringLine].Split(' '); //make a 1d array of strings that will become the neded int array
                    allocation[i, j] = int.Parse(allocationLine[j]); //add the needed value to the list
                }
                stringLine++; //increase the line we will be reading in from
            }

            //read in the max 2d array almost exactly how the allocation array was read in
            max = new int[5, 3]; //array that will hold the max values
            int stringMax = 8; //line we need to start at
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    string[] stringMaxValues = fileLines[stringMax].Split(' '); //make a 1d array of strings that will become the neded int array
                    max[i, j] = int.Parse(stringMaxValues[j]); //add the needed value to the list
                }
                stringMax++; //increase the line we will be reading in from
            }

            //display in listbox for debugging
            for (int i = 0; i < 5; i++)
            {
                lstMax.Items.Add("Process " + i + ": ");
                string maxNumbers = "";
                for (int j = 0; j < 3; j++)
                {
                    maxNumbers = maxNumbers + max[i, j] + " ";
                }
                lstMax.Items.Add(maxNumbers);
            }

            need = new int[5, 3]; //the need array
            CalculateNeed();

            //calculate the available
            CalculateAvailable();
            //display in listbox for debugging
            for (int i = 0; i < available.Length; i++)
            {
                lstAvailable.Items.Add(available[i].ToString());
            }

            //button calls the resource request function
            ResourceRequest();
        }

        private void CalculateAvailable()
        {
            //loop through the allocation function and add together all the values that have the same i value
            int[] eachAllocation = new int[5]; //where the summations will be stored
            for (int i = 0; i < 3; i++)
            {
                int sum = 0;
                for (int j = 0; j < 5; j++)
                {
                    sum = sum + allocation[j, i];
                }
                eachAllocation[i] = sum; //add to the array 
            }

            for(int i = 0; i<instances.Length; i++)
            {
                available[i] = instances[i] - eachAllocation[i];
            }
        }

        //clears all the list boxes
        private void ClearListBoxes()
        {
            lstAllocation.Items.Clear();
            lstAvailable.Items.Clear();
            lstFile.Items.Clear();
            lstNeed.Items.Clear();
            lstMax.Items.Clear();
            
        }

        //calculate need 
        private void CalculateNeed()
        {
            //the need values are calculated by max - allocation. Need is also a 2d array with the same size as the arrays that produce it.
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //calculate the need value based on the other two for any given location
                    need[i, j] = max[i, j] - allocation[i, j];
                }
            }

            //display in listbox for debugging
            for (int i = 0; i < 5; i++)
            {
                lstNeed.Items.Add("Process " + i + ": ");
                string needNumbers = "";
                for (int j = 0; j < 3; j++)
                {
                    needNumbers = needNumbers + need[i, j] + " ";
                }
                lstNeed.Items.Add(needNumbers);
            }
        }

        /***
         * Read in the text from the textbox. Store an array of the asking resources. 
         * Check if the asking resources as less than the need values for that process, if it is, stop there and tell user
         * if not, check if those resources are less than the available resources, if not, stop there and tell user
         * if it is less than both, increase the allocation for that process and decrease the available for each to reflect that
         * Run the safe state algorithm and see if there is a safe state
         */

        //the resource request algorithm
        private void ResourceRequest()
        {
            //read in the values the user has inputed
            int selctedProcess = 0; //the process the user selected
            int[] request = new int[3]; //the resources that process is requesting
            try
            {
                selctedProcess = int.Parse(txtProcess.Text); //save the process the user selected
                string[] resourcesAsked = txtResources.Text.Split(' '); //an array of the resources the process is requesting
                for(int i = 0; i < resourcesAsked.Length; i++)
                {
                    request[i] = int.Parse(resourcesAsked[i]); //convert the resources to int
                }
            }
            catch
            {
                txtOutput.Text = "Please follow example for formatting input.";
            }

            //check if it is requesting more than it needs
            if(request[0] > need[selctedProcess, 0] || request[1] > need[selctedProcess, 1] || request[2] > need[selctedProcess, 2])
            {
                txtOutput.Text = "Process has requested more than it's needed value, no safe state available.";
            }
            //check if it is asking for more than there is available
            else if(request[0] > available [0] || request[1] > available[1] || request[2] > available[2])
            {
                txtOutput.Text = "Process has requested more resources than available, it must wait to request these resources.";
            }
            //if it can make this request and we have enough resources
            else
            {
                

                //update the allocation for that process
                for(int i = 0; i < request.Length; i++)
                {
                    allocation[selctedProcess, i] = allocation[selctedProcess, i] + request[i]; //increase the allocation for the request
                    available[i] = available[i] - request[i]; //decrease the available resources based on the new allocation
                    //need[selctedProcess, i] = need[selctedProcess, i] - request[i]; //update the need for the selected process

                }

                CalculateNeed();
                lstNeed.Items.Clear();
                lstAllocation.Items.Clear();
                lstAvailable.Items.Clear();
                //print in list box for debugging
                for (int i = 0; i < 5; i++)
                {
                    lstAllocation.Items.Add("Process " + i + ": ");
                    string allocationNumbers = "";
                    for (int j = 0; j < 3; j++)
                    {
                        allocationNumbers = allocationNumbers + allocation[i, j] + " ";
                    }
                    lstAllocation.Items.Add(allocationNumbers);
                }
                for (int i = 0; i < available.Length; i++)
                {
                    lstAvailable.Items.Add(available[i].ToString());
                }
                //display in listbox for debugging
                for (int i = 0; i < 5; i++)
                {
                    lstNeed.Items.Add("Process " + i + ": ");
                    string needNumbers = "";
                    for (int j = 0; j < 3; j++)
                    {
                        needNumbers = needNumbers + need[i, j] + " ";
                    }
                    lstNeed.Items.Add(needNumbers);
                }

                SafeState(); //call the safe state algorithm

            }
        }

        /***
         * Loop through the list of finish. If finish is false, check if available is less than need for that process.
         * If available is less than need for that process, set finish to true, add the process name to the safe state list, and add the need for that process to available
         * repeat. 
         * End early if you get to end of the list and there is still values that are not finished, but none have available less than need, cannot have safe sate
         */
        //the safe state algorithm
        private void SafeState()
        {
            //finish is an array of bool data types that are all false until they have conpleted. One for each process
            //the finish array for the safety algorithm needs to all be set to false
            for (int i = 0; i < finish.Length; i++)
            {
                finish[i] = false;
            }

            for (int i = 0; i < finish.Length; i++)
            {
                if (!finish[i])
                {
                    int[] processNeed = new int[3]; //the array of the need values for the current process
                    for (int j = 0; j < 3; j++)
                    {
                        processNeed[j] = need[i, j]; //assign the value to the process needed
                    }
                    //if the needed processs are less than the available processes for all processes
                    if (processNeed[0] <= available[0] && processNeed[1] <= available[1] && processNeed[2] <= available[2])
                    {
                        finish[i] = true; //allow that process to finish
                                          //add all of the newly freed resources
                        available[0] = available[0] + max[i,0];
                        available[1] = available[1] + max[i, 1];
                        available[2] = available[2] + max[i, 2];
                        need[i, 0] = 0;
                        need[i, 1] = 0;
                        need[i, 2] = 0;
                        safeSate = safeSate + i + " "; //add the current process to the safe sate list so they are in order
                        //to reset the count and start the loop over again
                        i = -1;
                        continue;
                    }

                    //if it ever reaches index 4 and cannot do this, then it is not a safe state.
                    else if(i == 4)
                    {
                        isSafe = false;
                        break;
                    }
                }
            }

            //print the safe sequence to the user
            //check if after leaving the loop the sequence is safe
            if (isSafe)
            {
                //make an array of strings from the safe state string
                string[] stateList = safeSate.Split(' ');
                string safestateList = "<";
                //add them all to a string
                for (int i = 0; i < stateList.Length - 1; i++)
                {
                    safestateList = safestateList + stateList[i] + " ";
                }

                safestateList = safestateList + stateList[5] + ">";
                txtOutput.Text = "Yes. Safe sequence: " + safestateList;
            }

            //if not safe, tell the user no safe sequence could be found
            else if(!isSafe)
            {
                txtOutput.Text = "There was no safe state found.";
            }
        }

        //when the user pushes the reset button
        private void btnReset_Click(object sender, EventArgs e)
        {
            //reset the text boxes
            txtProcess.Text = "";
            txtResources.Text = "";
            txtOutput.Text = "";

            //return foucs to the processes textbox
            txtProcess.Focus();

            //clear the list boxes
            ClearListBoxes();
        }
    }
}
