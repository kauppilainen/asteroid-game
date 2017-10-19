package main;

public class PowerUpp extends Asteroid {

    private static int numberOfPowerups = 0;

    public PowerUpp(int xPos, int yPos, double xSpeed, double ySpeed) {
        super(xPos, yPos, xSpeed, ySpeed);
        setSymbol('❤');
    }

    public static int getNumberOfPowerups() {
        return numberOfPowerups;
    }

    public static void setNumberOfPowerups(int change) {
        numberOfPowerups += change;
    }
}
