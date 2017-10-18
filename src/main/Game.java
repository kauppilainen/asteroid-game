package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.Key;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;


public class Game {


    // Declare variables
    private Terminal terminal;
    private PlayerObject player;
    private List<Projectile> projectiles;
    private List<Asteroid> asteroids;
    private Key key;
    private Render render;
    private int points;


    public Game() { // Constructor
        // Initialize our variables
        this.terminal = TerminalFacade.createTerminal(System.in,
                System.out,
                Charset.forName("UTF8"));
        render = new Render(terminal); // Create new Render object with terminal as parameter

        projectiles = new ArrayList<>();
        asteroids = new ArrayList<>();
        points = 0;


    }

    public void run() throws InterruptedException {  // Method to run your game
        this.player = new PlayerObject(50, 15); // Create new player object
        terminal.enterPrivateMode();        // Method to create window
        terminal.setCursorVisible(false);   // Makes cursor invisible
        asteroids.add(new Asteroid(20,1,0.05,0.1));
        asteroids.add(new Asteroid(60,1,0.01,0.1));
        asteroids.add(new Asteroid(40,30,-0.02,0.2));
        asteroids.add(new Asteroid(80,30,-0.01,0.6));
        asteroids.add(new Asteroid(0,10,0.001,0.1));
        asteroids.add(new Asteroid(30,30,-0.03,-0.3));
        asteroids.add(new Asteroid(60,20,0,0));
        asteroids.add(new Asteroid(50,12,0,0));


        while (true) {

            key = terminal.readInput();     // Get key input from terminal
            if(key != null) {                // If a key press has happened
                input(key);                 // Send key to input where the input is dealt with
            }

            player.updatePosition();

            if (player.isDead(asteroids)){
                break;
            }

            for (int i = asteroids.size()-1; i >= 0 ; i--) {
                asteroids.get(i).updatePosition();
                if(asteroids.get(i).hitByProjectile(projectiles)){
                    render.drawAstroidExplosion(asteroids.get(i));
                    asteroids.remove(i);
                    points++;


                }
            }

            for (Asteroid a:asteroids){
                render.drawAsteroid(a);
            }

            render.drawPlayer(player); // Send player info to the render method drawPlayer to be drawn

            int projectileSize = projectiles.size();
            for(int i = projectileSize - 1; i >= 0; i--) {
                int x = projectiles.get(i).getxPos();
                int y = projectiles.get(i).getyPos();
                projectiles.get(i).updatePosition();

                // Remove projectile if hits edge of screen
                if(x < 0 || x > 100 || y < 0 || y > 30) {
                    projectiles.remove(i);
                    break;
                }

                render.drawProjectile(projectiles.get(i)); // Draw projectile
            }


            Thread.sleep(20); // Pause program for 20ms
            terminal.clearScreen();
        }//End of loop

        System.out.println("Utanför loop!!");
        render.printGameOver(points);
    }

    public void input(Key key) {
        switch (key.getKind()) { // If key input was one of our expected cases, do the case instruction
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
