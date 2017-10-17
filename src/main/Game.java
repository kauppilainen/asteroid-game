package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.Key;

import java.nio.charset.Charset;


public class Game {
    // Declare variables
    Terminal terminal;
    PlayerObject player;
    Key key;
    Render render;

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
        terminal.setCursorVisible(false);   // Makes cursor

        while (true){

            key = terminal.readInput();     // Get key input from terminal
            if(key != null){                // If a key press has happened
                input(key);                 // Send key to input where the input is dealt with
            }

            render.drawPlayer(player); // Send player info to the render method drawPlayer to be drawn

            Thread.sleep(200); // Pause program for 200ms

        }
    }

    public void input(Key key){
        switch (key.getKind()){ // If key input was one of our expected cases, do the case instruction
            case ArrowUp:
                System.out.println("upp");
                break;
            case ArrowDown:

                break;
            case ArrowLeft:

                break;
            case ArrowRight:

                break;
        }

    }


}
