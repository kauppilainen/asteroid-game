package main;

import java.util.List;

public class Asteroid extends MovingObject {

    public Asteroid(int xPos, int yPos, double xSpeed, double ySpeed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = xSpeed;

        this.ySpeed = ySpeed;
        setSymbol(' ');
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

    public boolean hitByProjectile(List<Projectile> projectiles){
        for (int i = projectiles.size()-1; i >= 0 ; i--) {
            //test if on same position as projectile
            if(projectiles.get(i).getxPos() == this.getxPos() && projectiles.get(i).getyPos() == this.getyPos()
                    || projectiles.get(i).getxPos() == this.getxPos()+1 && projectiles.get(i).getyPos() == this.getyPos() ){
                projectiles.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void setxPos(int tempxPos){
        this.xPos = tempxPos;
        if(this.xPos < 0){
            this.xPos = 99;
            this.xPosDouble = this.xPos;
        }
        else if(this.xPos > 99) {
            this.xPos = 1;
            this.xPosDouble = this.xPos;
        }
    }

    @Override
    public void setyPos(int newYPos){
        this.yPos = newYPos;
        if(this.yPos < 0){
            this.yPos = 30;
            this.yPosDouble = this.yPos;
        }
        else if(this.yPos > 30){
            this.yPos = 0;
            this.yPosDouble = this.yPos;
        }
    }

}//end of class
