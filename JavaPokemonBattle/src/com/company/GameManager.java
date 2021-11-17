package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * The game manager is how the game functions. It keeps track of everything that is happening.
 * It will be used to ensure the game keeps going until the end and that all the needed functions are called at the right times.
 * The player interacts with the game through buttons that appear on the screen. The button presses are handled by action listeners that are inner classes of the GameManager.
 */


public class GameManager extends JFrame {
    public final static int WIDTH = 1500; //sets the width
    public final static int HEIGHT = 600; //sets the height
    private GameScreen gameScreen;  //The screen on which we will draw
    private JPanel buttonScreen; //screen for the buttons and labels
    private JPanel infoScreen; //screen for the information about the game
    private Player human; //the human player
    private Player computer; //the computer player
    private String playerName = "Player1"; //to be used for the name the user wants to use
    private String[] playerPokemon = new String[2]; //list of the pokemon that the player has chosen
    private String[] computerPokemon = new String[2]; //list of the pokemon the computer will pick

    //buttons that will appear on the panel
    private JButton attackButton; //the button for the attack
    private JButton attack2Button; //the button for the second attack
    private JButton itemButton; //the button for the first item
    private JButton itemButton2; //the button for the second item
    private JButton switchButton; //the button for the switch feature
    private JButton runButton; //the button that will allow them for forfeit
    private JButton summaryButton; //button that will show the summary
    private JLabel playerHPlbl; // label to show the player hp
    private JLabel computerHPlbl; //label to show the computer hp
    private JLabel summarylbl; //label to show the summary of what happened

    private GameManager theGUI; //will be used to pass into the action listeners
    //the listeners for each of the buttons
    private Attack1 attackListener;
    private Attack2 attack2Listener;
    private Item itemListener;
    private Item2 item2Listener;
    private SwitchPokemon switchListener;
    private Summary summaryListener;
    private Run runListener;

    //constructor for the game manager
    public GameManager(String pName, String[] pPokemons, String[] cPokemons)
    {
        this.setTitle("Sam's Pokemon Game"); //set the title
        this.setSize(WIDTH, HEIGHT); //make the window the desired height and width
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //sets the default close operation
        this.setVisible(true); //make the window visible

        theGUI = this; //makes this the GUI for the listeners

        playerName = pName; //takes the name passed in and makes it the player name
        playerPokemon = pPokemons; //takes the list passed in and makes it the list of pokemon
        computerPokemon = cPokemons; //takes the list passed in and makes it the list of pokemon for the computer
        InstantiatePlayers(); //will set up the players
        human.ChoosePokemon(playerPokemon); //lets the human player choose their pokemon
        computer.ChoosePokemon(computerPokemon);

        gameScreen = new GameScreen(); //Instantiate the panel to draw the images
        this.add(gameScreen, BorderLayout.CENTER);  //put the JPanel on the center of the screen
        gameScreen.setPlayerReferenceInScreen((HumanPlayer)human, (ComputerPlayer)computer);
        gameScreen.repaint();
        buttonScreen = new JPanel(); //gets the button screen setup
        infoScreen = new JPanel(); //gets the info screen setup
        this.add(buttonScreen, BorderLayout.SOUTH); //adds the button screen to the Frame
        this.add(infoScreen, BorderLayout.EAST); //adds the info screen to the frame
        SetUpButtons(); //sets the buttons up
    }

    //will be used to get the players set up
    public void InstantiatePlayers()
    {
        human = new HumanPlayer(playerName); //makes the human player with the name entered
        computer = new ComputerPlayer("Computer"); //makes a computer player with the name computer

    }

