package main;

public abstract class MovingObject {
    protected int xPos;
    protected int yPos;
    protected double xPosDouble;
    protected double yPosDouble;
    protected double xSpeed;
    protected double ySpeed;
    protected char symbol;

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
        if(this.xSpeed > 0.3){
            this.xSpeed = 0.3;
        }
        else if(this.xSpeed < -0.3){
            this.xSpeed = -0.3;
        }
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeedChange) {
        this.ySpeed += ySpeedChange;
        if(this.ySpeed > 0.3){
            this.ySpeed = 0.3;
        }
        else if(this.ySpeed < -0.3){
            this.ySpeed = -0.3;
        }
    }

    public char getSymbol() {
        return symbol;
    }

}
