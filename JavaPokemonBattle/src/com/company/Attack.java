package com.company;

/**
 * The attack class is and abstract class that serves as a base for the attacks that can be used by the pokemon.
 * They have certain traits that are unique to attacks.
 */
public abstract class Attack {

    private int pp; //amount of times you can use a move
    private String name; //the name of the move
    private double damageRange; //the amount of damage it can do
    private double accuracy; //the amount of times you can hit with a move
}
