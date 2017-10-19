package main;

import java.util.List;

public class Alien extends Enemy {

    public Alien(int xPos, int yPos, Player player) {
        // Take care of parent constructor
        super(player);

        // Initialize our variables
        this.xPos = xPos; // Set instance variable xPos to xPos from in parameter
        this.yPos = yPos; // Set instance variable yPos to yPos from in parameter
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = 0;
        this.ySpeed = 0;
        setSymbol('\u2B24');
        maxSpeed = 0.2;
        minSpeed = -0.2;
    }

    @Override
    void updatePosition() {
        searchForPlayer(player);
        updateSpeed();
        xPosDouble += xSpeed;
        yPosDouble += ySpeed;
        int tempxPos = (int) (xPosDouble);
        int tempyPos = (int) (yPosDouble);
        setxPos(tempxPos);
        setyPos(tempyPos);
    }

    private void updateSpeed(){
        if (playerXPos > xPos) {
            setxSpeed(0.01);
        } else if (xPos > playerXPos) {
            setxSpeed(-0.01);
        }

        if (playerYPos < yPos) {
            setySpeed(-0.01);
        } else if (playerYPos > yPos) {
            setySpeed(0.01);
        }
    }

    public boolean hitByProjectile(List<Projectile> projectiles){
        for (int i = projectiles.size()-1; i >= 0 ; i--) {
            //test if on same position as projectile
            Projectile proj = projectiles.get(i);
            if(proj.getxPos() == this.getxPos() && proj.getyPos() == this.getyPos()){
                projectiles.remove(i);
                return true;
            }
        }
        return false;
    }



    //Vi behöver en metod som skjuter mot playerobjekt.
    void shootLazer(List<Projectile> projectiles) {

        projectiles.add(new Projectile(this)); // Create and add projectile to projectile list
    }

    private void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
