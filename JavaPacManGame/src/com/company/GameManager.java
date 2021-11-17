package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * This is the Game Manager class, it will be used to run the game and take input from the user. It also makes the window that the user will see.
 * It implements runnable to allow for the use of threads in the game.
 */

public class GameManager extends JFrame implements Runnable {
    public final static int WIDTH = 1000; //the height of the screen
    public final static int HEIGHT = 1000; //the width of the screen
    public final static int NUMENEMIES=4; //the number of enemies that will appear on the screen
    public final static int NUMPOWERS = 4; //the number of power ups that will appear on the screen
    public final static int NUMTASK = 10; //the number of tasks that will appear on the screen


    private GameScreen gameScreen;  //The screen on which we will draw
    private GameKeyListener keyListener; //The listener for key presses
    private Player player; //The player controlled by the user
    private ArrayList<Enemy> gameObjects; //all game objects on the game screen except the player
    private ArrayList<GameObject> powerUps; //the array list for the powerups that will appear on the screen
    private ArrayList<GameObject> tasks; //the array list for the tasks that will appear on the screen
    private Thread loop; //the thread that will run the game
    private Random rand; //random number generator for the enemy movement

    private int score = 0; //the total score the player has accumulated
    private JLabel scoreLbl; //the label for the score of the game
    private JLabel lives; //shows the lives the player has left
    private int health = 3; //the health of the player. if it reaches 0 the player loses the game.
    private int tasksComplete = 0; //the number of tasks the player completed


    //The constructor for the game manager class
    public GameManager()
    {
        this.setTitle("Sam's Final Project Game"); //set the title of the window
        this.setSize(WIDTH, HEIGHT); //set the width and height of the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //sets the default close operation
        gameScreen = new GameScreen(); //Instantiate the panel on which we will draw
        scoreLbl = new JLabel("Score: " + score); //makes the label and adds the needed text
        lives = new JLabel("Lives: " + health); //shows the player life
        gameScreen.add(lives); //adds to the game screen
        gameScreen.add(scoreLbl); //puts the label on the game screen
        this.add(gameScreen, BorderLayout.CENTER);  //add the JPanel to the center of the screen
        this.setVisible(true); // set the window to be visible
        keyListener = new GameKeyListener(); //instantiate the listener for key presses
        this.addKeyListener(keyListener); //add the key listener to the JFrame
        rand = new Random(); //makes the random number
        SetUpGame(); //calls the setup function
    }

    /**
     * SetupGame: used for the initialization process for the game
     * Create the player
     * Create the game objects
     */
    private void SetUpGame()
    {
        GameAudio audio = new GameAudio(); //loads the game audio file
        //Instantiate the Player
        player = new Player("jodySpriteSheet2.png", getWindowWidth(), getWindowHeight());
        //Instantiate the game objects that will be the enemies
        gameObjects = new ArrayList<Enemy>();
        powerUps = new ArrayList<GameObject>();
        tasks = new ArrayList<GameObject>();
        gameScreen.setPlayerReferenceInScreen((Player)player); //sets the player reference
        gameScreen.setGameObjectsReferenceInScreen(gameObjects); //sets the enemy reference
        gameScreen.setPowerUpReferenceInScreen(powerUps); //sets up the powerup reference
        gameScreen.setTaskReferenceInScreen(tasks);


        //generates the powerups we need and makes sure its the right number of powerups
        for (int i=0; i<NUMPOWERS; i++) {
            GeneratePowerUps(); //calls the generate function
        }

        //generates the tasks we need and makes sure its the right number of tasks
        for (int i=0; i<NUMTASK; i++) {
            GenerateTasks(); //calls the generate function
        }

        //Generate the enemies we need on the screen, makes sure it is the number we set before
        for (int i=0; i<NUMENEMIES; i++) {
            GenerateBones(); //calls the generate function
        }

        RunGame(); //runs the game
    }

    //generates new enemies and loads their image file
    private void GenerateBones()
    {
        gameObjects.add(new Enemy("enemy2.png")); //loads the needed image
    }

    //generates the powerups and loads their image file
    private void GeneratePowerUps()
    {
        powerUps.add(new Powerup("powerup2.jpg")); //loads the needed image
    }

