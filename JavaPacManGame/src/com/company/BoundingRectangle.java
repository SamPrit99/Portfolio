package com.company;

import java.awt.*;

/**
 * The bounding rectangle is used for the hit boxes of the player, the enemy, and the powerups.
 * Bounding rectangles surround them and are used to tell if the two have collided.
 */

public class BoundingRectangle extends GameObject{

    private Rectangle boundingBox; //the rectangle that will be used for the shape of the bounding rectangle
    private int spriteWidth; //the width of the sprite it surrounds
    private int spriteHeight; //the height of the sprite it surrounds


    //constructor of the bounding rectangle. Takes in the position values and the height and width needed
    public BoundingRectangle(int pXPos, int pYPos,
                             int pWidth, int pHeight)
    {
        xPos = pXPos; //sets its x position
        yPos = pYPos; //sets its y position
        spriteWidth = pWidth; //sets the width of the sprite
        spriteHeight = pHeight; //sets the height of the sprite

        boundingBox = new Rectangle(xPos, yPos, pWidth, pHeight); //makes a rectangle of the sizes specified, which will be the bounding rectangle.
    }

    //updates the position of the bounding rectangle
    public void updateBoundingBox(int pX, int pY)
    {
        //changes the x and y position of the rectangle based on what was passed in to be its new location
        boundingBox.x=pX;
        boundingBox.y=pY;
    }

    //overrides the draw function
    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g; //turns the graphics object to a graphics 2d object so it can redraw a rectangle
        g2d.draw(boundingBox); //draws the rectangle where it needs to be
    }


    //Accessors and Mutators
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }
}