    //will be used to set up the buttons that can be used by the player
    public void SetUpButtons()
    {
        //sets up the action listeners that will be needed for the buttons
        attackListener = new Attack1(theGUI);
        attack2Listener = new Attack2(theGUI);
        itemListener = new Item(theGUI);
        item2Listener = new Item2(theGUI);
        switchListener = new SwitchPokemon(theGUI);
        summaryListener = new Summary(theGUI);
        runListener = new Run(theGUI);


        playerHPlbl = new JLabel("Player HP: " + human.PokemonHP()); //makes the player hit points label
        buttonScreen.add(playerHPlbl); //adds the label to the screen
        computerHPlbl = new JLabel("Computer HP: " + computer.PokemonHP()); //makes the computer hit points label
        buttonScreen.add(computerHPlbl); //adds the computer hp label to the screen

        String[] attacks = human.PokemonAttacks(); //to get the attacks the active pokemon has to make them the button text

        attackButton = new JButton(attacks[0]); //makes the button
        attackButton.addActionListener(attackListener); //adds the action listener
        buttonScreen.add(attackButton); //adds it to the panel
        attack2Button = new JButton(attacks[1]); //makes the button
        attack2Button.addActionListener(attack2Listener); //adds the action listener
        buttonScreen.add(attack2Button); //adds it to the panel
        itemButton = new JButton("Potion"); //makes the button
        itemButton.addActionListener(itemListener); //adds the action listener
        buttonScreen.add(itemButton); //adds the items button
        itemButton2 = new JButton("Super Potion"); //makes the button
        itemButton2.addActionListener(item2Listener); //adds the action listener
        buttonScreen.add(itemButton2); //adds the items button
        switchButton = new JButton("Switch"); //makes the button
        switchButton.addActionListener(switchListener); //adds the action listener
        buttonScreen.add(switchButton); //adds the switch button to the screen
        summaryButton = new JButton("Summary"); //makes the button
        summaryButton.addActionListener(summaryListener); //sets up the action listener
        buttonScreen.add(summaryButton); //adds the summary button
        runButton = new JButton("Run"); //makes the button
        runButton.addActionListener(runListener); //adds the action listener
        buttonScreen.add(runButton); //adds the run button to the screen

        summarylbl = new JLabel("This is the summary label"); //makes the summary label
        infoScreen.add(summarylbl); //adds the summary label to the panel
    }


     // Inner classes for the action listeners for each of the buttons. Will handel when each of the buttons are pushed and preform the needed action when they are.


