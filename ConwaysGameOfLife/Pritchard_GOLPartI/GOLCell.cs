using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Pritchard_GOLPartI
{
    class GOLCell
    {
        //creates the attributes of the GOLCell
        int alive;
        int age;
        int futureState;
        Rectangle cell;

        //constructor sets the parameters that are inputed equal to the attributes
        public GOLCell(Rectangle pCell, int pAge, int pAlive, int pFuture)
        {
            Cell = pCell; 
            Age = pAge;
            Alive = pAlive;
            FutureState = pFuture;
        }

        //allows the attributes to be changed
        public int Alive { get => alive; set => alive = value; }
        public int Age { get => age; set => age = value; }
        public int FutureState { get => futureState; set => futureState = value; }
        public Rectangle Cell { get => cell; set => cell = value; }
    }
}
