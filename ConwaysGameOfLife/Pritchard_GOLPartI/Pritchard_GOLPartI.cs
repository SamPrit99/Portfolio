using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;


//Samantha Pritchard. This is the Game of Life Part II. The purpose of this project is to make the cells live and show their generations.
//The Rules For GOL:
// 
// Already Alive Cells
// If no neighbor, dies
// If 4 or more neighbors, dies
// If 2 or 3 Neighbors, lives
// 
// Dead Cells Come to Life
// Has exactly 3 alive neighbors


namespace Pritchard_GOLPartI
{
    public partial class Pritchard_GOL : Form
    {
        //initalizes the constants and instance variables and creates the brush, pen, and graphics objects that will be used later.
        private GOLCell[,] grid;
        private const int ROWS = 42;
        private const int COLS = 62;
        private const int SIZE = 10;
        private Brush gridBrush;
        private Brush gridBrush0;
        private Brush gridBrush1;
        private Brush gridBrush2;
        private Brush gridBrush3;
        private Brush gridBrush4;
        private Pen pen;
        private Graphics graphics;
        private int dead = 0;
        private int genNumber = 1;
        private StreamReader inputFile;
        private int[,] aliveVals = new int[ROWS, COLS]; 
        private int[] inputedVals;
        private int generationWanted;
       // private StreamWriter saveFile;
        private string output = "";

        public Pritchard_GOL()
        {
            //initialize the objects and sets the grid size. also chooses the brush color and pen color and thickness.
            InitializeComponent();
            grid = new GOLCell[ROWS, COLS];
            gridBrush = new SolidBrush(Color.White);
            gridBrush0 = new SolidBrush(Color.Yellow);
            gridBrush1 = new SolidBrush(Color.Green);
            gridBrush2 = new SolidBrush(Color.Blue);
            gridBrush3 = new SolidBrush(Color.Magenta);
            gridBrush4 = new SolidBrush(Color.Red);
            pen = new Pen(Color.Black, 2);
            graphics = this.CreateGraphics();
        }

        //allows them to cycle through and age.
        public void FutureState()
        {
            for (int i = 2; i < ROWS; i++)
            {
                for (int j = 2; j < COLS; j++)
                {
                    if (i != 42 && i!=41 && j != 61 && j!=62)
                    {
                        //determines how many alive neighbors there are
                        int numAliveNeighbors = grid[i, j - 1].Alive + grid[i - 1, j - 1].Alive + grid[i - 1, j].Alive + grid[i - 1, j + 1].Alive + grid[i, j + 1].Alive + grid[i + 1, j + 1].Alive + grid[i + 1, j].Alive + grid[i + 1, j - 1].Alive;

                        if (grid[i, j].Alive == 1)
                        {
                            //if cells will die
                            if (numAliveNeighbors <= 1 || numAliveNeighbors >= 4)
                            {
                                //changes their future state and sets their age to -1
                                grid[i, j].FutureState = 0;
                                grid[i, j].Age = -1;
                                dead++;
                            }
                            //if cells will live
                            else if (numAliveNeighbors == 2 || numAliveNeighbors == 3)
                            {
                                //changes their future state and increments their age
                                grid[i, j].FutureState = 1;
                                int age = grid[i, j].Age;
                                grid[i, j].Age = age + 1;
                            }
                        }
                        //if cells will become alive
                        else if (grid[i, j].Alive == 0)
                        {
                            if (grid[i, j].Alive == 0 && numAliveNeighbors == 3)
                            {
                                //changes their future state and increments their age
                                grid[i, j].FutureState = 1;
                                int age = grid[i, j].Age;
                                grid[i, j].Age = age + 1;
                            }
                        }

                        else
                        { }
                    }
                }
            }
        }