    /**
     * This is the first attack class that will handel when the first attack button is pressed.
     */
    private class Attack1 implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public Attack1(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //needs to check to make sure there is pp left for the move
            if(human.PokemonPP() == 0)
            {
                summarylbl.setText("You do not have enough pp for that move.");
            }
            else
            {
                //check if it wants to switch first
                boolean computerSwitch = computer.CheckIfSwitch();
                //for the computer to make their choice
                if(computerSwitch == true)
                {
                    computer.SwitchPokemon();
                    computer.setHasSwitched(true); //to make sure it only switches once
                    gameScreen.repaint();
                    //has the users pokemon attack using their choice of move
                    human.PokemonAttack(computer.getActivePokemon(), 1);
                    summarylbl.setText(human.PokemonTurnSummary() + " " + human.PokemonPP() + " PP left. Computer has switched Pokemon."); //Displays the summary on the screen
                    playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                    computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp


                }
                //if the computer does not switch
                else
                {
                    //has the users pokemon attack using their choice of move
                    human.PokemonAttack(computer.getActivePokemon(), 1);
                    int computerChoice = computer.ChooseMove(human); //the move the computer wants to use
                    computer.PokemonAttack(human.getActivePokemon(), computerChoice); //has the computer attack the player
                    summarylbl.setText(human.PokemonTurnSummary() + " " + human.PokemonPP() + " PP left. " + computer.PokemonTurnSummary()); //Displays the summary on the screen
                    playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                    computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
                }
                //no matter what it will check if the pokemon have fainted
                //check to see if you 0 or fewer hit points and will force you out if you do.
                if(human.PokemonHP() <= 0)
                {
                    human.SwitchPokemon(); //forces you to switch if you have 0 or fewer hit points
                    playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                    //to get the active pokemon's attacks for the buttons
                    String[] attacks = human.PokemonAttacks();
                    attackButton.setText(attacks[0]); //makes the first attack button the text of the first attack
                    attack2Button.setText(attacks[1]); //makes the second attack button the text of the second attack
                    gameScreen.repaint();

                    //checks to make sure that your other pokemon is up too
                    if(human.PokemonHP() <= 0)
                    {
                        //if both of your pokemon are out, you have lost the game
                        human.Run(theGUI); //calls the player run function
                        theGUI.dispose(); //closes the program
                    }
                }
                //check to see if the computer has fewer than 0 hit points
                if(computer.PokemonHP() <= 0)
                {
                    computer.SwitchPokemon(); //forces the computer to switch
                    computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
                    gameScreen.repaint();

                    //checks to see if the computer has lost
                    if(computer.PokemonHP() <= 0)
                    {
                        //if both of the computer pokemon have no hit points, you win
                        JOptionPane.showMessageDialog(theGUI, "You have won the game! Congratz!"); //shows the user a popup that will say player has won the game
                        theGUI.dispose(); //closes the program
                    }
                }
            }
        }
    }

    /**
     * This is the second attack class that will handel when the second attack button is pressed.
     */
    private class Attack2 implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public Attack2(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //check if it wants to switch first
            boolean computerSwitch = computer.CheckIfSwitch();
            //for the computer to make their choice
            if(computerSwitch == true)
            {
                computer.SwitchPokemon();
                computer.setHasSwitched(true); //to make sure it only switches once
                gameScreen.repaint();
                //has the users pokemon attack using their choice of move
                human.PokemonAttack(computer.getActivePokemon(), 2);
                summarylbl.setText(human.PokemonTurnSummary() + " Computer has switched Pokemon."); //Displays the summary on the screen
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
            }
            //if the computer does not switch
            else
            {
                //has the users pokemon attack using their choice of move
                human.PokemonAttack(computer.getActivePokemon(), 2);
                int computerChoice = computer.ChooseMove(human); //the move the computer wants to use
                computer.PokemonAttack(human.getActivePokemon(), computerChoice); //has the computer attack the player
                summarylbl.setText(human.PokemonTurnSummary() + " " + computer.PokemonTurnSummary()); //Displays the summary on the screen
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
            }

            //no matter what it will check if the pokemon have fainted
            //check to see if you 0 or fewer hit points and will force you out if you do.
            if(human.PokemonHP() <= 0)
            {
                human.SwitchPokemon(); //forces you to switch if you have 0 or fewer hit points
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                //to get the active pokemon's attacks for the buttons
                String[] attacks = human.PokemonAttacks();
                attackButton.setText(attacks[0]); //makes the first attack button the text of the first attack
                attack2Button.setText(attacks[1]); //makes the second attack button the text of the second attack
                gameScreen.repaint();

                //checks to make sure that your other pokemon is up too
                if(human.PokemonHP() <= 0)
                {
                    //if both of your pokemon are out, you have lost the game
                    human.Run(theGUI); //calls the player run function
                    theGUI.dispose(); //closes the program
                }
            }
            //check to see if the computer has fewer than 0 hit points
            if(computer.PokemonHP() <= 0)
            {
                computer.SwitchPokemon(); //forces the computer to switch
                computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
                gameScreen.repaint();

                //checks to see if the computer has lost
                if(computer.PokemonHP() <= 0)
                {
                    //if both of the computer pokemon have no hit points, you win
                    JOptionPane.showMessageDialog(theGUI, "You have won the game! Congratz!"); //shows the user a popup that will say player has won the game
                    theGUI.dispose(); //closes the program
                }
            }
        }
    }

    /**
     * This is the Item class that will handel when the item button is pressed.
     */
    private class Item implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public Item(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //if they have not used the potion yet
            if(human.isHasUsedPotion() == false)
            {
                human.UsePotion(); //uses the potion on the current pokemon
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                human.setHasUsedPotion(true); //sets the has used potion to true
            }
            //if they have
            else if(human.isHasUsedPotion() == true)
            {
                summarylbl.setText("You have already used your potion."); //tell them they have already
            }
        }
    }

    /**
     * This is the Item2 class that will handel when the item button is pressed.
     */
    private class Item2 implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public Item2(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //if they have not used the potion yet
            if(human.isHasUsedSuperPotion() == false)
            {
                human.UseSuperPotion(); //uses the super potion on the current pokemon
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                human.setHasUsedSuperPotion(true); //sets the has used potion to true
            }
            //if they have
            else if(human.isHasUsedSuperPotion() == true)
            {
                summarylbl.setText("You have already used your super potion."); //tell them they have already
            }
        }
    }

    /**
     * This is the switch class that will handel when the switch button is pressed.
     */
    private class SwitchPokemon implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public SwitchPokemon(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //even if the player switches, the computer still gets to attack, if they do not want to switch
            //check if it wants to switch first
            boolean computerSwitch = computer.CheckIfSwitch();

            if(computerSwitch == true)
            {
                computer.SwitchPokemon();
                computer.setHasSwitched(true); //to make sure it only switches once
                //calls the human player switch function
                human.SwitchPokemon();
                //updates the information on the label
                playerHPlbl.setText("Player HP: " + human.PokemonHP());
                computerHPlbl.setText("Computer HP: " + computer.PokemonHP());
                //to get the active pokemon's attacks for the buttons
                String[] attacks = human.PokemonAttacks();
                attackButton.setText(attacks[0]); //makes the first attack button the text of the first attack
                attack2Button.setText(attacks[1]); //makes the second attack button the text of the second attack
                summarylbl.setText(human.getName() + " has switched Pokemon. Computer has switched Pokemon."); //Displays the summary on the screen
                //redraws the panel to reflect the change in pokemon
                gameScreen.repaint();
            }

            //if the computer does not switch
            else
            {
                //the computer chooses a move before the player switches
                int computerChoice = computer.ChooseMove(human); //the move the computer wants to use
                //the player switches first
                //calls the human player switch function
                human.SwitchPokemon();
                //updates the information on the label
                playerHPlbl.setText("Player HP: " + human.PokemonHP());
                //to get the active pokemon's attacks for the buttons
                String[] attacks = human.PokemonAttacks();
                attackButton.setText(attacks[0]); //makes the first attack button the text of the first attack
                attack2Button.setText(attacks[1]); //makes the second attack button the text of the second attack
                //redraws the panel to reflect the change in pokemon
                gameScreen.repaint();
                //has the computers pokemon attack using their choice of move
                computer.PokemonAttack(human.getActivePokemon(), computerChoice); //has the computer attack the player
                summarylbl.setText(human.getName() + " has switched Pokemon. " + computer.PokemonTurnSummary()); //Displays the summary on the screen
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
            }
            //no matter what it will check if the pokemon have fainted
            //check to see if you 0 or fewer hit points and will force you out if you do.
            if(human.PokemonHP() <= 0)
            {
                human.SwitchPokemon(); //forces you to switch if you have 0 or fewer hit points
                playerHPlbl.setText("Player HP: " + human.PokemonHP()); //updates the player hp
                //to get the active pokemon's attacks for the buttons
                String[] attacks = human.PokemonAttacks();
                attackButton.setText(attacks[0]); //makes the first attack button the text of the first attack
                attack2Button.setText(attacks[1]); //makes the second attack button the text of the second attack
                gameScreen.repaint();

                //checks to make sure that your other pokemon is up too
                if(human.PokemonHP() <= 0)
                {
                    //if both of your pokemon are out, you have lost the game
                    human.Run(theGUI); //calls the player run function
                    theGUI.dispose(); //closes the program
                }
            }
            //check to see if the computer has fewer than 0 hit points
            if(computer.PokemonHP() <= 0)
            {
                computer.SwitchPokemon(); //forces the computer to switch
                computerHPlbl.setText("Computer HP: " + computer.PokemonHP()); //updates the computer hp
                gameScreen.repaint();

                //checks to see if the computer has lost
                if (computer.PokemonHP() <= 0) {
                    //if both of the computer pokemon have no hit points, you win
                    JOptionPane.showMessageDialog(theGUI, "You have won the game! Congratz!"); //shows the user a popup that will say player has won the game
                    theGUI.dispose(); //closes the program
                }
            }
        }
    }

    /**
     * This is the summary class that will handel when the summary button is pressed.
     */
    private class Summary implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public Summary(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            summarylbl.setText(human.PokemonSummary()); //makes the Pokemon summary the
        }
    }

    /**
     * This is the run class that will handel when the run button is pressed.
     */
    private class Run implements ActionListener
    {
        //so it can access the GUI
        GameManager theGUI;
        public Run(GameManager pGUI) {theGUI = pGUI;}

        //needs to implemented due to implements command abstract method that must be made here
        @Override
        public void actionPerformed(ActionEvent e)
        {
            human.Run(theGUI); //calls the player run function
            theGUI.dispose(); //closes the program
        }
    }
}

