using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


/***
 * Samantha Pritchard
 * April 25, 2021
 * This is the page replacement algorithm. This program either genterates a random reference string with no consecuive douplicating numbers or takes 
 * in a reference string from the user. Both reference strings are of length 20. It also takes a frame number between 1 and 7 from the user. 
 * Using the reference string and the frame number it performs FIFO, LRU, and OPT page replacemnt and displays the results of each in the list box.
 */

namespace Samantha_Pritchard_PageReplacement
{
    public partial class frmPageReplacement : Form
    {
        public frmPageReplacement()
        {
            InitializeComponent();
        }

        //when the user pushes the button to calculate the page faults
        private void btnStart_Click(object sender, EventArgs e)
        {
            lstResult.Items.Clear(); //clear the list box for resuabiliy

            int[] refString = new int[20]; //make the reference string array, has size 20
            int frameNum = -1; //the frame number the user wants

            //if the random button is checked 
            if(rdoRandom.Checked)
            {
                //generate a random list of numbers between 0 and 9 with no immediate duplicates

                Random rnd = new Random(); //make a random generator

                string printString = "";
                //produce 20 random numbers
                for(int i=0; i<20; i++)
                {
                    //assign the value in the array the number between 0 and 9
                    int temp = rnd.Next(10);
                    //first value cant be a duplicate
                    if (i == 0)
                    {
                        refString[i] = temp; //need something to compare it too
                    }
                    //when its not the first iteration, make sure its not the same as the value
                    else
                    {
                        int check = rnd.Next(10); //get the next random number to check
                        //while the check is the same as the pervious value
                        while(check == refString[i-1])
                        {
                            //roll a new random number
                            check = rnd.Next(10);
                        }

                        //once we're sure its not the same as the previous number
                        refString[i] = check;
                    }
                    printString = printString + refString[i].ToString(); //create a string to print out the reference string for the user
                }

                txtReferenceString.Text = printString; //print out the randomly generated reference string

                try
                {
                    frameNum = int.Parse(txtFrameNum.Text); //save the frame number the user wanted
                                                            //check that the frame number was between 1 and 7
                    if (frameNum < 1 || frameNum > 7)
                    {
                        MessageBox.Show("Please choose a frame number between 1 and 7.");                       
                    }
                    else
                    {
                        //do the FIFO page replacement
                        int fifoPageFaults = fifoReplacement(refString, frameNum); //pass in the frame number and the referece string
                        lstResult.Items.Add("FIFO page faults: " + fifoPageFaults);

                        //do LRU replacement
                        int lruPageFaults = lruReplacement(refString, frameNum); //pass in the frame number and the reference string
                        lstResult.Items.Add("LRU page faults: " + lruPageFaults);

                        //do OPT replacement
                        int optPageFaults = optReplacement(refString, frameNum); //pass in the frame number and reference string
                        lstResult.Items.Add("OPT page faults: " + optPageFaults);
                    }
                }
                catch
                {
                    MessageBox.Show("Please enter a number for the frame value.");
                }
            }

            //if the enter button is checked
            else if(rdoEnter.Checked)
            {
                //read in the string the user enered
                string enteredRef = txtReferenceString.Text;               

                try
                {
                    //to make sure it is numbers
                    //long testInt = long.Parse(txtReferenceString.Text);

                    //convert the refernece string they entered into an array of characters
                    char[] refChar = enteredRef.ToCharArray();

                    //check that the length of the character array is 20
                    if(refChar.Length == 20)
                    {
                        //go through the array
                        for(int i = 0; i<20; i++)
                        {
                            //convert the character to an int and add to the reference string
                            refString[i] = int.Parse(refChar[i].ToString());
                            
                        }
                    }

                    try
                    {
                        frameNum = int.Parse(txtFrameNum.Text); //save the frame number the user wanted
                                                                //check that the frame number was between 1 and 7
                        if (frameNum < 1 || frameNum > 7)
                        {
                            MessageBox.Show("Please choose a frame number between 1 and 7.");
                        }
                        else
                        {
                            //do the FIFO page replacement
                            int fifoPageFaults = fifoReplacement(refString, frameNum); //pass in the frame number and the referece string
                            lstResult.Items.Add("FIFO page faults: " + fifoPageFaults);

                            //do LRU replacement
                            int lruPageFaults = lruReplacement(refString, frameNum); //pass in the frame number and the reference string
                            lstResult.Items.Add("LRU page faults: " + lruPageFaults);

                            //do OPT replacement
                            int optPageFaults = optReplacement(refString, frameNum); //pass in the frame number and reference string
                            lstResult.Items.Add("OPT page faults: " + optPageFaults);
                        }
                    }
                    catch
                    {
                        MessageBox.Show("Please enter a number for the frame value.");
                    }
                }
                catch
                {
                    MessageBox.Show("Please enter a valid reference string. Do not use spaces.");
                }
            }

            txtFrameNum.Focus(); //retrun focus to the frame number textbox
        }

