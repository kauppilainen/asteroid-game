package main;

/*
Render: Ritar ut chars med färger i terminalfönstret.
*/

import com.googlecode.lanterna.terminal.Terminal;

public class Render {
    // Declare variables
    Terminal terminal;

    public Render(Terminal terminal) { // Constructor
        // Initialize our variables
        this.terminal = terminal; // Set terminal variable to terminal from in parameter
    }

    public void drawPlayer(Player player) { // Method to draw player position
        if(player.getLives() == 2) {
            terminal.applyForegroundColor(Terminal.Color.YELLOW);
        } else if(player.getLives() == 1) {
            terminal.applyForegroundColor(Terminal.Color.RED);
        } else {
            terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        }
        terminal.moveCursor(player.getxPos(), player.getyPos()); // Move cursor to player position
        terminal.putCharacter(player.getSymbol()); // Puts character on screen
    }

    public void drawAlien(Alien alien) { // Method to draw alien position
        terminal.applyForegroundColor(Terminal.Color.GREEN); // Set so alien don't get same color as player
        terminal.moveCursor(alien.getxPos(), alien.getyPos()); // Move cursor to alien position
        terminal.putCharacter(alien.getSymbol()); // Puts character on screen
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }

    public void drawProjectile(Projectile proj) {
        terminal.moveCursor(proj.getxPos(), proj.getyPos());
        terminal.applyForegroundColor(Terminal.Color.YELLOW);
        terminal.putCharacter(proj.getSymbol());
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }


    public void drawAsteroid(Asteroid asteroid) { // Method to draw player position
        terminal.applyBackgroundColor(150, 150, 150);
        if(asteroid instanceof PowerUp){
            terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
            terminal.applyForegroundColor(Terminal.Color.RED);
        }
        if(asteroid.size == Asteroid.small) {
            drawSmallAsteroid(asteroid);
        } else if(asteroid.size == Asteroid.big) {
            drawBigAsteroid(asteroid);
        }
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }

    private void drawSmallAsteroid(Asteroid asteroid) {
        terminal.moveCursor(asteroid.getxPos(), asteroid.getyPos()); // Move cursor to player position
        terminal.putCharacter(asteroid.getSymbol()); // Puts character on screen
        terminal.moveCursor(asteroid.getxPos() + 1, asteroid.getyPos());
        terminal.putCharacter(asteroid.getSymbol());
    }

    private void drawBigAsteroid(Asteroid asteroid) {
        // Draw andra kvadrant
        terminal.moveCursor(asteroid.getxPos(), asteroid.getyPos()); // Move cursor to player position
        terminal.putCharacter(asteroid.getSymbol()); // Puts character on screen
        terminal.moveCursor(asteroid.getxPos() + 1, asteroid.getyPos());
        terminal.putCharacter(asteroid.getSymbol());

        // Draw första kvadrant
        terminal.moveCursor(asteroid.getxPos() + 2, asteroid.getyPos()); // Move cursor to player position
        terminal.putCharacter(asteroid.getSymbol()); // Puts character on screen
        terminal.moveCursor(asteroid.getxPos() + 3, asteroid.getyPos());
        terminal.putCharacter(asteroid.getSymbol());

        // Draw tredje kvadranten
        terminal.moveCursor(asteroid.getxPos(), asteroid.getyPos() + 1); // Move cursor to player position
        terminal.putCharacter(asteroid.getSymbol()); // Puts character on screen
        terminal.moveCursor(asteroid.getxPos() + 1, asteroid.getyPos() + 1);
        terminal.putCharacter(asteroid.getSymbol());

        // Draw fjärde kvadranten
        terminal.moveCursor(asteroid.getxPos() + 2, asteroid.getyPos() + 1); // Move cursor to player position
        terminal.putCharacter(asteroid.getSymbol()); // Puts character on screen

        terminal.moveCursor(asteroid.getxPos() + 3, asteroid.getyPos() + 1);
        terminal.putCharacter(asteroid.getSymbol());

    }

    public void drawAsteroidExplosion(Asteroid asteroid) {
        terminal.applyBackgroundColor(Terminal.Color.YELLOW);
        terminal.applyForegroundColor(Terminal.Color.RED);

        for(int x = asteroid.getxPos() - 1; x < asteroid.getxPos() + 2; x++) {
            for(int y = asteroid.getyPos() - 1; y < asteroid.getyPos() + 2; y++) {
                terminal.moveCursor(x, y);
                terminal.putCharacter('\u2593');
            }
        }
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
    }


    public void drawAlienExplosion(Alien alien){
        terminal.applyBackgroundColor(0,102,0);
        terminal.applyForegroundColor(0,204,0);

        for (int x = alien.getxPos()-1; x < alien.getxPos()+2 ; x++) {
            for (int y = alien.getyPos()-1; y < alien.getyPos()+2 ; y++) {
                terminal.moveCursor(x,y);
                terminal.putCharacter('\u2593');
            }
        }
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
    }

    public void printGameOver (int points){
        terminal.clearScreen();
        char choice;
        String gameOver = "Game Over!";

        String pointString ="Points: "+points;
        String playAgain = "Press \"Enter\" to play again, ESC to quit";
        terminal.moveCursor(45,10);
        for (int i = 0; i < gameOver.length() ; i++) {

            terminal.putCharacter(gameOver.charAt(i));
        }
        terminal.moveCursor(45, 12);
        for(int i = 0; i < pointString.length(); i++) {
            terminal.putCharacter(pointString.charAt(i));
        }

        terminal.moveCursor(32,14);
        for (int i = 0; i < playAgain.length() ; i++) {
            terminal.putCharacter(playAgain.charAt(i));
        }
    }

    public void startScreen() throws InterruptedException{
        String start = "Flappy Asteroids";
        terminal.moveCursor(42,10);
        for (int i = 0; i < start.length() ; i++) {
            terminal.putCharacter(start.charAt(i));
        }
        terminal.moveCursor(0,12);
        for (int x = 0; x < 100 ; x++) {
            terminal.applyBackgroundColor(Terminal.Color.GREEN);
            terminal.putCharacter(' ');
        }
        terminal.moveCursor(0,14);
        for (int x = 0; x < 100 ; x++) {
            terminal.putCharacter(' ');
        }
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        Thread.sleep(3000);

    }


}//end of class
