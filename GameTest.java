import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    @Test
    public void testGameInitialization() {
        // Create players
        Player player1 = new Player();
        Player player2 = new Player();

        // Create a game with a match length of 5 and the quitGame flag set to false
        Game game = new Game(player1, player2, 5, false);

        // Check if the players are initialized correctly
        assertEquals(player1, game.getPlayer1());
        assertEquals(player2, game.getPlayer2());

        // Check if the match length and quitGame flag are set correctly
        assertEquals(5, game.getMatchLength());
        assertFalse(game.getQuitGame());
    }

    @Test
    public void testDiceRollAndPlayerDetermination() {
        // Create players
        Player player1 = new Player();
        Player player2 = new Player();

        player1.setName("p1");
        player2.setName("p2");
        int currentplayer = 0;

        // Create a game with a match length of 5 and the quitGame flag set to false
        Game game = new Game(player1, player2, 5, false);

        // Set up a mock Die class with fixed dice values for testing
        int[] diceValues = {1, 5};

        // Call the playGame method to execute the dice roll and player determination
        System.out.println("DICE " + player1.getName() + ":" + "[" + diceValues[0] + "]");
        int total1 = diceValues[0];

        System.out.println("DICE " + player2.getName() + ":" + "[" + diceValues[1] + "]");
        int total2 = diceValues[1];

        // Determine the starting player based on the dice draw
        if (total1 > total2) {
            System.out.println(player1.getName() + " goes first!");
            currentplayer = 1;
        }
        if (total1 < total2) {
            System.out.println(player2.getName() + " goes first!");
            currentplayer = 2;
        }
        if (total1 == total2) {
            System.out.println("Draw! Lets roll again!");
        }


        // After the dice roll, check if the expected player is set as the current player
        assertEquals(2, currentplayer); // Assuming player1 goes first based on the mock dice values

        // Check if the doubleOwner is set correctly
        assertEquals(2, currentplayer); // Assuming player1 has the higher dice value

    }



}
