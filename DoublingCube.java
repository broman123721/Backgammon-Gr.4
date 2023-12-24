import java.util.Random;

class DoublingCube extends Die {
    private int currentValue;

    public DoublingCube() {
        super(6); // Six-sided doubling cube
        this.currentValue = 1; // Initial value of the doubling cube
    }

    public int getValue() {
        return currentValue;
    } //gets current value of the cube

    public void doubleCube() { //Doubles values on the cube until it reaches it's maximum value.
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
