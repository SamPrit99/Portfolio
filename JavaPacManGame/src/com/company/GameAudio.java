package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

/**
 * This is yje game audio class. It is for the different music that plays in the game.
 */

public class GameAudio implements Runnable {

    private File music; //the file that will be played
    private AudioInputStream audioInput; //the kind of audio

    //the function that starts the thread of the game audio
    public GameAudio()
    {
        Thread audioloop = new Thread(this); //makes a new thread
        audioloop.start(); //starts the thread
    }

    //the override of the game audio class needed because implements runnable
    @Override
    public void run() {
        music = new File("music.wav"); //sets the needed file path
        try {
            audioInput = AudioSystem.getAudioInputStream(music); //makes a new audio input
            Clip clip = AudioSystem.getClip(); //gets the needed clip
            clip.open(audioInput); //opens the audio file
            clip.start(); //starts the clip
            clip.loop(Clip.LOOP_CONTINUOUSLY); //loops continuously


        }
        //in case the music doesnt load properly
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Music messed up " + ex.getMessage());
        }
    }
}
