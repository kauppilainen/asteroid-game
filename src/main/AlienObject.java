package main;

import java.util.List;
import java.util.Random;

public class AlienObject extends EnemyObject {

    public AlienObject(int xPos, int yPos) {
        // Initialize our variables
        this.xPos = xPos; // Set instance variable xPos to xPos from in parameter
        this.yPos = yPos; // Set instance variable yPos to yPos from in parameter
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = 0;
        this.ySpeed = 0;
        setSymbol('O');
        maxSpeed = 0.1;
        minSpeed = -0.1;
    }

    @Override
    void updatePosition() {
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

    //Vi behöver en metod som skjuter mot playerobjekt.
    void shootLazer(List<Projectile> projectiles) {
        projectiles.add(new Projectile((MovingObject) this)); // Create and add projectile to projectile list
    }

    private void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
