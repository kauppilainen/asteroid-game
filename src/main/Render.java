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
        if (player.getLives() == 2){
            terminal.applyForegroundColor(Terminal.Color.YELLOW);
        }
        else if (player.getLives()== 1){
            terminal.applyForegroundColor(Terminal.Color.RED);
        }
        else {
            terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        }
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

    public void drawProjectile(Projectile proj) {
        terminal.moveCursor(proj.getxPos(), proj.getyPos());
        terminal.applyForegroundColor(Terminal.Color.YELLOW);
        terminal.putCharacter(proj.getSymbol());
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }

    public void drawAstroidExplosion(Asteroid asteroid){
        terminal.applyBackgroundColor(Terminal.Color.YELLOW);
        terminal.applyForegroundColor(Terminal.Color.RED);

        for (int x = asteroid.getxPos()-1; x < asteroid.getxPos()+2 ; x++) {
            for (int y = asteroid.getyPos()-1; y < asteroid.getyPos()+2 ; y++) {
                terminal.moveCursor(x,y);
                terminal.putCharacter('\u2593');
            }
        }
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
    }

    public void printGameOver (int points){
        terminal.clearScreen();
        String gameOver = "Game Over!";
        String pointString ="Points: "+points;
        terminal.moveCursor(45,10);
        for (int i = 0; i < gameOver.length() ; i++) {
            terminal.putCharacter(gameOver.charAt(i));
        }
        terminal.moveCursor(45,12);
        for (int i = 0; i < pointString.length() ; i++) {
            terminal.putCharacter(pointString.charAt(i));
        }


    }

    public void drawAlienObject(AlienObject alien){ // Method to draw alien position
        terminal.moveCursor(alien.getxPos(),alien.getyPos()); // Move cursor to alien position
        terminal.putCharacter(alien.getSymbol()); // Puts character on screen
    }


}//end of class
