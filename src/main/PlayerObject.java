package main;


import java.util.List;

public class PlayerObject extends MovingObject {
    // Declare variables
    private int direction; //direction 0-3, 0 = upp
    private int lives;

    //Upp: '\u25B2'
    //Ner: '\u25BC'
    //Vänster: '\u25C0'
    //Höger: '\u25B6'


    public PlayerObject(int xPos, int yPos) {
        // Initialize our variables
        this.xPos = xPos; // Set instance variable xPos to xPos from in parameter
        this.yPos = yPos; // Set instance variable yPos to yPos from in parameter
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.direction = 0;
        setSymbol(direction);
        this.lives = 3;
    }

    public boolean isDead(List<Asteroid> asteroids, Render render)throws InterruptedException{
        for (int i = asteroids.size()-1; i >=0 ; i--) {
            Asteroid asteroid = asteroids.get(i);
            if (asteroid.getxPos() == this.xPos && asteroid.getyPos()==this.yPos ||asteroid.getxPos()+1 == this.xPos && asteroid.getyPos() == this.yPos){
                lives--;
                System.out.println(lives);
                render.drawAstroidExplosion(asteroids.get(i));
                asteroids.remove(i);

            }

        }

        if(lives <= 0){
            return true;
        }
        return false;
    }

    public void moveForward() { // OBS! JAG HAR FUCKAT NÅTT MED ACCELERATIONEN

        if(direction == 0) {
            setySpeed(-0.02);
        } else if(direction == 1) {
            setxSpeed(0.02);
        } else if(direction == 2) {
            setySpeed(0.02);
        } else if(direction == 3) {
            setxSpeed(-0.2);
        }
    }

    public void brake() {

        if(direction == 0) {
            setySpeed(0.01);
        } else if(direction == 1) {
            setxSpeed(-0.01);
        } else if(direction == 2) {
            setySpeed(-0.01);
        } else if(direction == 3) {
            setxSpeed(0.01);
        }
    }

    public void updatePosition() {

        xPosDouble += xSpeed;
        yPosDouble += ySpeed;
        int tempxPos = (int) (xPosDouble);
        int tempyPos = (int) (yPosDouble);
        setxPos(tempxPos);
        setyPos(tempyPos);
        //System.out.println(xPos + " " + yPos + " speed X:" + xSpeed + " Y: " + ySpeed);
    }

    public void setSymbol(int direction) {

        if(direction == 0) {
            this.symbol = '\u25B2';
        } else if(direction == 1) {
            this.symbol = '\u25B6';
        } else if(direction == 2) {
            this.symbol = '\u25BC';
        } else if(direction == 3) {
            this.symbol = '\u25C0';
        }
    }

    public int getDirection() {
        return direction;
    }

    public int getLives() {
        return lives;
    }

    public void setDirection(int turn) {

        int temp = this.direction += turn;
        //fulhack to solve negative direction
        if(temp == -1) {
            temp = 3;
        }
        this.direction = temp % 4;
        setSymbol(this.direction);



    }

    @Override
    public void setxPos(int newXPos){
        this.xPos = newXPos;
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
