package main;

public class PowerUp extends Asteroid {

    private static int numberOfPowerups = 0;

    public PowerUp(int xPos, int yPos, double xSpeed, double ySpeed) {
        super(xPos, yPos, xSpeed, ySpeed);
        setSymbol('❤');
        setBigOrSmall();
    }

    @Override
    protected void setBigOrSmall() {
        size = small;
    }

    public static int getNumberOfPowerups() {
        return numberOfPowerups;
    }

    public static void setNumberOfPowerups(int change) {
        numberOfPowerups += change;
    }
}
