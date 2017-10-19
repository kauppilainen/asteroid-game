package main;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        boolean playAgain = true;           // Call run method from your game object

        do {
            Game game = new Game();
            if(game.run() == false){
                System.exit(0);
            }
        } while (playAgain);
    }
}