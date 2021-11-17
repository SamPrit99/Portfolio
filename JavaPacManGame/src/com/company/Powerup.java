package com.company;

/**
 * Need to make it so the player eats a power up, the enemies become weak for a little while, then after 30 seconds they are no longer weak.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The powerups class is used for the powerups the player can use on the screen.
 * There will be a set number of them that will appear randomly around the screen and they will
 * allow the player to attack the enemies.
 */

public class Powerup extends GameObject {

    private Random rnd; //random number to give them a random location on the map
    private BoundingRectangle collisionBox; //the collision box for the powerups
    private ArrayList<Enemy> enemy; //used for when the player uses a powerup


    //the constructor for the powerup class
    public Powerup(String spriteFileName)
    {
        rnd = new Random(); //make a new random
        loadSprite(spriteFileName); //load the needed image

        //powerups are placed on the screen in random positions in the game play area
        xPos = rnd.nextInt(800);
        yPos = rnd.nextInt(800);
        //makes the collision box for the enemies
        collisionBox = new BoundingRectangle(xPos, yPos, sprite.getWidth(), sprite.getHeight());
        visible = true; //makes them visible
    }

    //updates the position of the collision box as they move
    public void UpdateCollisionBoxPosition()
    {
        collisionBox.updateBoundingBox(this.xPos, this.yPos);
    }

    //will be called when you use the powerup
    public void UsePowerup(ArrayList<Enemy> pEnemy, GameScreen gameScreen)
    {
        enemy = pEnemy; //make the array list that was passed the needed array list
        //go through all the enemies
        for(Enemy anEnemy : enemy)
        {
            anEnemy.makeWeak(gameScreen); //make them weak
        }
    }

    /**
     * Overloaded function to force the player and other objects to draw themselves on the screen
     * Have to pass in the graphics object associated with the screen
     */
    public void draw(Graphics g) {
        g.drawImage(this.sprite, xPos, yPos, null);
        UpdateCollisionBoxPosition();
        collisionBox.draw(g);
    }

    //gets the location of the collision box
    public Rectangle getCollisionBox() {
        return collisionBox.getBoundingBox();
    }

}