        //counts the number of cells that are alive
        public int CountAliveCells()
        {
            int numAliveCells = 0;
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    if (grid[i,j].Alive==1)
                    {
                        numAliveCells++;
                    }
                }
            }
            return numAliveCells;
        }

        //changes their alive status when its time for the new generation to happen.
        public void UpdateCellLife()
        {
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    grid[i, j].Alive = grid[i, j].FutureState;
                    grid[i, j].FutureState = 0;
                }
            }
        }

        //creates the empty grid
        public void CreateEmptyGrid()
        {
            //cycles through each row and column.
            for (int i=0; i<ROWS; i++)
            {
                for (int j=0; j<COLS; j++)
                {
                    //creates a rectangle at the necessary location with the size using the constants.
                    Rectangle tempRect = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
                    //creates new GOLCells and gives them a size equal to the rectangle that was just made as well as sets them all to have alive of 0 or dead and -1 for their age
                    grid[i, j] = new GOLCell(tempRect, -1, 0, 0);
                }
            }
        }

        //creates the pulsar
        public void CreatePulsarGrid()
        {
            //determines if the pulsar is alive or dead
            int[,] pulse = new int[13, 13] {{0,0,1,1,1,0,0,0,1,1,1,0,0},
                                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                                            {1,0,0,0,0,1,0,1,0,0,0,0,1},
                                            {1,0,0,0,0,1,0,1,0,0,0,0,1},
                                            {1,0,0,0,0,1,0,1,0,0,0,0,1},
                                            {0,0,1,1,1,0,0,0,1,1,1,0,0},
                                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                                            {0,0,1,1,1,0,0,0,1,1,1,0,0},
                                            {1,0,0,0,0,1,0,1,0,0,0,0,1},
                                            {1,0,0,0,0,1,0,1,0,0,0,0,1},
                                            {1,0,0,0,0,1,0,1,0,0,0,0,1},
                                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                                            {0,0,1,1,1,0,0,0,1,1,1,0,0}};

            //cycles through each row and column to create rectangles
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //creates a rectangle at the necessary location with the size using the constants.
                    Rectangle tempRect = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
                    //creates new GOLCells and gives them a size equal to the rectangle that was just made as well as sets them all to have alive of 0 or dead and -1 for their age
                    grid[i, j] = new GOLCell(tempRect, -1, 0, 0);

                    //when it reaches where the pulsar should start, it checks the pulsar array to determine what their alive value should be.
                    if(i>=14 && i<27 && j>=20 && j<33)
                    {
                        grid[i, j].Alive = pulse[i - 14, j - 20];
                    }
                    //if not at the place the pulsar should be, sets their alive value to 0
                    else
                    {
                        grid[i, j].Alive = 0;
                    }
                }
            }
        }

        //creates the beacon grid
        public void CreateBeaconGrid()
        {
            //determines if the beacon is alive or dead
            int[,] beacon = new int[4, 4] { { 1,1,0,0},
                                             {1,1,0,0},
                                             {0,0,1,1},
                                             {0,0,1,1}};

            //cycles through each row and column to create rectangles
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //creates a rectangle at the necessary location with the size using the constants.
                    Rectangle tempRect = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
                    //creates new GOLCells and gives them a size equal to the rectangle that was just made as well as sets them all to have alive of 0 or dead and 0 for their age
                    grid[i, j] = new GOLCell(tempRect, -1, 0, 0);

                    //when it reaches where the beacon should start, it checks the pulsar array to determine what their alive value should be.
                    if (i >= 14 && i < 18 && j >= 20 && j < 24)
                    {
                        grid[i, j].Alive = beacon[i - 14, j - 20];
                    }
                    //if not at the place the beacon should be, sets their alive value to 0
                    else
                    {
                        grid[i, j].Alive = 0;
                    }
                }
            }
        }

        //creats the infiniate growth grid
        public void CreateInfinateGrid()
        {
            //determines if the beacon is alive or dead
            int[] infinate = new int[40] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 };
            //cycles through each row and column to create rectangles
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //creates a rectangle at the necessary location with the size using the constants.
                    Rectangle tempRect = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
                    //creates new GOLCells and gives them a size equal to the rectangle that was just made as well as sets them all to have alive of 0 or dead and 0 for their age
                    grid[i, j] = new GOLCell(tempRect, -1, 0, 0);

                    //when it reaches where the beacon should start, it checks the pulsar array to determine what their alive value should be.
                    if (i >= 14 && i <= 54 && j == 20)
                    {
                        grid[i, j].Alive = infinate[i-14];
                    }
                    //if not at the place the beacon should be, sets their alive value to 0
                    else
                    {
                        grid[i, j].Alive = 0;
                    }
                }
            }
        }

        //creates the randomly generated grid
        public void CreateRandomGrid()
        {
            //creates random object to give them a random alive value
            Random rnd = new Random();
            //cycles through each row and column.
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //creates a rectangle at the necessary location with the size using the constants.
                    Rectangle tempRect = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
                    //makes their alive randomly a 1 or a 0
                    int alive = rnd.Next(0,2);
                    //creates the cell with the random alive value
                    grid[i, j] = new GOLCell(tempRect, -1, alive, 0);                   
                }
            }

        }

        //draws the grid.
        public void DrawGrid()
        {
            //cycles through the array
            for (int i=0; i<ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //checks if the cells have an alive value of 1 or are alive
                    if (grid[i, j].Alive == 1)
                    {
                        //fills and draws a line around the rectangle
                        graphics.FillRectangle(gridBrush0, grid[i, j].Cell);
                        graphics.DrawRectangle(pen, grid[i, j].Cell);                       
                    }
                    //if cells are not alive or have alive value of 0
                    else
                    {
                        graphics.FillRectangle(gridBrush, grid[i, j].Cell);
                        graphics.DrawRectangle(pen, grid[i, j].Cell);
                    }
                }
            }
        }

        //draws the grid when having the cells live
        public void DrawLiveGrid()
        {
            //cycles through the array
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //checks if the cells have an alive value of 1 or are alive
                    if (grid[i, j].Alive == 1)
                    {
                        //checks to see if they have an age of 0
                        if (grid[1, j].Age == 0)
                        {
                            //fills and draws a line around the rectangle
                            graphics.FillRectangle(gridBrush0, grid[i, j].Cell);
                            graphics.DrawRectangle(pen, grid[i, j].Cell);
                        }
                        //checks to see if they have an age of 1
                        else if (grid[i, j].Age == 1)
                        {
                            //fills and draws a line around the rectangle
                            graphics.FillRectangle(gridBrush1, grid[i, j].Cell);
                            graphics.DrawRectangle(pen, grid[i, j].Cell);
                        }
                        //checks to see if they have an age of 2
                        else if (grid[i, j].Age == 2)
                        {
                            //fills and draws a line around the rectangle
                            graphics.FillRectangle(gridBrush2, grid[i, j].Cell);
                            graphics.DrawRectangle(pen, grid[i, j].Cell);
                        }
                        //checks to see if they have an age of three
                        else if (grid[i, j].Age == 3)
                        {
                            //fills and draws a line around the rectangle
                            graphics.FillRectangle(gridBrush3, grid[i, j].Cell);
                            graphics.DrawRectangle(pen, grid[i, j].Cell);
                        }
                        //if they have an age of 4 or more
                        else
                        {
                            //fills and draws a line around the rectangle
                            graphics.FillRectangle(gridBrush4, grid[i, j].Cell);
                            graphics.DrawRectangle(pen, grid[i, j].Cell);
                        }
                    }
                    //if cells are not alive or have alive value of 0
                    else
                    {
                        graphics.FillRectangle(gridBrush, grid[i, j].Cell);
                        graphics.DrawRectangle(pen, grid[i, j].Cell);
                    }
                }
            }
        }

        //to determine how many of each kind of cell there are
        public void UpdateStats()
        {
            //counts the alive cells
            int aliveCells = CountAliveCells();
            int died = dead;
            //adds items to the list box
            lstStats.Items.Add("Generation: " + genNumber);
            lstStats.Items.Add("Alive Cells: " + aliveCells);
            lstStats.Items.Add("Died: " + died);
            lstStats.Items.Add("Newly Born - Yellow");
            lstStats.Items.Add("1 Year Old - Green");
            lstStats.Items.Add("2 Years Old - Blue");
            lstStats.Items.Add("3 Years Old - Magenta");
            lstStats.Items.Add("4+ Years Old - Red");
            lstStats.Refresh();
        }

        //for when they upload a file reads the values and makes an array
        public void FileUploaded()
        {
            //reads the values in from the file and makes an array
            int counter = 0;
            string lines2 = "";
            while (inputFile.EndOfStream == false)
            {
                lines2 = lines2 + inputFile.ReadLine();
                counter++;
            }

            //splits each character
            char[] valuesAlive = lines2.ToCharArray();

            //creates the needed int array
            int[] aliveAsInts = new int[lines2.Length]; 
            //makes sure numbers were entered and provides an error when they are not
            try
            {
                //converts the inputed values to ints
                for (int b = 0; b < valuesAlive.Length; b++)
                {
                    int tempLife = (int)Char.GetNumericValue(valuesAlive[b]);
                    aliveAsInts[b] = tempLife;
                }
            }
           catch
            {
                MessageBox.Show("Please Make sure your text file contains only numbers");
            }

            //changes to a 2d array makes sure the file is the right size
            try
            {
                ConvertToTwoDArray(aliveAsInts);
            }
            catch
            {
                MessageBox.Show("Please Make sure your file is the right size");
            }

            //creates the grid needed
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    //creates a rectangle at the necessary location with the size using the constants.
                    Rectangle tempRect = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
                    int alive = aliveVals[i, j];
                    grid[i, j] = new GOLCell(tempRect, 0, 0, 0);
                    grid[i, j].Alive = alive;
                }
            }
        }


        //converts a 1D int array into a 2D int array
        public void ConvertToTwoDArray(int[] pOneArray)
        {
         //goes through and sets all the values to thier alive value from the file
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    aliveVals[i, j] = pOneArray[i * COLS + j];
                }
            }

        }

        //to save a file
        public void SaveAliveState()
        {
           
            int[] aliveValues = new int[2604];
            //creates the output for the file
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    output = output + grid[i, j].Alive.ToString();
                }
                output = output + Environment.NewLine;
            }
        }

        //how the generations pass
        public void GenerationPass()
        {
            lstStats.Items.Clear();
            dead = 0;
            FutureState();                 
            UpdateCellLife();
            DrawLiveGrid();
            UpdateStats();
           // Timer time = new Timer();
           // time.Interval = 2000;
           // time.Start();
            genNumber++;
        }
       
        //when you push the button to draw the grid.
        private void btnCreateGrid_Click(object sender, EventArgs e)
        {
            //checks if the radio button to draw the empty grid is checked
            if(radEmpty.Checked == true)
            {
                CreateEmptyGrid();
                DrawGrid();
                //to make the gen reset when you change graph
                genNumber = 0;
            }

            //checks if the radio button to draw the pulsar is checked.
            else if(radPulsar.Checked == true)
            {
                CreatePulsarGrid();
                DrawGrid();
                //to make the gen reset when you change graph
                genNumber = 0;
            }
            //checks if the radio button to draw the beacon is checked
            else if(radBeacon.Checked == true)
            {
                CreateBeaconGrid();
                DrawGrid();
                //to make the gen reset when you change graph
                genNumber = 0;
            }
            else if(radInfinite.Checked == true)
            {
                CreateInfinateGrid();
                DrawGrid();
                //to make the gen reset when you change graph
                genNumber = 0;
            }
            else if(radRandom.Checked == true)
            {
                CreateRandomGrid();
                DrawGrid();
                //to make the gen reset when you change graph
                genNumber = 0;
            }
            else if(radFile.Checked == true)
            {
                FileUploaded();
                DrawGrid();
            }
            else
            { }
            //to make sure they have a grid drawn before they begin to pass generations.
            startLife.Enabled = true;
            //makes sure the save button is enabled once it makes a grid
            btnSave.Enabled = true;
        }

        //when the let cells live button is pushed
        private void startLife_Click(object sender, EventArgs e)
        {
            //makes sure you cant save while cells are progressing
            btnSave.Enabled = false;
            btnUpload.Enabled = false;
            //determines how many times people want to run the program
            try
            {
                generationWanted = int.Parse(txtGenNumber.Text);
            }
            catch
            {
                MessageBox.Show("Please Input A Number");
            }
            //makes generations pass for each time they want it to run
            for (int i = 0; i < generationWanted; i++)
            {
                GenerationPass();
            }
            //allows you to save again
            btnSave.Enabled = true;
            btnUpload.Enabled = true;
        }

        //allows people to upload their own file
        private void btnUpload_Click(object sender, EventArgs e)
        {
            //This try/catch is to ensure files are in the correct format
            try
            {
                //Message box will appear first and say what needs to be done. sets the stream reader to work and opens the file.
                MessageBox.Show("Please input a valid text file of Alive Statuses.");
                fdOpenLife.ShowDialog();
                inputFile = new StreamReader(fdOpenLife.FileName);
            }

            //catch will tell user to upload a file of the correct format
            catch
            {
                MessageBox.Show("Error with file upload. Please upload a vald text file.");
            }
        }

        //allows to save the current life
        private void btnSave_Click(object sender, EventArgs e)
        {
            fdSaveFile = new SaveFileDialog();
            fdSaveFile.Filter = "txt files (*.txt)|*.txt";
            fdSaveFile.FilterIndex = 2;
            fdSaveFile.RestoreDirectory = true;
            fdSaveFile.Dispose();

            if (fdSaveFile.ShowDialog() == DialogResult.OK)
            {
                StreamWriter saveFile = new StreamWriter(fdSaveFile.FileName);
                SaveAliveState();
                saveFile.Write(output);
                saveFile.Close();
            }
        }
    }
}