    //generates the tasks and loads their image file
    private void GenerateTasks()
    {
        tasks.add(new Task("task.jpg")); //loads the needed image
    }

    //runs the game, updates the screen and begins the thread
    private void RunGame()
    {
        //sets the powerup positions
        //to draw all of the needed powerups
        //for each game object in the list of powerups
        for (GameObject aGameObject : powerUps)
        {
            aGameObject.setyPos(aGameObject.getyPos() + rand.nextInt(10) ); //gives them their random position on the screen
            aGameObject.setxPos(aGameObject.getxPos() + rand.nextInt(10) ); //gives them their random position on the screen
        }

        //puts all the enemies at their default place
        for (Enemy aGameObject : gameObjects)
        {
            aGameObject.setyPos(aGameObject.getyPos() + rand.nextInt(10) ); //gives them their random position on the screen
            aGameObject.setxPos(aGameObject.getxPos() + rand.nextInt(10) ); //gives them their random position on the screen
        }

        //draws all the tasks we need and sets their position
        for (GameObject aGameObject : tasks)
        {
            aGameObject.setyPos(aGameObject.getyPos() + rand.nextInt(10) ); //gives them their random position on the screen
            aGameObject.setxPos(aGameObject.getxPos() + rand.nextInt(10) ); //gives them their random position on the screen
        }

        gameScreen.repaint(); //calls the repaint function
        loop = new Thread(this); //loops the thread
        loop.start(); //starts the loop

    }

    //override for the runnable interface
    @Override
    public void run() {
        //to make sure it will always run
        while(true)
        {
            //for each game object in the list of game objects
            for (Enemy aGameObject : gameObjects)
            {
                //how the objects move on the screen, they want to track the players movement and follow them, unless they are weak
                //if they are not weak to a player attack
                if(aGameObject.isWeak() == false)
                {
                    //if the player is positive x away from them
                    if(player.getxPos() > aGameObject.getxPos())
                    {
                        //they will move toward the player with each step
                        aGameObject.setxPos(aGameObject.getxPos() + 1);
                    }
                    //if the player is positive y away from them
                    else if(player.getyPos() > aGameObject.getyPos())
                    {
                        //they will move toward the player with each step
                        aGameObject.setyPos(aGameObject.getyPos() + 1);
                    }
                    //if the player is negative x away from them
                    else if(player.getxPos() < aGameObject.getxPos())
                    {
                        //they will move toward the player with each step
                        aGameObject.setxPos(aGameObject.getxPos() - 1);
                    }
                    //if the player is negative y away from them
                    else if(player.getyPos() < aGameObject.getyPos())
                    {
                        //they will move toward the player with each step
                        aGameObject.setyPos(aGameObject.getyPos() - 1);
                    }
                }
                //else if the are weak they want to move away from the player
                else if(aGameObject.isWeak())
                {
                    //if the player is positive x away from them
                    if(player.getxPos() > aGameObject.getxPos())
                    {
                        //they will move away the player with each step
                        aGameObject.setxPos(aGameObject.getxPos() - 1);
                    }
                    //if the player is positive y away from them
                    else if(player.getyPos() > aGameObject.getyPos())
                    {
                        //they will move away the player with each step
                        aGameObject.setyPos(aGameObject.getyPos() - 1);
                    }
                    //if the player is negative x away from them
                    else if(player.getxPos() < aGameObject.getxPos())
                    {
                        //they will move away the player with each step
                        aGameObject.setxPos(aGameObject.getxPos() + 1);
                    }
                    //if the player is negative y away from them
                    else if(player.getyPos() < aGameObject.getyPos())
                    {
                        //they will move away the player with each step
                        aGameObject.setyPos(aGameObject.getyPos() + 1);
                    }
                }

                //aGameObject.setyPos(aGameObject.getyPos() + rand.nextInt(10) ); //gives them their random position on the screen
            }

            //make the thread sleep for 50 ms, so it doesnt update too quickly
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //check to see if the player has collided with anything
            checkCollisions();

            //check if the player has been defeated by the enemies
            if(health <= 0)
            {
                //if they have 0 or less health, the game is over
                JOptionPane.showOptionDialog(this, "You have been overwhelmed by students.", "You lost!", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null); //display a message when they have lost
                this.dispose(); //closes the game
                break; //ends the loop
            }

            //if they complete all 10 tasks
            if(tasksComplete == NUMTASK)
            {
                //they win the game
                JOptionPane.showOptionDialog(this, "You have graded all assignments.", "You won!", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null); //display a message when they have won
                this.dispose(); //closes the game
                break; //ends the loop
            }

            //goes through the list of enemies and makes sure they havent left the screen. If they have, it puts them back at the top in a random spot
            for(Enemy anEnemy :gameObjects)
            {
                checkBoneOffScreen((Enemy)anEnemy);
            }

            CheckPlayerOffScreen(); //check if the player is off screen
            gameScreen.repaint(); //calls the repaint function to update the screen
            scoreLbl.setText("Score: " + score); //updates the score of the player
        }
    }

