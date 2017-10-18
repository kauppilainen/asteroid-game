package main;

public class Projectile extends MovingObject {
    private PlayerObject player;
    private int playerXPos;
    private int playerYPos;
    private int direction = 0; //direction 0-3, 0 = upp

    public Projectile(PlayerObject player) {
        this.player = player;               // Set player object to our player variable
        this.xPos = player.getxPos(); // Get player position
        this.yPos = player.getyPos();
        direction = player.getDirection();  // Get player direction
        setSymbol(direction); // Set symbol depending on direction
        updatePosition();   // Set projectile starting position
    }

    public void setSymbol(int direction) {

        if(direction == 0 || direction == 2) {
            this.symbol = '|';
        } else if(direction == 1 || direction == 3) {
            this.symbol = '-';
        }
    }

    public void updatePosition() {
        switch (direction) { // Place projectile position in front of player depending on its direction
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
}
