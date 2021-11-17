package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameSetUp is used to set up the game at the start and to have the User enter their name and select their pokemon.
 */

public class GameSetup extends JFrame implements ActionListener {

    public final static int WIDTH = 1000; //sets the width
    public final static int HEIGHT = 600; //sets the height
    private String playerName = "Player1"; //to be used for the name the user wants to use
    private JTextField userNameText; //the text field that will be used to get the users name
    private JButton enterButton; //the button that will be pushed to get the users name
    private JPanel p;
    private JPanel section1;
    private JPanel section2;
    private JPanel informationPanel;
    private JTextArea information; //for the game information

    //the different radio buttons that will be selected from for the player to pick their pokemon
    //there are two of each to separate the different lines and that the player can choose two pokemon
    //the player can have two of the same pokemon
    private JRadioButton charmander1;
    private JRadioButton charmander2;
    private JRadioButton squirtle1;
    private JRadioButton squirtle2;
    private JRadioButton bulbasaur1;
    private JRadioButton bulbasaur2;
    private JRadioButton pikachu1;
    private JRadioButton pikachu2;

    private ButtonGroup group1;
    private ButtonGroup group2;

    private String[] playerPokemon = new String[2]; //list of the pokemon that the player has chosen
    private String[] computerPokemon = new String[2]; //list of the pokemon that the computer has chosen

    private GameManager theGame;

    //constructor for the game manager
    public GameSetup()
    {
        this.setTitle("Game Setup"); //set the title
        this.setSize(WIDTH, HEIGHT); //make the window the desired hight and width
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //sets the default close operation
        NameandPokemonSetUp(); //will get the uer input
        this.setVisible(true); //make the window visible

    }

    /**
     * This is the initial setup of the game. It will open a popup window that will ask the user to select two Pokemon using radio buttons and ask them to enter their name.
     * It will listen for the action of the button being pressed and will change the name and playerTeam to the corresponding values that were inputted and then close the window.
     */
    public void NameandPokemonSetUp()
    {
        // create a new button
        enterButton = new JButton("Enter");
        //adds ActionListener to the button
        enterButton.addActionListener(this);
        JLabel enterNameLbl = new JLabel("Enter your name: "); //label to tell user to enter their name
        //sets the JTextField to have 16 columns
        userNameText = new JTextField("", 16);
        p = new JPanel(); //creates a Panel that will be used to display the text field and the button
        section1 = new JPanel(); //panel for the first group of radio buttons
        section2 = new JPanel(); //panel for the second group of radio buttons

        information = new JTextArea();
        information.setText("The game allows you to select two Pokemon. \n The computer will take the Pokemon that are strong against the ones you choose, \n unless you choose Pikachu, which will cause the computer to pick Pikachu as well. \n Pokemon attack and each attack has a chance to hit or miss. \n Some attacks are strong against other Pokemon and some are weak against other Pokemon. \n You have two potions, one that heals 50 hit points and one that heals 20 hit points. \n You can use them each once. \n Your first attack has a set number of uses, pp, and when you run out you must use tackle.");
        information.setLineWrap(true);
        information.setColumns(50);

        information.setEditable(false);
        information.setVisible(true);
        informationPanel = new JPanel();
        informationPanel.add(information); //adds to the information panel

        //will set the text of all the buttons in group 1
        charmander1 = new JRadioButton("Charmander");
        charmander1.setSelected(true); //will be selected by default
        squirtle1 = new JRadioButton("Squirtle");
        bulbasaur1 = new JRadioButton("Bulbasaur");
        pikachu1 = new JRadioButton("Pikachu");

        //labels that will appear next to the radio buttons
        JLabel lbl1 = new JLabel("Select First Pokemon: ");
        JLabel lbl2 = new JLabel("Select Second Pokemon: ");

        group1 = new ButtonGroup(); //makes the first group
        //adds each button to the group
        group1.add(charmander1);
        group1.add(squirtle1);
        group1.add(bulbasaur1);
        group1.add(pikachu1);

        //will set the text of all the buttons in group 2
        charmander2 = new JRadioButton("Charmander");
        charmander2.setSelected(true); //will be selected by default
        squirtle2 = new JRadioButton("Squirtle");
        bulbasaur2 = new JRadioButton("Bulbasaur");
        pikachu2 = new JRadioButton("Pikachu");
        group2 = new ButtonGroup(); //makes the second group
        //adds each button to the group
        group2.add(charmander2);
        group2.add(squirtle2);
        group2.add(bulbasaur2);
        group2.add(pikachu2);

        //adds the labels to the sections
        section1.add(lbl1);
        section2.add(lbl2);

        //adds the buttons to the needed panel
        section1.add(charmander1);
        section1.add(squirtle1);
        section1.add(bulbasaur1);
        section1.add(pikachu1);
        section2.add(charmander2);
        section2.add(squirtle2);
        section2.add(bulbasaur2);
        section2.add(pikachu2);


        p.add(enterNameLbl); //adds the label to the JPanel
        p.add(userNameText); //add the textField to the JPanel
        p.add(enterButton); //add the button to the JPanel
        this.add(section1, BorderLayout.NORTH); //adds the radio button panel to the JFrame
        this.add(section2, BorderLayout.CENTER); //adds the radio button panel to the JFrame
        this.add(p, BorderLayout.SOUTH); //add panel to the JFrame
        this.add(informationPanel, BorderLayout.WEST); //adds information bubble
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Enter")) {
            //if the enter button is pushed, it will make the player name string the text in the text box
            playerName = userNameText.getText();
            //checks which radio buttons were pressed and adds the associated names of the pokemon to the array of the player's pokemon
            //the opposing player will pick the pokemon that is string against the one chosen by the player
            if(charmander1.isSelected())
            {
                playerPokemon[0] = "Charmander";
                computerPokemon[0] = "Squirtle";
            }
            if(bulbasaur1.isSelected())
            {
                playerPokemon[0] = "Bulbasaur";
                computerPokemon[0] = "Charmander";
            }
            if(squirtle1.isSelected())
            {
                playerPokemon[0] = "Squirtle";
                computerPokemon[0] = "Bulbasaur";
            }
            if(pikachu1.isSelected())
            {
                playerPokemon[0] = "Pikachu";
                computerPokemon[0] = "Pikachu";
            }
            if(charmander2.isSelected())
            {
                playerPokemon[1] = "Charmander";
                computerPokemon[1] = "Squirtle";
            }
            if(bulbasaur2.isSelected())
            {
                playerPokemon[1] = "Bulbasaur";
                computerPokemon[1] = "Charmander";
            }
            if(squirtle2.isSelected())
            {
                playerPokemon[1] = "Squirtle";
                computerPokemon[1] = "Bulbasaur";
            }
            if(pikachu2.isSelected())
            {
                playerPokemon[1] = "Pikachu";
                computerPokemon[1] = "Pikachu";
            }
            //System.out.println(playerName + playerPokemon[0] + playerPokemon[1]);
            theGame = new GameManager(playerName, playerPokemon, computerPokemon); //makes the game window
            this.dispose(); //closes this window

        }
    }

}
