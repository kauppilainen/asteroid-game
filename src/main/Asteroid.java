package main;

public class Asteroid {

    private int xPos;
    private int yPos;
    private double xPosDouble;
    private double yPosDouble;
    private double xSpeed;
    private double ySpeed;
    private char symbol;

    public Asteroid(int xPos, int yPos, double xSpeed, double ySpeed){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;z
        this.symbol =' ';
    }

    public void update(){
        xPosDouble += xSpeed;
        yPosDouble += ySpeed;
        int tempxPos =(int)(xPosDouble);
        int tempyPos = (int)(yPosDouble);
        setxPos(tempxPos);
        setyPos(tempyPos);
    }

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

    public double getxPosDouble() {
        return xPosDouble;
    }

    public void setxPosDouble(double xPosDouble) {
        this.xPosDouble = xPosDouble;
    }

    public double getyPosDouble() {
        return yPosDouble;
    }

    public void setyPosDouble(double yPosDouble) {
        this.yPosDouble = yPosDouble;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}//end of klass
