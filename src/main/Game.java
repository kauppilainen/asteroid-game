package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Game {


    // Declare variables
    private Terminal terminal;
    private PlayerObject player;
    private List<Projectile> projectiles;
    private Key key;
    private Render render;


    public Game(){ // Constructor
        // Initialize our variables
        this.terminal = TerminalFacade.createTerminal(System.in,
                                                      System.out,
                                                      Charset.forName("UTF8"));
        render = new Render(terminal); // Create new Render object with terminal as parameter

        projectiles = new ArrayList<>();


    }

    public void run() throws InterruptedException{  // Method to run your game
        this.player = new PlayerObject(50,15); // Create new player object
        terminal.enterPrivateMode();        // Method to create window
        terminal.setCursorVisible(false);   // Makes cursor invisible
        Asteroid asteroid = new Asteroid(10,1,0.05,0.1);

        while (true){

            key = terminal.readInput();     // Get key input from terminal
            if(key != null){                // If a key press has happened
                input(key);                 // Send key to input where the input is dealt with
            }

            player.update();
            asteroid.update();

            render.drawAsteroid(asteroid);
            render.drawPlayer(player); // Send player info to the render method drawPlayer to be drawn
            int projectileSize = projectiles.size();
            for(int i = projectileSize-1; i >= 0; i--) {
                int x = projectiles.get(i).getxPos();
                int y = projectiles.get(i).getyPos();
                projectiles.get(i).setPosition(x, y);

                // Remove projectile if hits edge of screen
                if(x < 0 || x > 100 || y < 0 || y > 30){
                    projectiles.remove(i);
                    break;
                }

                render.drawProjectile(projectiles.get(i)); // Draw projectile
            }


            Thread.sleep(20); // Pause program for 20ms
            terminal.clearScreen();
        }
    }

    public void input(Key key){
        switch (key.getKind()){ // If key input was one of our expected cases, do the case instruction
            case ArrowUp:
                player.moveForward();
                break;
            case ArrowDown:
                player.brake();
                break;
            case ArrowLeft:
                player.setDirection(-1);//turn left
                break;
            case ArrowRight:
                player.setDirection(1);//turn right
                break;
            case Tab:
                System.out.println("Tab");
                projectiles.add(new Projectile(player)); // Create and add projectile to projectile list
                break;
        }
    }

}
