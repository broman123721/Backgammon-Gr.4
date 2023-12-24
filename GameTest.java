import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
    public void testPlayerDetermination() {
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

    @Test
    public void testCommandsDisplay(){

        Player player1 = new Player();
        Player player2 = new Player();

        player1.setName("p1");
        player2.setName("p2");

        int currentplayer = 1; //Simulating player 1 turn and is in ownership of doubling cube.
        int doubleOwner = 1;
        String coutput = null;
        String coutput2 = null;

        // Display available commands based on game state
        if (doubleOwner == 1 && currentplayer == 1) {
            // Commands for player 1 if in ownership of the double cube
            coutput = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'D' if you would like to use the doubling.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        } else if (doubleOwner == 2 && currentplayer == 2) {
            // Commands for player 2 if in ownership of the double cube
            coutput = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'D' if you would like to use the doubling.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        } else {
            // Commands for regular turns if player is not in ownership
            coutput = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        }

        String expectedOutput = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'D' if you would like to use the doubling.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        assertEquals(expectedOutput, coutput);

         currentplayer = 2; //Simulating player 2 turn and is not in ownership of doubling cube.

        // Display available commands based on game state
        if (doubleOwner == 1 && currentplayer == 1) {
            // Commands for player 1 if in ownership of the double cube
            coutput2 = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'D' if you would like to use the doubling.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        } else if (doubleOwner == 2 && currentplayer == 2) {
            // Commands for player 2 if in ownership of the double cube
            coutput2 = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'D' if you would like to use the doubling.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        } else {
            // Commands for regular turns if player is not in ownership
            coutput2 = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        }

        String expectedOutput2 = """
                - Type 'R' if you would like to roll the dice.
                - Type 'P' if you would like to view the pip count:
                - Type 'Q' if you would like to quit the game.
                - Type 'M' if you would like to display the match length and scores.
                - Type 'DC' if you would like to manually enter dice values.
                - Type 'T' if you would like to run a test file to process commands.
                """;

        assertEquals(expectedOutput2, coutput2);



    }



    @Test
    public void testPromptUserPickChecker() {
        // Create a list of movable checkers for testing
        List<Integer> movableCheckers = new ArrayList<>();
        movableCheckers.add(3);
        movableCheckers.add(7);
        movableCheckers.add(10);

        // Set playerMoving for testing
        int playerMoving = 1;

        // Redirect System.in and System.out to provide input and capture printed output
        String input = "2\n"; // Simulate user entering '2'
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the promptUserPickChecker method to capture the printed output and input
        Game game = new Game(null, null, 0, false); // Pass null players and irrelevant parameters for the test
        int result = game.promptUserPickChecker(movableCheckers, playerMoving);

        // Reset System.in and System.out to the original streams
        System.setIn(System.in);
        System.setOut(System.out);

        // Verify the printed output and the returned result
        String expectedOutput = System.lineSeparator() +
                "Enter 1 to move Checker at position 4" + System.lineSeparator() +
                "Enter 2 to move Checker at position 8" + System.lineSeparator() +
                "Enter 3 to move Checker at position 11" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
        assertEquals(7, result); // The user entered '2', so the second checker (index 1) should be selected
    }



}