        //the FIFO replacement algorithm
        private int fifoReplacement(int[] referenceString, int frameNumber)
        {
            int pagefaults = 0; //the number of page faults
            //make a queue for the FIFO replacement
            Queue<int> frameQueue = new Queue<int>();

            //go through the reference string
            for(int i = 0; i<referenceString.Length; i++)
            {
                //if the current reference string number is not in the queue
                if(!frameQueue.Contains(referenceString[i]))
                {
                    //check if the queue has room for this new value
                    if(frameQueue.Count < frameNumber)
                    {
                        frameQueue.Enqueue(referenceString[i]); //enque the value and we do not have to dequeue
                        pagefaults++; //increment the number of page faults
                    }

                    //if the queue has reached its capacity
                    else
                    {
                        int deq = frameQueue.Dequeue(); //dequeue the first item that we entered in the list
                        frameQueue.Enqueue(referenceString[i]); //enqueue the value we need
                        pagefaults++; //increment the number of page faults
                    }
                }
            }

            return pagefaults; //retrun the number of page faults
        }

        //the LRU replacement 
        private int lruReplacement(int[] referenceString, int frameNumber)
        {
            int pageFaults = 0; //the number of page faults we have 
            //use linked list to find the value that hasn't been used for the longest time
            //when a page is referenced, move to top of linked list. that way one that has been removed will be 

            LinkedList<int> frameList = new LinkedList<int>(); //the list that will be used for the frames
            
            //loop through all the values in the reference string
            for(int i = 0; i<referenceString.Length; i++)
            {
                //if the value is not in the string, need to add to linked list
                if(!frameList.Contains(referenceString[i]))
                {
                    //if there is room in the string, just add to the front of the linked list
                    if(frameList.Count < frameNumber)
                    {
                        //add to the front of the list, as it was just used
                        frameList.AddFirst(referenceString[i]);
                        pageFaults++; //increment the page faults
                    }

                    //if there is no more room in the frame list
                    else
                    {
                        frameList.RemoveLast(); //remove the last value in the frame list
                        frameList.AddFirst(referenceString[i]); //add the new value to the front
                        pageFaults++; //increment the page faults
                    }
                }

                //if the value is in the frame list
                else if(frameList.Contains(referenceString[i]))
                {
                    //move it to the front, to keep the one used last at the bottom
                    //go through the frame list and find the index of the value we want to remove
                    int frameVal = 0; //the value we want to remove
                    //go through the frame list
                    foreach(int frame in frameList)
                    {
                        //if they match
                        if(frame == referenceString[i])
                        {
                            frameVal = frame;
                            frameList.Remove(frame); //remove the frame
                            frameList.AddFirst(frameVal); //add it back to the front of the list
                            break; //break from the loop
                        }
                    }

                }
                
            }

            return pageFaults; //return the page faults
        }

        //the OPT replacement
        private int optReplacement(int[] referenceString, int frameNumber)
        {
            int pageFaults = 0; //the number of page faults

            //based on the number of frames, check the future to determine which one to remove
            List<int> frameList = new List<int>(); //the frame list that will hold the values in the frame

            for(int i = 0; i<referenceString.Length; i++)
            {
                //if the value is not in the frame list
                if(!frameList.Contains(referenceString[i]))
                {
                    //check if the list has room for the new value
                    if(frameList.Count < frameNumber)
                    {
                        //if it has room, just add the value to the list, we dont need to remove
                        frameList.Add(referenceString[i]);
                        pageFaults++; //increment the page faults
                    }
                    //if the list is full
                    else
                    {
                        //need to check which value in the list will need to be removed. Whichever has the longest time to be used again
                        //make a list of how long in the future the values in the frame list will be used. 
                        //the one with the higest value will be overwritten
                        List<int> usageList = new List<int>();
                        //defualt all the values in the usage list to 0
                        for(int k = 0; k<frameList.Count; k++)
                        {
                            usageList.Add(0);
                        }

                        for(int k = 0; k < frameList.Count; k++)
                        {
                            //go through the reference string starting at the next value
                            for(int j = i + 1; j < referenceString.Length; j++)
                            {
                                //if the reference string value is not equal to the 
                                if(referenceString[j] != frameList[k])
                                {
                                    usageList[k] = usageList[k] + 1; //increase the time in the future it will be used
                                }
                            }
                        }

                        //go through and find the value that is the biggest to replace it
                        int bigestIndex = 0;
                        int bigestValue = 0; //check the value that will be used in the longest time
                        
                        //go through the usage list and find which one will be used in the longest amount of time
                        for(int k = 0; k<usageList.Count; k++)
                        {
                            //if the value is bigger than the pervious biggest seen value
                            if(usageList[k] > bigestValue)
                            {
                                bigestIndex = k; //the biggest index is the current index
                                bigestValue = usageList[k]; //biggest value is the current value
                            }
                        }

                        //overwrite the index in the frame list of the one that will be used in the longest time
                        frameList[bigestIndex] = referenceString[i];
                        pageFaults++; //increase the page faults                       
                    }
                }
            }

            return pageFaults; //retrun the number of page faults
        }

        //if they decide to enter their own refernece string
        private void rdoEnter_CheckedChanged(object sender, EventArgs e)
        {
            //allow them to type in the box if checked
            if(rdoEnter.Checked)
            {
                txtReferenceString.ReadOnly = false;
            }
            //dont allow them to type if its not
            else
            {
                txtReferenceString.ReadOnly = true;
                txtReferenceString.Focus(); //return focus to the textbox needed
            }
            
        }
    }
}
