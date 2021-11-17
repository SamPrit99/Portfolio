package com.company;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the enemy class in the game, it will be used to make the enemies that the player has to take on.
 */

public class Enemy extends GameObject{

    private Random rnd; //the random number for the enemy placement
    private BoundingRectangle collisionBox; //the collision box for the enemies
    private boolean weak = false; //will be used to determine if the player can hit them
    Timer time; //timer for use when the player can hit them
    GameScreen screen; //needed to redraw them when they become weak

    //constructor for the enemies
    public Enemy(String spriteFileName)
    {
        rnd = new Random(); //make a new random
        loadSprite(spriteFileName); //load the needed image

        //enemies are placed on the screen in random positions in the game play area
        xPos = rnd.nextInt(800);
        yPos = rnd.nextInt(800);
        //makes the collision box for the enemies
        collisionBox = new BoundingRectangle(xPos, yPos, sprite.getWidth(), sprite.getHeight());
        visible = true; //makes them visible
        weak = false; //they are not weak to start
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

    //will make the enemy weak if the player eats a powerup
    public void makeWeak(GameScreen gameScreen)
    {
        screen = gameScreen;
        weak = true;
        loadSprite("enemyWeak.jpeg"); //change which sprite will load
        screen.repaint();
        time = new Timer(); //make a new timer
        time.schedule(new TimeLimit(), 10000); //starts the timer with a 10 second delay, so they are weak for 10 seconds before it ends
    }

    //for use with the got mode cheat. Will always remain weak
    public void GodMode(GameScreen gameScreen)
    {
        screen = gameScreen;
        weak = true;
        loadSprite("enemyWeak.jpeg"); //change which sprite will load
        screen.repaint();
    }

    //gets the location of the collision box
    public Rectangle getCollisionBox() {
        return collisionBox.getBoundingBox();
    }

    //Accessors and Mutators
    public boolean isWeak() {
        return weak;
    }

    public void setWeak(boolean weak) {
        this.weak = weak;
    }


    /**
     * Inner class for the timer object that is used to make the enemies weak to player attacks.
     * At the end of the time limit, the enemies will no longer be able to be attacked by the player
     */
    private class TimeLimit extends TimerTask{

        @Override
        public void run() {
            weak = false; //at the end of the time limit make them strong again
            loadSprite("enemy2.png"); //revert their sprite
            screen.repaint(); //re draw the screen as needed
            time.cancel(); //Terminate the timer thread
        }
    }
}