    /**
     * This is the inner class for the Key Listener. It handles how the player moves when they push the arrow keys to move the object on the screen.
     */
    private class GameKeyListener implements KeyListener {

        //needs to be here for the Key Listener, but the game will not do anything if a key is typed
        @Override
        public void keyTyped(KeyEvent e) {

        }

        //handles if the user presses a key, the arrow keys, to move the player on screen
        @Override
        public void keyPressed(KeyEvent e) {
            //if they pushed the right arrow key
            if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                //adjusts the position of the player
                player.setxPos(player.getxPos() + Player.PLAYER_SPEED);
                player.setSubImageY(2); //Want the images where character moves to the right

               // Want the center image where he is just standing
                if (player.getSubImageX() <2)
                {
                    player.setSubImageX(player.getSubImageX() + 1); //adjusts their image 1 more than it was
                }
                else
                {
                    player.setSubImageX(0); //the default sub image
                }

            }

            //if the user pushed the left key
            if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                //adjust the position of the player
                player.setxPos(player.getxPos() - Player.PLAYER_SPEED);
                player.setSubImageY(1); //Want the images where character moves to the right
                // Want the center image where he is just standing
                if (player.getSubImageX() <2)
                {
                    player.setSubImageX(player.getSubImageX() + 1);
                }
                else
                {
                    player.setSubImageX(0); //the default player position
                }
            }

            //if the user pushed the up arrow
            if (e.getKeyCode()==KeyEvent.VK_UP) {
                //adjust the position of the player
                player.setyPos(player.getyPos() - Player.PLAYER_SPEED);
                player.setSubImageY(3); //Want the images where character moves to the right
                // Want the center image where he is just standing
                if (player.getSubImageX() <2)
                {
                    player.setSubImageX(player.getSubImageX() + 1);
                }
                else
                {
                    player.setSubImageX(0); //the default player image
                }
            }

            //if the user pushed the down arrow
            if (e.getKeyCode()==KeyEvent.VK_DOWN) {
                //adjust the position of the player
                player.setyPos(player.getyPos() + Player.PLAYER_SPEED);
                player.setSubImageY(0); //Want the images where character moves to the right
                // Want the center image where he is just standing
                if (player.getSubImageX() <2)
                {
                    player.setSubImageX(player.getSubImageX() + 1);
                }
                else
                {
                    player.setSubImageX(0); //the default player image
                }
            }

            /**
             * The cheat implemented in the game is right here. Press 'g' key to make all the enemies weak
             */
            //for god mode
            if(e.getKeyCode() == 71)
            {
                //make all the enemies weak to the player always
                for(Enemy anEnemy : gameObjects)
                {
                    anEnemy.GodMode(gameScreen); //make them weak to player attacks
                }
            }

