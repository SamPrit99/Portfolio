package com.company;

import javax.swing.*;


/**
 * The Human Player class will be controlled by the player of the game.
 * It allows the player to make choices that will be reflected in the game.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String pName) {
        super(pName);

    }

    //to override the choose pokemon. This is the players initial choice of pokemon form the start screen
    @Override
    public void ChoosePokemon(String[] pokemons) {
        //if the first element in the list was charmander it will add charmander to the team first
        if (pokemons[0] == "Charmander") {
            pokemon1 = new Charmander(0, 0); //makes the new charmander
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }
        //if the first element in the list was squirtle it will add squirtle to the team first
        if (pokemons[0] == "Squirtle") {
            pokemon1 = new Squirtle(0, 0); //makes the new squirtle
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }
        //if the first element in the list was bulbasaur it will add bulbasaur to the team first
        if (pokemons[0] == "Bulbasaur") {
            pokemon1 = new Bulbasaur(0, 0); //makes the new bulbasaur
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }
        //if the first element in the list was pikachu it will add pikachu to the team first
        if (pokemons[0] == "Pikachu") {
            pokemon1 = new Pikachu(0, 0); //makes the new pikachu
            pokemonTeam.add(pokemon1); //adds pokemon to the list
        }

        //if the second element in the list was charmander it will add charmander to the team second
        if (pokemons[1] == "Charmander") {
            pokemon2 = new Charmander(0, 0); //makes the new charmander
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }
        //if the second element in the list was squirtle it will add squirtle to the team second
        if (pokemons[1] == "Squirtle") {
            pokemon2 = new Squirtle(0, 0); //makes the new squirtle
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }
        //if the second element in the list was bulbasaur it will add bulbasaur to the team second
        if (pokemons[1] == "Bulbasaur") {
            pokemon2 = new Bulbasaur(0, 0); //makes the new bulbasaur
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }
        //if the second element in the list was pikachu it will add pikachu to the team second
        if (pokemons[1] == "Pikachu") {
            pokemon2 = new Pikachu(0, 0); //makes the new pikachu
            pokemonTeam.add(pokemon2); //adds pokemon to the list
        }

        activePokemon = pokemon1; //makes the first pokemon in the list the active pokemon

    }

    @Override
    public void Run(GameManager theGUI) {
        JOptionPane.showMessageDialog(theGUI, "You have lost the game."); //shows the user a popup that will say they have lost the game
    }



    @Override
    public int ChooseMove(Player player) {
        return 0; //for the sake of making things easy
    }

    @Override
    public boolean CheckIfSwitch() {
        return false; //again for the sake of making things easier
    }


}
