package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


/**
 * The Game Screen is the JPanel that the images will appear on.
 * It will be used to show the game components and what they will do such as buttons that can be pressed and the images.
 *
 */

public class GameScreen extends JPanel {

    private HumanPlayer playerReference; //for the human player
    private ComputerPlayer computerPlayerReference; // for the computer player


    //Constructor sets the background color of the panel
    public GameScreen()
    {
        this.setBackground(Color.GRAY);
    }

    //We need to be able to
    public void setPlayerReferenceInScreen(HumanPlayer pPlayer, ComputerPlayer cPlayer)
    {

        playerReference = pPlayer;
        computerPlayerReference = cPlayer;
    }


    @Override
    //to draw the current pokemon
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerReference != null) {
            playerReference.draw(g);
        }

        if (computerPlayerReference != null) {
            computerPlayerReference.draw(g);
        }
    }
}
