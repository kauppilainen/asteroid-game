package main;

public class PlayerObject {
    // Declare variables
    private int xPos;
    private int yPos;
    private double xPosDouble;
    private double yPosDouble;
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
        this.xPosDouble = xPos;
        this.yPosDouble = yPos;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.direction = 0;
    }

    public void moveForward(){

        if (direction == 0){
            setySpeed(-0.01);
        }
        else if (direction == 1){
            setxSpeed(0.01);
        }
        else if (direction == 2){
            setySpeed(0.01);
        }
        else if (direction == 3){
            setxSpeed(-0.01);
        }
    }

    public void brake(){

        if (direction == 0){
            setySpeed(0.005);
        }
        else if (direction == 1){
            setxSpeed(-0.005);
        }
        else if (direction == 2){
            setySpeed(-0.005);
        }
        else if (direction == 3){
            setxSpeed(0.005);
        }

    }

    public void update(){
        xPosDouble += xSpeed;
        yPosDouble += ySpeed;
        int tempxPos =(int)(xPosDouble);
        int tempyPos = (int)(yPosDouble);
        setxPos(tempxPos);
        setyPos(tempyPos);
        System.out.println(xPos+" "+yPos+" speed X:"+xSpeed+" Y: "+ySpeed);

    }

    //region getters & setters

    public int getxPos() {
        return xPos;
    }


    public void setxPos(int newXPos) {
        this.xPos = newXPos;
        if(this.xPos < 0){
            this.xPos = 99;
            this.xPosDouble = 99;
        }else if(this.xPos>99){
            this.xPos = 1;
            this.xPosDouble = this.xPos;
        }
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int newYPos) {
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
