package com.company;

/**
 * The purpose of this project is to make a simplified turn based battle game similar to Pokemon. It makes use of objects and interfaces
 * to produce a game that allows the user to pick Pokemon, use items, and battle against the computer. The computer uses simple logic
 * and always attempts to use super effective moves against the player. Both the player and the computer are able to switch their Pokemon,
 * however, the computer will only do this once.
 *
 * Author: Sam Pritchard
 *
 * Date Last Edited: March 31, 2020
 *
 * This is the main function. It is used to run the game. It makes a GameSetup object and will use that to set up the game to start.
 * The GameSetUp object will make the game manager when it is finished.
 */

public class Main {

    //to begin the game
    public static void main(String[] args) {
        GameSetup game = new GameSetup();
    }
}
