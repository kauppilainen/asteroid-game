package main;

public class Projectile {
    private PlayerObject player;
    private int playerXPos;
    private int playerYPos;

    private int xPos;
    private int yPos;
    private double xSpeed;
    private double ySpeed;
    private char symbol;
    private int direction = 0; //direction 0-3, 0 = upp

    public Projectile(PlayerObject player){
        this.player = player;               // Set player object to our player variable
        this.playerXPos = player.getxPos(); // Get player position
        this.playerYPos = player.getyPos();
        direction = player.getDirection();  // Get player direction
        setSymbol(direction);

        // Set projectile starting position
        setPosition(playerXPos, playerYPos);
    }

    //region Getters and setters

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

        if(direction == 0 || direction == 2){
            this.symbol = '|';
        }
        else if(direction == 1 || direction == 3){
            this.symbol = '-';
        }
    }

    //endregion

    public void setPosition(int xPos, int yPos){
        switch(direction){ // Place projectile position in front of player depending on its direction
            case 0:
                setxPos(xPos);
                setyPos(yPos - 1);
                break;
            case 1:
                setxPos(xPos + 1);
                setyPos(yPos);
                break;
            case 2:
                setxPos(xPos);
                setyPos(yPos + 1);
                break;
            case 3:
                setxPos(xPos - 1);
                setyPos(yPos);
                break;
        }
    }

    private void updatePosition(int xPos, int yPos){
        setPosition(xPos, yPos);
    }
}
