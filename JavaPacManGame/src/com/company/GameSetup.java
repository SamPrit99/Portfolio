package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The game setup is used to give the player all the information they need and allow them to begin the game when they want.
 * It will display the needed information about the game and contain a start button. When the user presses the button, it will
 * make a
 */
public class GameSetup extends JFrame implements ActionListener {

    public final static int WIDTH = 1000; //sets the width
    public final static int HEIGHT = 600; //sets the height
    private JButton enter; //the enter button to begin the game
    private JTextArea information; //for the game information
    private JPanel informationPanel; //panel for the information
    private GameManager theGame; //the game manager for the game

    //constructor for the game setup
    public GameSetup()
    {
        this.setTitle("Game Setup"); //set the title
        this.setSize(WIDTH, HEIGHT); //make the window the desired hight and width
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //sets the default close operation
        information = new JTextArea(); //make the text area
        information.setText("Play as Jody and grade assignments \n that have piled up all semester. \n Avoid the pesky students until you've had your pepsi. \n Then you can deal with them and send them away. \n Grade all assignments to win. \n Run into a student 3 times and you lose. \n Psst. If you press the g key, \n you can supercharge Jody and he can fight off students without his pepsi.");
        information.setLineWrap(true); //wrap text
        information.setColumns(50); //number of cols
        information.setEditable(false); //cannot edit
        information.setVisible(true); //can see it

        informationPanel = new JPanel(); //make the panel
        informationPanel.add(information); //adds to the information panel

        enter = new JButton("Start"); //makes button to start the game
        enter.addActionListener(this); //add action listener
        informationPanel.add(enter); //adds to the information panel

        this.add(informationPanel); //adds the pannel to the JFrame
        this.setVisible(true); //make the window visible

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //if the uses pushes the start button
        String s = e.getActionCommand();
        if (s.equals("Start"))
        {
            theGame = new GameManager(); //makes the game window
            this.dispose(); //closes this window
        }
    }

}

