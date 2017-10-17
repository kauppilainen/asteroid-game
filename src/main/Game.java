package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.charset.Charset;


public class Game {


    // Declare variables
    private Terminal terminal;
    private PlayerObject player;
    private Key key;
    private Render render;


    public Game(){ // Constructor
        // Initialize our variables
        this.terminal = TerminalFacade.createTerminal(System.in,
                                                      System.out,
                                                      Charset.forName("UTF8"));
        render = new Render(terminal); // Create new Render object with terminal as parameter

    }

    public void run() throws InterruptedException{  // Method to run your game
        this.player = new PlayerObject(50,15); // Create new player object
        terminal.enterPrivateMode();        // Method to create window
        terminal.setCursorVisible(false);   // Makes cursor invisible

        while (true){

            key = terminal.readInput();     // Get key input from terminal
            if(key != null){                // If a key press has happened
                input(key);                 // Send key to input where the input is dealt with
            }

            render.drawPlayer(player); // Send player info to the render method drawPlayer to be drawn

            Thread.sleep(20); // Pause program for 20ms
            terminal.clearScreen();
        }
    }

    public void input(Key key){
        switch (key.getKind()){ // If key input was one of our expected cases, do the case instruction
            case ArrowUp:
                System.out.println("upp");
                player.moveForward();
                break;
            case ArrowDown:
                System.out.println("ner");

                break;
            case ArrowLeft:
                System.out.println("vänster");
                player.setDirection(-1);//turn left
                break;
            case ArrowRight:
                System.out.println("höger");
                player.setDirection(1);//turn right
                break;
        }

    }

}
