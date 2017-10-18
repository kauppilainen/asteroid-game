package main;

import java.util.List;

public abstract class EnemyObject extends MovingObject{

    //Här deklarerar vi de variabler som vi kommer att ha i Alienobjekt.
    PlayerObject player;
    int playerXPos;
    int playerYPos;

    int lives = 1;

    //Skapa en konstruktor för EnemyObject
    public EnemyObject(PlayerObject player) {
        this.player = player;
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

    public boolean isDead(List<Projectile> proj,
                          Render render) throws InterruptedException {

        for(int i = proj.size() - 1; i >= 0; i--) { // If asteroid has hit player
            Projectile projectile = proj.get(i);
            if(projectile.getxPos() == this.xPos && projectile.getyPos() == this.yPos ||
               projectile.getxPos() + 1 == this.xPos && projectile.getyPos() == this.yPos) { // If same position
                //render.drawAsteroidExplosion(asteroids.get(i));
                proj.remove(i);
                lives--;
            }
        }

        if(lives <= 0){
            return true;
        }
        return false;
    }
}
