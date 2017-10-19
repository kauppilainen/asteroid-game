package main;

public abstract class MovingObject {
    protected int xPos;
    protected int yPos;
    protected double xPosDouble;
    protected double yPosDouble;
    protected double xSpeed;
    protected double ySpeed;
    protected char symbol;
    protected int direction; //direction 0-3, 0 = upp
    protected double maxSpeed;
    protected double minSpeed;

    abstract void updatePosition();

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeedChange) {
        this.xSpeed += xSpeedChange;
        if(this.xSpeed > maxSpeed){
            this.xSpeed = maxSpeed;
        }
        else if(this.xSpeed < minSpeed){
            this.xSpeed = minSpeed;
        }
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeedChange) {
        this.ySpeed += ySpeedChange;
        if(this.ySpeed > maxSpeed){
            this.ySpeed = maxSpeed;
        }
        else if(this.ySpeed < minSpeed){
            this.ySpeed = minSpeed;
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public int getDirection() {
        return direction;
    }
}
