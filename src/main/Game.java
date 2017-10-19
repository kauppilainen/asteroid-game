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
    private List<AlienObject> aliens;
    private List<Projectile> projectiles;
    private List<Asteroid> asteroids;
    private Key key;
    private Render render;
    private int points;
    private Random rand;
    private long loopCounter;
    private boolean coolDown; //stop player from firing to often
    private long coolDownCounter;


    public Game() { // Constructor
        // Initialize our variables
        this.terminal = TerminalFacade.createTerminal(System.in,
                System.out,
                Charset.forName("UTF8"));
        render = new Render(terminal); // Create new Render object with terminal as parameter

        aliens = new ArrayList<>();
        projectiles = new ArrayList<>();
        asteroids = new ArrayList<>();
        points = 0;
        rand = new Random();
        loopCounter = 0;
        coolDown = false;
    }

    public void run() throws InterruptedException {  // Method to run your game
        terminal.enterPrivateMode();        // Method to create window
        terminal.setCursorVisible(false);   // Makes cursor invisible

        player = new PlayerObject(50, 15); // Create new player object
        aliens.add(new AlienObject(10, 10, player));  // Create new alien object in alien array

        while (true) {

            //add enemies
            if(rand.nextInt(1000)<12+loopCounter/1500){ // Create asteroid
                asteroids.add(addRandomAsteroid());
            }
            //introduction of aliens delayed 1500 frames ~ 30s? enters randomly from right or left
            if(loopCounter > 1500){
                if(rand.nextInt(1000)<1){
                    aliens.add(new AlienObject(0,rand.nextInt(30),player));
                }
                else if(rand.nextInt(1000)<2){
                    aliens.add(new AlienObject(100,rand.nextInt(30),player));
                }
            }

            //cooldown for player gun
            if(coolDown){
                coolDownCounter++;
                if(coolDownCounter > 8){
                    coolDown = false;
                    coolDownCounter = 0;
//                    System.out.println(coolDownCounter);
//                    System.out.println(coolDown);
                }
            }



            key = terminal.readInput();     // Get key input
            if(key != null) {                // If a key press has happened
                input(key);
            }

            // Update object positions
            player.updatePosition();

//            for(int i = 0; i < aliens.size(); i++) {
//                aliens.get(i).updatePosition();
//            }
            updateAliens();


             //alien.shootLazer(projectiles);                VART OCH NÄR SKA DENNA AVFYRAS?

            if (player.isDead(asteroids, aliens, render)){ // Check if dead before updating projectiles and asteroids
                break;
            }

//            for(int i = aliens.size(); i >= 0; i--) {
//                if(aliens.get(i).isDead(projectiles, render)){
//                    points++;
//                }
//            }

            updateProjectiles();
            updateAsteriods();

            // Render objects
            render.drawPlayer(player);
            for(AlienObject alien : aliens) {
                render.drawAlien(alien);
            }

            for(Projectile p: projectiles){
                render.drawProjectile(p); // Draw projectile
            }

            for (Asteroid a:asteroids){
                render.drawAsteroid(a);
            }

            Thread.sleep(20); // Pause program for 20ms
            terminal.clearScreen();
            loopCounter++;

        } // end of main loop

        System.out.println("Utanför loop!!");
        render.printGameOver(points);
    }

    private void updateAliens(){
        for(int i = aliens.size() - 1; i >= 0; i--) {
            aliens.get(i).updatePosition();
            if(aliens.get(i).hitByProjectile(projectiles)) {
                aliens.remove(i);
                points++;
            }
        }
    }

    private void updateAsteriods() {
        for (int i = asteroids.size()-1; i >= 0 ; i--) { // Update asteroids
            asteroids.get(i).updatePosition();
            if(asteroids.get(i).hitByProjectile(projectiles)){
                render.drawAsteroidExplosion(asteroids.get(i));
                asteroids.remove(i);
                points++;
            }
        }
    }

    private void updateProjectiles() {
        for(int i = projectiles.size() - 1; i >= 0; i--) { // Update projectiles
            int x = projectiles.get(i).getxPos();
            int y = projectiles.get(i).getyPos();
            projectiles.get(i).updatePosition();

            // Remove projectile if hits edge of screen
            if(x < 0 || x > 100 || y < 0 || y > 30) {
                projectiles.remove(i);
                break;
            }
        }
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
                //System.out.println("Tab");
            // player.shootLazer(projectiles);// Create and add projectile to projectile list
                if (!coolDown){
                    projectiles.add(new Projectile(player)); // Create and add projectile to projectile list
                    coolDown = true;
                    System.out.println(coolDown);
                }
            break;
        }
    }

    private Asteroid addRandomAsteroid(){
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
        double adjustSpeed = 0.8;
        xSpeed *= adjustSpeed;
        ySpeed *= adjustSpeed;


        return new Asteroid(x,y,xSpeed,ySpeed);

    }

}
