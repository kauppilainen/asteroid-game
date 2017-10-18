package main;

public abstract class EnemyObject extends MovingObject{

    //Här deklarerar vi de variabler som vi kommer att ha i Alienobjekt.
    int playerXPos;
    int playerYPos;

    //Skapa en konstruktor för EnemyObject
    public EnemyObject() {

    }

    //Vi behöver en metod som söker upp Playerobjekt.
    public void searchForPlayer(PlayerObject player) {
        playerXPos = player.getxPos();
        playerYPos = player.getyPos();
    }

    public void updateDirection(PlayerObject player){
        int diffX, diffY;
        //diffX = playerXPos > xPos;
        if (playerXPos > xPos) {
            direction = 1;
        } else if (xPos > playerXPos) {
            direction = 3;
        }

        if (playerYPos < yPos) {
            direction = 0;
        } else if (playerYPos > yPos) {
            direction = 2;
        }
    }
}
