package main;

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
}
