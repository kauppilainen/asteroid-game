package main;

public class PlayerObject {
    // Declare variables
    private int xPos;
    private int yPos;
    private double xSpeed;
    private double ySpeed;
    private char symbol = '\u25B2';


    //Upp: '\u25B2'
    //Ner: '\u25BC'
    //Vänster: '\u25C0'
    //Höger: '\u25B6'


    public PlayerObject(int xPos, int yPos){ // Constructor
        // Initialize our variables
        this.xPos = xPos; // Set instance variable xPos to xPos from in parameter
        this.yPos = yPos; // Set instance variable yPos to yPos from in parameter
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
