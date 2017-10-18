package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.Key;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.util.Random;


public class Game {


    // Declare variables
    private Terminal terminal;
    private PlayerObject player;
    private List<Projectile> projectiles;
    private List<Asteroid> asteroids;
    private Key key;
    private Render render;
    private int points;
    private Random rand;
    private long loopCounter;


    public Game() { // Constructor
        // Initialize our variables
        this.terminal = TerminalFacade.createTerminal(System.in,
                System.out,
                Charset.forName("UTF8"));
        render = new Render(terminal); // Create new Render object with terminal as parameter

        projectiles = new ArrayList<>();
        asteroids = new ArrayList<>();
        points = 0;
        rand = new Random();
        loopCounter = 0;


    }

    public void run() throws InterruptedException {  // Method to run your game
        this.player = new PlayerObject(50, 15); // Create new player object
        terminal.enterPrivateMode();        // Method to create window
        terminal.setCursorVisible(false);   // Makes cursor invisible

        while (true) {


                if(rand.nextInt(1000)<12+loopCounter/1500){
                    asteroids.add(addRandomAstroid());
                }




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
            loopCounter++;
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

    private Asteroid addRandomAstroid(){
        int x=0;
        int y=0;
        double xSpeed = -0.2;
        double ySpeed = -0.2;

        if (rand.nextInt(100)<50){
            x = 0;
            y = rand.nextInt(30);
        }
        else{
            y = 0;
            x = rand.nextInt(100);
        }
        xSpeed += rand.nextInt(5)/10.0;
        ySpeed += rand.nextInt(5)/10.0;

        //Here you can adjust start speed of atroids by changing one variable
        double adjustSpeed = 0.65;
        xSpeed *= adjustSpeed;
        ySpeed *= adjustSpeed;


        return new Asteroid(x,y,xSpeed,ySpeed);

    }

}
