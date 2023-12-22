import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoublingCubeTest {

    @Test
    void testInitialValue() {
        DoublingCube doublingCube = new DoublingCube();
        assertEquals(1, doublingCube.getValue(), "Initial value of the doubling cube should be 1");
    }

    @Test
    void testDoubleCube() {
        DoublingCube doublingCube = new DoublingCube();
        int initialValue = doublingCube.getValue();

        doublingCube.doubleCube();

        assertEquals(initialValue * 2, doublingCube.getValue(), "Doubling cube value should be doubled");
    }

    @Test
    void testMaxValue() {
        DoublingCube doublingCube = new DoublingCube();
        // Double the cube until it reaches the maximum value
        for (int i = 1; i <= 6; i++) {
            doublingCube.doubleCube();
        }

        doublingCube.doubleCube(); // Attempting to double beyond the maximum value

        assertEquals(64, doublingCube.getValue(), "Doubling cube should not exceed the maximum value (64)");
    }

    @Test
    void testResetCube() {
        DoublingCube doublingCube = new DoublingCube();
        doublingCube.doubleCube(); // Increase the value
        doublingCube.resetCube();

        assertEquals(1, doublingCube.getValue(), "Doubling cube should be reset to 1");
    }
}
