package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.util.Random;


public class Game {

    // Declare variables
    private Terminal terminal;
    private Player player;
    private List<Alien> aliens;
    private List<Projectile> projectiles;
    private List<Asteroid> asteroids;
    private Key key;
    private Key key2;
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
        PowerUp.resetNumberOfPowerups();

    }

    public boolean run() throws InterruptedException {  // Method to run your game
        terminal.enterPrivateMode();        // Method to create window
        terminal.setCursorVisible(false);   // Makes cursor invisible

        player = new Player(50, 15); // Create new player object
        render.startScreen();

        while (true) {


            //add asteroid
            if(rand.nextInt(1000)<12+loopCounter/1500){ // Create asteroid
                asteroids.add(addRandomAsteroid());
            }

            // introduction of aliens delayed 1500 frames ~ 30s? enters randomly from right or left
            if(loopCounter > 1500){
                if(rand.nextInt(1000)<1){
                    aliens.add(new Alien(0,rand.nextInt(30),player));
                }
                else if(rand.nextInt(1000)<2){
                    aliens.add(new Alien(100,rand.nextInt(30),player));
                }
            }
            //add Powerups
            if(player.getLives()+ PowerUp.getNumberOfPowerups() < 3){
                if(rand.nextInt(1000) < 2 && PowerUp.getNumberOfPowerups() < 3){
                    asteroids.add(new PowerUp(rand.nextInt(100),0,0,0.1));
                    PowerUp.setNumberOfPowerups(1);

                }
            }
            System.out.println(PowerUp.getNumberOfPowerups());

            //cooldown for player gun
            if (coolDown) {
                coolDownCounter++;

                if(coolDownCounter > 7){

                    coolDown = false;
                    coolDownCounter = 0;
                }
            }



            key = terminal.readInput();     // Get key input
            if (key != null) {                // If a key press has happened

                input(key);
            }

            // Update object positions
            player.updatePosition();

            updateAliens();


            if (player.isDead(asteroids, aliens, render)){ // Check if dead before updating projectiles and asteroids

                break;
            }

            updateProjectiles();
            updateAsteriods();

            // Render objects
            render.drawPlayer(player);

            for(Alien alien : aliens) {

                render.drawAlien(alien);
            }

            for (Projectile p : projectiles) {
                render.drawProjectile(p); // Draw projectile
            }

            for (Asteroid a : asteroids) {
                render.drawAsteroid(a);
            }

            Thread.sleep(20); // Pause program for 20ms
            terminal.clearScreen();
            loopCounter++;

        } // end of main loop


        render.printGameOver(points);

        boolean keyInput = false;
        while (!keyInput) {
            // Read input

            Key key2 = terminal.readInput();
            // om key2 är null
            if (key2 != null) {
                switch (key2.getKind()) {
                    case Enter:
                        terminal.exitPrivateMode();
                        return true;

                    case Escape:
                        System.out.println("ESC??");
                        terminal.exitPrivateMode();
                        return false;
                }
            }
        }
        return false;
    }


    private void updateAliens(){
        for(int i = aliens.size() - 1; i >= 0; i--) {
            aliens.get(i).updatePosition();
            if(aliens.get(i).hitByProjectile(projectiles)) {
                render.drawAlienExplosion(aliens.get(i));
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
                if(asteroids.get(i) instanceof PowerUp){
                    PowerUp.setNumberOfPowerups(-1);
                }
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
                }
                break;
            case Escape:
                System.exit(1338);
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

        //Here you can adjust start speed of atsteroids by changing one variable
        double adjustSpeed = 0.8;
        xSpeed *= adjustSpeed;
        ySpeed *= adjustSpeed;

        return new Asteroid(x,y,xSpeed,ySpeed);
    }




}
