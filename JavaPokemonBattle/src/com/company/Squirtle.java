package com.company;

public class Squirtle extends Pokemon {
    //constructor for the squirtle class
    public Squirtle(int x, int y)
    {
        super(x, y); //calls the parent constructor and passes in the x and y position that the image will appear in
        name = "Squirtle"; //sets its name.
        HP = 100; //sets its initial HP
        type = "Water"; //sets its Type
        //sets the moves the Pokemon Knows
        attacks[0] = "Water Gun";
        attacks[1] = "Tackle";
        loadSprite("squirtle.jpg"); //the correct image to load
    }

    @Override
    //to override the attack function for the chosen Pokemon
    public void attack(Pokemon p, int choice)
    {
        //will decide at random if an attack will hit. All attacks have a 10% chance to miss
        int hit = rand.nextInt(10); //produces a random number between 0 and 9
        //if they get a nine, it will miss
        if(hit == 9)
        {
            //takes no damage
            turnSummary = name + "'s attack missed";
        }
        else
        {
            //checks for what attack was used
            if(choice == 1)
            {
                //if the first attack was selected will use Water Gun
                //check for resistance or strength
                if(p.getType() == "Fire")
                {
                    //if it was a fire type it takes double damage
                    p.setHP(p.getHP() - 30);
                    powerPoints = powerPoints - 1; //they lost a use of their special attack
                    turnSummary = name + " used Water Gun. It was super effective! " + p.getName() + " took 30 damage."; //a summary of the turn
                }
                else if(p.getType() == "Grass")
                {
                    //if it was a grass type it takes half damage
                    p.setHP(p.getHP() - 7.5);
                    powerPoints = powerPoints - 1; //they lost a use of their special attack
                    turnSummary = name + " used Water Gun. Not very effective... " + p.getName() + " took 7.5 damage."; //a summary of the turn
                }
                else
                {
                    //if it takes neutral damage
                    p.setHP(p.getHP() - 15);
                    powerPoints = powerPoints - 1; //they lost a use of their special attack
                    turnSummary = name + " used Water Gun. " + p.getName() + " took 15 damage."; //a summary of the turn
                }
            }

            //if the player chose to use tackle
            else
            {
                //tackle is neutral to all Pokemon but does less damage
                p.setHP(p.getHP() - 10);
                turnSummary = name + " used Tackle. " + p.getName() + " took 15 damage."; //a summary of the turn
            }
        }
    }

    @Override
    public String speak() {
        //displays all the information for the pokemon needed.
        String summary = "Name: " + name + " HP: " + HP + " Attacks: Water Gun " + powerPoints + " Power Points, Tackle";
        return summary; //returns the information needed information
    }
}
