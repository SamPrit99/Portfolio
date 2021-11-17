package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The screen that the game will appear on.
 */

public class GameScreen extends JPanel {

    private GameObject playerReference; //a reference for the player
    private ArrayList<Enemy> gameObjReference; //a reference for the other game objects
    private ArrayList<GameObject> powerUpsReference; //a reference for the powerups that will appear on the screen
    private ArrayList<GameObject> taskReference;


    //Constructor sets the background color
    public GameScreen()
    {
        this.setBackground(Color.gray);
    }

    //sets the player reference from the passed in game object
    public void setPlayerReferenceInScreen(GameObject pPlayer)
    {
        playerReference = pPlayer;
    }

    //sets the game object reference from the passed in array list
    public void setGameObjectsReferenceInScreen(ArrayList<Enemy> pGameObjs)
    {
        gameObjReference= pGameObjs;
    }

    //sets the powerup reference from the passed in array list
    public void setPowerUpReferenceInScreen(ArrayList<GameObject> pPowerups)
    {
        powerUpsReference = pPowerups;
    }

    //sets the task reference from the passed in array list
    public void setTaskReferenceInScreen(ArrayList<GameObject> pTasks)
    {
        taskReference = pTasks;
    }

    //override the paint component function to draw the screen
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //call parent draw component

        //Tell game objects (enemies) to draw themselves
        if (gameObjReference != null)
        {
            //for all the objects in the list
            for(Enemy aGameObject : gameObjReference)
            {
              aGameObject.draw(g); //draw the game objects
            }
        }

        //as long as there is a powerups reference
        if (powerUpsReference != null)
        {
            //for all the objects in the list
            for(GameObject aGameObject : powerUpsReference)
            {
                aGameObject.draw(g); //draw the game objects
            }
        }

        //as long as there is a task reference
        if (taskReference != null)
        {
            //for all the objects in the list
            for(GameObject aGameObject : taskReference)
            {
                aGameObject.draw(g); //draw the game objects
            }
        }

        //as long as there is a player reference
        if (playerReference != null) {
            playerReference.draw(g); //draw the player
        }

    }
}