            //update the position of the players collision box
            player.UpdateCollisionBoxPosition();
            repaint(); //redraw the screen
        }

        //override of the key released function
        @Override
        public void keyReleased(KeyEvent e) {
            //Set the place to the middle picture in the first row...standing position
            player.setSubImageX(1);
        }
    }

    //checks to see if the enemies have moved off the screen and wraps them around if they have
    public void checkBoneOffScreen(Enemy aBone)
    {
        //if they have walked off the bottom of the screen
        if(aBone.getyPos()>GameManager.HEIGHT)
        {
            aBone.setyPos(0); //put them at the top
        }

        //if they have walked off the top of the screen
        if(aBone.getyPos()<0)
        {
            aBone.setyPos(GameManager.HEIGHT); //put them at the bottom
        }

        //if they have walked off the right side
        if(aBone.getxPos()>GameManager.WIDTH)
        {
            aBone.setxPos(0); //bring them around to the other side
        }

        //if they have walked off the left side
        if(aBone.getxPos()<0)
        {
            aBone.setxPos(GameManager.WIDTH); //brings them around to the other side
        }
    }

    //check if the player is off screen and wraps around if they are
    public void CheckPlayerOffScreen()
    {
        //if they have walked off the bottom of the screen
        if(player.getyPos()>GameManager.HEIGHT)
        {
            player.setyPos(0); //put them at the top
        }

        //if they have walked off the top of the screen
        if(player.getyPos()<0)
        {
            player.setyPos(GameManager.HEIGHT); //put them at the bottom
        }

        //if they have walked off the right side
        if(player.getxPos()>GameManager.WIDTH)
        {
            player.setxPos(0); //bring them around to the other side
        }

        //if they have walked off the left side
        if(player.getxPos()<0)
        {
            player.setxPos(GameManager.WIDTH); //brings them around to the other side
        }
    }


    //Accessors and mutators
    public int getWindowHeight()
    {
        return this.getHeight();
    }

    public int getWindowWidth()
    {
        return this.getWidth();
    }

    //checks to see if an object has collided with another
    public void checkCollisions()
    {
        //makes an array list of objects that need to be removed, so they arent removed too early.
        ArrayList<GameObject>objectToBeRemoved = new ArrayList<GameObject>();
        ArrayList<GameObject>powerToBeRemoved = new ArrayList<GameObject>();
        ArrayList<GameObject>taskToBeRemoved = new ArrayList<GameObject>();


        //check all the enemies in the list
        for(Enemy anObject : gameObjects)
        {
            //check if they are weak to player attacks
            if(anObject.isWeak())
            {
                //type cast as the needed type
                Enemy aBone = (Enemy)anObject;
                //if they player has collided with that object
                if (player.getCollisionBox().intersects(aBone.getCollisionBox()))
                {
                    objectToBeRemoved.add(anObject); //add the object to the list of ones that will be removed
                    score = score + 500; //gets 500 points for defeating an enemy
                }
            }

            //if the enemy is not weak to the player, it will damage the player
            else if (!anObject.isWeak())
            {
                //type cast as the needed type
                Enemy aBone = (Enemy)anObject;
                //if they player has collided with that object. if the player can be damaged
                if (player.getCollisionBox().intersects(aBone.getCollisionBox()) && player.isWeak())
                {
                    health = health - 1; //the player loses a some of their health
                    player.takeDamage(); //to make sure the player has some time to get away
                    lives.setText("Lives: " + health); //updates their health
                }
            }
        }

        //check all the powerups in the list
        for(GameObject anObject : powerUps) {
            //type cast as the needed type
            Powerup aPower = (Powerup) anObject;
            //if they player has collided with that object
            if (player.getCollisionBox().intersects(aPower.getCollisionBox()))
            {
                Powerup power = (Powerup) anObject; //makes a powerup that will be used by the player
                powerToBeRemoved.add(anObject); //add the object to the list of ones that will be removed
                power.UsePowerup(gameObjects, gameScreen); //make all the enemies weak
                score = score + 200; //gets 200 points for consuming a powerup
            }
        }

        //checks all the tasks
        //check all the enemies in the list
        for(GameObject anObject : tasks) {
            //type cast as the needed type
            Task aTask = (Task)anObject;
            //if they player has collided with that object
            if (player.getCollisionBox().intersects(aTask.getCollisionBox()))
            {
                taskToBeRemoved.add(anObject); //add the object to the list of ones that will be removed
                score = score + 300; //gets 300 points for finishing a task
                tasksComplete = tasksComplete + 1; //they completed another task
            }
        }

        //go through the list of objects that need to me removed
        for(GameObject anObject : objectToBeRemoved)
        {
            gameObjects.remove(anObject); //remove the objects from the list
        }

        //go through the list of powerups and remove them
        for(GameObject anObject : powerToBeRemoved)
        {
            powerUps.remove(anObject); //remove the objects from the list
        }

        //go through the list of tasks and remove them
        for(GameObject anObject : taskToBeRemoved)
        {
            tasks.remove(anObject); //remove the objects from the list
        }
    }

}
