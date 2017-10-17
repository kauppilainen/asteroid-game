package main;

public class PlayerObject {
    // Declare variables
    private int xPos;
    private int yPos;
    private double xSpeed;
    private double ySpeed;
    private char symbol = '\u25B2';
    //direction 0-3, 0 = upp
    private int direction;


    //Upp: '\u25B2'
    //Ner: '\u25BC'
    //Vänster: '\u25C0'
    //Höger: '\u25B6'


    public PlayerObject(int xPos, int yPos){ // Constructor
        // Initialize our variables
        this.xPos = xPos; // Set instance variable xPos to xPos from in parameter
        this.yPos = yPos; // Set instance variable yPos to yPos from in parameter
    }

    public void moveForward(){

        if (direction == 0){
            yPos--;
        }
        else if (direction == 1){
            xPos +=2;
        }
        else if (direction == 2){
            yPos++;
        }
        else if (direction == 3){
            xPos -=2;
        }
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

    public void setSymbol(int direction) {

        if(direction == 0){
            this.symbol = '\u25B2';
        }
        else if(direction == 1){
            this.symbol = '\u25B6';
        }
        else if(direction == 2){
            this.symbol = '\u25BC';
        }
        else if(direction == 3){
            this.symbol = '\u25C0';
        }



    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int turn) {

        int temp = this.direction+=turn;
        //fulhack to solve negative direction
        if(temp == -1){
            temp = 3;
        }
        this.direction = temp%4;
        setSymbol(this.direction);


    }

    //endregion




}//end of class
