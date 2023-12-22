import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    @Test
    void testRoll() {
        Die die = new Die(6);
        int result = die.roll();

        assertTrue(result >= 1 && result <= 6, "Roll result should be between 1 and 6");
    }

    @Test
    void testRollDice() {
        int[] diceValues = Die.rollDice();

        assertNotNull(diceValues, "Dice values should not be null");
        assertEquals(2, diceValues.length, "Dice values array should have length 2");
        assertTrue(diceValues[0] >= 1 && diceValues[0] <= 6, "First dice value should be between 1 and 6");
        assertTrue(diceValues[1] >= 1 && diceValues[1] <= 6, "Second dice value should be between 1 and 6");
    }

    @Test
    void testDiceEntry() {
        int die1 = 4;
        int die2 = 2;
        int[] result = Die.diceEntry(die1, die2);

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.length, "Result array should have length 2");
        assertEquals(die1, result[0], "First value should be the same as the manual entry for die1");
        assertEquals(die2, result[1], "Second value should be the same as the manual entry for die2");
    }
}
