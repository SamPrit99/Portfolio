package com.company;
import java.util.Random;

/**
 * The Computer player is the AI enemy that the user ill be playing against. They are smart and use strategy in how they battle.
 * Knowing the Pokemon chosen by the opposing player, it chooses Pokemon with a type advantage to those the player has chosen.
 * In battle, it will do its best to use super effective moves against the opposing Player and make choices of attacks based on what they do.
 * It it's Pokemon go below half HP, it will switch them out, but only once.
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(String pName)
    {
        super(pName);
    }

    @Override
    //to determine what move it will use. Passes in the Pokemon the player has active
    public int ChooseMove(Player user)
    {
        //checks to make sure the computer player has pp left for their move
        if(activePokemon.getPowerPoints() > 0)
        {
            //if computer has a water type and player has a fire type
            if(activePokemon.getType() == "Water" && user.PokemonType() == "Fire")
            {
                return 1; //will be super effective
            }
            //if computer has a water type and player has a grass type
            else if(activePokemon.getType() == "Water" && user.PokemonType() =="Grass")
            {
                return 2; //will go for the neutral tackle
            }
            //if the computer has a fire type and the player has a grass type
            else if(activePokemon.getType() == "Fire" && user.PokemonType() == "Grass")
            {
                return 1; //will be super effective
            }
            //if the computer has a fire type and the player has a water type
            else if(activePokemon.getType() == "Fire" && user.PokemonType() =="Water")
            {
                return 2; //will go for the neutral tackle
            }
            //if the computer has a grass type and the player has a water type
            else if(activePokemon.getType() == "Grass" && user.PokemonType() == "Water")
            {
                return 1; //will be super effective
            }
            //if the computer has a grass type and the player has a fire type
            else if(activePokemon.getType() == "Grass" && user.PokemonType() =="Fire")
            {
                return 2; //will go for the neutral tackle
            }
            //if the computer has an electric type and the player has a water type
            else if(activePokemon.getType() == "Electric" && user.PokemonType() == "Water")
            {
                return 1; //will be super effective
            }
            //if the computer has an electric type and the player has a grass type
            else if(activePokemon.getType() == "Electric" && user.PokemonType() =="Grass")
            {
                return 2; //will go for the neutral tackle
            }
            //if there is no type advantage it will choose randomly between the two moves it has
            else
            {
                Random rand = new Random(); //makes the random generator
                int choice = rand.nextInt(2); //picks either 0 or 1
                if(choice == 0)
                {
                    return 1; //if 0 choose first move
                }
                else
                {
                    return 2; //if 1 choose second move
                }
            }
        }
        else
        {
            return 2; //if no PP left it will use tackle
        }
    }

    @Override
    //to determine if the computer player wants to switch pokemon
    public boolean CheckIfSwitch()
    {
        //if Pokemon has less than half health and it has not switched yet
        if(activePokemon.getHP() <= 50 && hasSwitched == false)
        {
            return true; //it does want to switch

        }
        else
        {
            return false; //if it has more than 50% health, it does not want to switch
        }


    }

    @Override
    public void ChoosePokemon(String[] pokemons) {
        //if the first element in the list was charmander it will add charmander to the team first
        if(pokemons[0] == "Charmander")
        {
            pokemon1 = new Charmander(0,350); //makes the new charmander
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }
        //if the first element in the list was squirtle it will add squirtle to the team first
        if(pokemons[0] == "Squirtle")
        {
            pokemon1 = new Squirtle(0,350); //makes the new squirtle
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }
        //if the first element in the list was bulbasaur it will add bulbasaur to the team first
        if(pokemons[0] == "Bulbasaur")
        {
            pokemon1 = new Bulbasaur(0,350); //makes the new bulbasaur
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }
        //if the first element in the list was pikachu it will add pikachu to the team first
        if(pokemons[0] == "Pikachu")
        {
            pokemon1 = new Pikachu(0,350); //makes the new pikachu
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }

        //if the second element in the list was charmander it will add charmander to the team second
        if(pokemons[1] == "Charmander")
        {
            pokemon2 = new Charmander(0,350); //makes the new charmander
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }
        //if the second element in the list was squirtle it will add squirtle to the team second
        if(pokemons[1] == "Squirtle")
        {
            pokemon2 = new Squirtle(0,350); //makes the new squirtle
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }
        //if the second element in the list was bulbasaur it will add bulbasaur to the team second
        if(pokemons[1] == "Bulbasaur")
        {
            pokemon2 = new Bulbasaur(0,350); //makes the new bulbasaur
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }
        //if the second element in the list was pikachu it will add pikachu to the team second
        if(pokemons[1] == "Pikachu")
        {
            pokemon2 = new Pikachu(0,350); //makes the new pikachu
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }

        activePokemon = pokemon1; //makes the first pokemon in the list the active pokemon

    }

    @Override
    public void Run(GameManager theGUI) {
        //not used for the computer player they do not forfeit
    }

}
