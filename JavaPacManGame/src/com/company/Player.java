package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The player controlled by the user in the game.
 */

public class Player extends GameObject{

    public static final int PLAYER_SPEED = 5; //the speed the player moves at
    public final static int TILE_SIZE = 32; //the size of the tile the player takes up

    private int subImageX, subImageY; //subimage for the sprite sheet smaller image that is the player
    private BufferedImage spriteSheet; //the sprite sheet the player comes from


    private boolean weak = true; //for the player to be attacked by enemies and still get away
    private Timer time; //timer for when it takes damage

    private BoundingRectangle collisionBox;

    //the constructor, will take in the file that will be loaded and its width and height
    public Player(String spriteFileName, int pWidth, int pHeight)
    {
        subImageX=0; //the x of the sub image
        subImageY=0; //the y of the sub image
        loadSprite(spriteFileName); //Load the image for the player
        spriteSheet = sprite; //sets the sprite sheet to be the sprite that was loaded


        //Player always starts out in the same position
        //Left center of the screen.
        xPos = pWidth/2;
        yPos = pHeight-sprite.getHeight();

        visible = true; //make the player visible
    }

    //updates the position of the bounding box surrounding the player
    public void UpdateCollisionBoxPosition()
    {
        collisionBox.updateBoundingBox(this.xPos, this.yPos);
    }

    /**
     * Overrriden function to force the player and other objects to draw themselves on the screen.
     * Have to pass in the graphics object associated with the screen.
     */
    public void draw(Graphics g) {
        getSubSprite(); //gets the subsprite for the walking animation
        collisionBox = new BoundingRectangle(xPos, yPos, sprite.getWidth(), sprite.getHeight()); //makes a new collision box for the player
        g.drawImage(this.sprite, xPos, yPos, null); //draws the image of the player
        collisionBox.draw(g); //draws the bounding box
    }

    //gets the collision box for the player
    public Rectangle getCollisionBox() {
        return collisionBox.getBoundingBox(); //returns the bounding box
    }

    //gets the sub sprite for the player, the part of the sprite sheet that we want to use
    public void getSubSprite()
    {
        sprite = spriteSheet.getSubimage(subImageX*TILE_SIZE, subImageY*TILE_SIZE, TILE_SIZE, TILE_SIZE); //updates the sub image based on the player tile size and the needed sub sprite
    }

    //Accessors and Mutators
    public int getSubImageX() {
        return subImageX;
    }

    public void setSubImageX(int subImageX) {
        this.subImageX = subImageX;
    }

    public int getSubImageY() {
        return subImageY;
    }

    public void setSubImageY(int subImageY) {
        this.subImageY = subImageY;
    }

    public boolean isWeak() {
        return weak;
    }

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    public void takeDamage()
    {
        weak = false;
        time = new Timer(); //make a new timer
        time.schedule(new Player.TimeLimit(), 5000); //starts the timer with a 5 second delay, so they have 5 seconds to get away 
    }

    private class TimeLimit extends TimerTask {

        /**
         * Timer needed for when the player gets hit and has to move out of the way so they wont die to fast.
         */
        @Override
        public void run() {
            weak = true; //at the end of the time limit, go back to being attacked
            time.cancel(); //Terminate the timer thread
        }
    }

}
