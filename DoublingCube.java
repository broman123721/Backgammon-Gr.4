import java.util.Random;

class DoublingCube extends Die {
    private int currentValue;

    public DoublingCube() {
        super(6); // Six-sided doubling cube may not need this can remove in testing.
        this.currentValue = 1; // Initial value of the doubling cube
    }

    public int getValue() {
        return currentValue;
    }

    public void doubleCube() {
        if (currentValue < 64) {
            currentValue *= 2;
            System.out.println("Doubling cube is now at " + currentValue);
        } else {
            System.out.println("Doubling cube is already at the maximum value (64).");
        }
    }
    // Reset the doubling cube to its initial value
    public void resetCube() {
        currentValue = 1;
        System.out.println("Doubling cube has been reset to 1");
    }
}
