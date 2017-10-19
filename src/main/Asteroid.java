package main;

import java.util.List;
import java.util.Random;

public class Asteroid extends MovingObject {
    static final int small = 0;
    static final int big = 1;
    int size;

    public Asteroid(int xPos, int yPos, double xSpeed, double ySpeed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        setSymbol(' ');
        setBigOrSmall();

        maxSpeed = 0.3;
        minSpeed = -0.3;


    }

    private void setBigOrSmall() {
        double rand = Math.random();
        if(rand <= 0.5) {
            size = small;
        } else {
            size = big;
        }
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void updatePosition() {
        xPosDouble += xSpeed;
        yPosDouble += ySpeed;
        int tempxPos = (int) (xPosDouble);
        int tempyPos = (int) (yPosDouble);
        setxPos(tempxPos);
        setyPos(tempyPos);
    }

    public boolean hitByProjectile(List<Projectile> projectiles) {
        for(int i = projectiles.size() - 1; i >= 0; i--) {
            int projectileX = projectiles.get(i).getxPos();
            int projectileY = projectiles.get(i).getyPos();

            // Different hitboxes depending on size
            if(this.size == Asteroid.small) {
                if(projectileX == this.getxPos()     && projectileY == this.getyPos() ||
                   projectileX == this.getxPos() + 1 && projectileY == this.getyPos()) {
                    projectiles.remove(i);
                    return true;
                }
            }
            else if(this.size == Asteroid.big) {
                if(projectileX == this.getxPos()     && projectileY == this.getyPos() ||
                   projectileX == this.getxPos() + 1 && projectileY == this.getyPos() ||
                   projectileX == this.getxPos() + 2 && projectileY == this.getyPos() ||
                   projectileX == this.getxPos() + 3 && projectileY == this.getyPos() ||
                   projectileX == this.getxPos()     && projectileY == this.getyPos() + 1 ||
                   projectileX == this.getxPos() + 1 && projectileY == this.getyPos() + 1 ||
                   projectileX == this.getxPos() + 2 && projectileY == this.getyPos() + 1 ||
                   projectileX == this.getxPos() + 3 && projectileY == this.getyPos() + 1 ) {
                    projectiles.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void setxPos(int tempxPos) {
        this.xPos = tempxPos;
        if(this.xPos < 0) {
            this.xPos = 99;
            this.xPosDouble = this.xPos;
        } else if(this.xPos > 99) {
            this.xPos = 1;
            this.xPosDouble = this.xPos;
        }
    }

    @Override
    public void setyPos(int newYPos) {
        this.yPos = newYPos;
        if(this.yPos < 0) {
            this.yPos = 30;
            this.yPosDouble = this.yPos;
        } else if(this.yPos > 30) {
            this.yPos = 0;
            this.yPosDouble = this.yPos;
        }
    }

}//end of class
