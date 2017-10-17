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


    public void drawAsteroid(Asteroid asteroid){ // Method to draw player position
        terminal.applyBackgroundColor(150,150,150);
        terminal.moveCursor(asteroid.getxPos(),asteroid.getyPos()); // Move cursor to player position
        terminal.putCharacter(asteroid.getSymbol()); // Puts character on screen
        terminal.moveCursor(asteroid.getxPos()+1,asteroid.getyPos());
        terminal.putCharacter(asteroid.getSymbol());
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);

    }

    public void drawProjectile(Projectile proj){
        terminal.moveCursor(proj.getxPos(), proj.getyPos());
        terminal.putCharacter(proj.getSymbol());
    }


}//end of class
