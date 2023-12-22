import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testPlayerInitialization() {
        Player player = new Player();

        assertNull(player.getName(), "Player name should be initialized as null");
        assertEquals(0, player.getScore(), "Player score should be initialized as 0");
    }

    @Test
    void testSetterMethods() {
        Player player = new Player();

        player.setName("Player1");
        player.setScore(10);

        assertEquals("Player1", player.getName(), "Player name should be set");
        assertEquals(10, player.getScore(), "Player score should be set");
    }

    @Test
    void testScoreModification() {
        Player player = new Player();

        player.setScore(5);
        assertEquals(5, player.getScore(), "Player score should be set");

        player.setScore(15);
        assertEquals(15, player.getScore(), "Player score should be updated");
    }
}
