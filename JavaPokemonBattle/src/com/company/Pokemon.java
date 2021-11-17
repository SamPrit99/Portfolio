package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/***
 * The Pokemon class is an abstract class that serves as a base for all the pokemon included in the game.
 * They each have certain traits represented by the data members and can preform different actions represented by the methods.
 *
 */
public abstract class Pokemon {

    protected double HP = 0.0; //the pokemons health
    protected String[] attacks = {"attack", "attack2"}; //a list of the attacks a Pokemon has
    protected String type; //the pokemons type
    protected int powerPoints = 15; //the amount of times a pokemon can use the special attack
    protected BufferedImage sprite; //the file for the image of the pokemon
    protected String name = ""; //the name of the pokemon
    //these are to determine where the sprite will appear on the screen
    protected int xPos;
    protected int yPos;
    protected boolean visible;
    protected Random rand; //the random number to be used for the attack function
    protected String turnSummary; //to display what happened on a given turn on the screen



    //constructor for the pokemon class
    public Pokemon()
    {
        //Default x and y position of the pokemon
        xPos = 0;
        yPos = 0;
    }

    //overloaded constructor for is you know where you want to place the pokemon
    public Pokemon(int pX, int pY)
    {
        xPos = pX;
        xPos = pY;
        rand = new Random(); //makes teh random object to be used for the attack function
    }

    //will be used to attack pokemon by children
    public abstract void attack(Pokemon p, int choice);


    //will be used to display information to the screen about the pokemon and what happened
    public abstract String speak();

    //used to load the image of the pokemon on the screen. takes in the name of the file
    protected void loadSprite(String pFileName)
    {
        Image image = null;
        try {
            image = ImageIO.read(new File(pFileName)); //reads the image and opens the correct file
        } catch (IOException e) {
            e.printStackTrace();
        }
        sprite = (BufferedImage) image; //makes the sprite the image file that was loaded

    }

    //to draw the image on the screen
    protected void draw(Graphics g)
    {
        g.drawImage(this.sprite, xPos, yPos, null);
    }

    //Accessors and Mutators

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTurnSummary() {
        return turnSummary;
    }

    public void setTurnSummary(String turnSumary) {
        this.turnSummary = turnSumary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAttacks() {
        return attacks;
    }

    public void setAttacks(String[] attacks) {
        this.attacks = attacks;
    }


    public int getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }
}
