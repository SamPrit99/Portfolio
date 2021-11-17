package com.company;
import java.util.ArrayList;
import java.awt.*;

/**
 * The player class is an abstract class that is both for the User to interact with as well as for the computer AI that the user is facing.
 * It contains functions that help to access and interface the game though the active pokemon each player has.
 */
public abstract class Player {
    protected String name; //the name of the player
    protected Pokemon activePokemon; //the currently active pokemon
    protected ArrayList<Pokemon> pokemonTeam; //the pokemon the player has at their disposal
    protected boolean hasSwitched = false; //to make sure that the computer only switch their pokemon out one time during the battle.

    //for the item usage
    protected Potion potion = new Potion();
    protected SuperPotion superPotion = new SuperPotion();
    protected boolean hasUsedPotion = false; //to make sure the player only uses their potion once
    protected boolean hasUsedSuperPotion = false; //to make sure the player only uses their super potion once

    protected Pokemon pokemon1; //the first pokemon choice
    protected Pokemon pokemon2; //the second pokemon choice

    //the player class constructor
    public Player(String pName)
    {
        name = pName;
        pokemonTeam = new ArrayList<Pokemon>(); //instantiates the array list
    }

    //will be used to choose the pokemon the player wants
    public abstract void ChoosePokemon(String [] pokemons);

    public abstract void Run(GameManager theGUI); //will allow the human player to run from the battle

    //to use the potion on a pokemon
    public void UsePotion()
    {
        potion.Use(activePokemon); //uses the item on the active pokemon
    }

    //to use the super potion on a pokemon
    public void UseSuperPotion()
    {
        superPotion.Use(activePokemon); //uses the item on the active pokemon
    }

    //to get the Pokemon's summary
    public String PokemonSummary()
    {
        return activePokemon.speak(); //calls the active pokemon's speak function
    }


    //to swap the pokemon
    public void SwitchPokemon()
    {
        //check to see if the active pokemon is the first pokemon.
       if(activePokemon == pokemon1)
       {
           //switches it to the second pokemon
           activePokemon = pokemon2;
       }

       //checks to see if the active pokemon is the second pokemon
        else if(activePokemon == pokemon2)
        {
            //switches it to the first pokemon
            activePokemon = pokemon1;
        }
    }

    //to make the active pokemon attack
    public void PokemonAttack(Pokemon p, int choice)
    {
        activePokemon.attack(p, choice); //calls the active Pokemon's attack function and passes in the opposing pokemon and their choice
    }

    public double PokemonHP()
    {
        return activePokemon.getHP(); //returns the current HP of the pokemon
    }

    //to get the type of the current Pokemon for the computer player
    public String PokemonType()
    {
        return activePokemon.getType();
    }

    //to get the turn summary for the pokemon's attacks
    public String PokemonTurnSummary()
    {
        return activePokemon.getTurnSummary();
    }

    //to get the list of attacks the active pokemon has
    public String[] PokemonAttacks()
    {
        return activePokemon.getAttacks();
    }

    public int PokemonPP()
    {
        return activePokemon.getPowerPoints(); //gets the active pokemons powerpoints
    }

    public abstract int ChooseMove(Player player);

    public abstract boolean CheckIfSwitch(); //to check if the computer wants to switch


    //calls the draw function for the current pokemon so that it will be drawn when they switch
    protected void draw(Graphics g)
    {
        activePokemon.draw(g); //passes the graphics object in so it can draw
    }


    //Accessors and Mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pokemon> getPokemonTeam() {
        return pokemonTeam;
    }

    public void setPokemonTeam(ArrayList<Pokemon> pokemonTeam) {
        this.pokemonTeam = pokemonTeam;
    }

    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    public void setActivePokemon(Pokemon activePokemon) {
        this.activePokemon = activePokemon;
    }

    public boolean isHasSwitched() {
        return hasSwitched;
    }

    public void setHasSwitched(boolean hasSwitched) {
        this.hasSwitched = hasSwitched;
    }

    public boolean isHasUsedPotion() {
        return hasUsedPotion;
    }

    public void setHasUsedPotion(boolean hasUsedPotion) {
        this.hasUsedPotion = hasUsedPotion;
    }

    public boolean isHasUsedSuperPotion() {
        return hasUsedSuperPotion;
    }

    public void setHasUsedSuperPotion(boolean hasUsedSuperPotion) {
        this.hasUsedSuperPotion = hasUsedSuperPotion;
    }

    //inner classes for items
    protected class Potion implements Items
    {
        @Override
        //to use the simple potion
        public void Use(Pokemon p)
        {
            //will not allow the pokemon to have more than 100 HP
            if(p.getHP() < 100)
            {
                if(p.getHP() < 80)
                {
                    p.setHP(p.getHP() + 20); //simple potion restores 20 hp
                }
                //if it has less than 100 HP, but more than 80 HP
                else
                {
                    p.setHP(100); //gets it back to full
                }
            }
        }
    }

    protected class SuperPotion implements Items
    {
        @Override
        //to use the super potion
        public void Use(Pokemon p)
        {
            //will not allow the pokemon to have more than 100 HP
            if(p.getHP() < 100)
            {
                if(p.getHP() < 50)
                {
                    p.setHP(p.getHP() + 50); //super potion restores 50 hp
                }
                //if it has less than 100 HP, but more than 50 HP
                else
                {
                    p.setHP(100); //gets it back to full
                }
            }
        }
    }


}
