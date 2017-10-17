package main;

public class PlayerObject {

    private int xPos;
    private int yPos;
    private double xSpeed;
    private double ySpeed;
    private char symbol = '<';

    public PlayerObject(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }


    //region getters & setters

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

    //endregion


}//end of class
