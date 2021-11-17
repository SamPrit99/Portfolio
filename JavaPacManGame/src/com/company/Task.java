package com.company;

import java.awt.*;
import java.util.Random;

/**
 * The task class is for the objects that the player needs to collect in order to
 */

public class Task extends GameObject {

    private Random rnd; //random number to give them a random location on the map
    private BoundingRectangle collisionBox; //the collision box for the tasks

    //the constructor for the task class
    public Task(String spriteFileName)
    {
        rnd = new Random(); //make a new random
        loadSprite(spriteFileName); //load the needed image

        //tasks are placed on the screen in random positions in the game play area
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
