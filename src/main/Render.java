package main;

/*
Render: Ritar ut chars med färger i terminalfönstret.
*/

import com.googlecode.lanterna.terminal.Terminal;

public class Render {
    // Declare variables
    Terminal terminal;

    public Render(Terminal terminal){ // Constructor
        // Initialize our variables
        this.terminal = terminal; // Set terminal variable to terminal from in parameter
    }



    public void drawPlayer(PlayerObject player){ // Method to draw player position

        terminal.moveCursor(player.getxPos(),player.getyPos()); // Move cursor to player position
        terminal.putCharacter(player.getSymbol()); // Puts character on screen

    }

}//end of class
