import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(8); // Assuming 8 as stake for testing
        board.createBoard();
    }

    @Test
    void testPipCountForPlayer1() {
        assertEquals(167, board.getPipCount(1), "Pip count should be correct for Player 1");
    }

    @Test
    void testPipCountForPlayer2() {
        assertEquals(167, board.getPipCount(2), "Pip count should be correct for Player 2");
    }

    @Test
    void testBorneOffCountForPlayer1() {
        assertEquals(0, board.getBorneOffp1(), "Borne off count should be initialized to 0 for Player 1");
    }

    @Test
    void testBorneOffCountForPlayer2() {
        assertEquals(0, board.getBorneOffp2(), "Borne off count should be initialized to 0 for Player 2");
    }


    @Test
    void testPrintBoardForPlayer1() {
        // Assuming that the printBoard method doesn't throw any exceptions
        assertDoesNotThrow(() -> board.printBoard(1));
    }

    @Test
    void testPrintBoardForPlayer2() {
        // Assuming that the printBoard method doesn't throw any exceptions
        assertDoesNotThrow(() -> board.printBoard(2));
    }

    @Test
    void testIsSpaceAvailableForPlayer1() {
        assertTrue(board.isSpaceAvailable(1, 10), "Space should be available for Player 1 at index 10");
        assertFalse(board.isSpaceAvailable(1, 18), "Space should not be available for Player 1 at index 18");
    }

    @Test
    void testIsSpaceAvailableForPlayer2() {
        assertTrue(board.isSpaceAvailable(2, 24 - 15), "Space should be available for Player 2 at index 15");
        assertFalse(board.isSpaceAvailable(2, 24 - 6), "Space should not be available for Player 2 at index 6");
    }

    @Test
    void testFindFreeCheckers() {
        // Assuming that the findFreeCheckers method doesn't throw any exceptions
        assertDoesNotThrow(() -> board.findFreeCheckers(1, new int[]{1, 2}));
        assertDoesNotThrow(() -> board.findFreeCheckers(2, new int[]{3, 4}));
    }

    @Test
    void testHighlightOneCheckerAndPrint() {

    }

    @Test
    void testHighlightCheckersAndPrint() {

    }

    @Test
    void testCalculateMoves() {
        // Assuming that the calculateMoves method returns the expected destinations
        assertEquals(List.of(9, 8, 7), board.calculateMoves(10, new int[]{1, 2}, 1));
        assertEquals(List.of(13), board.calculateMoves(24 - 15, new int[]{3, 4}, 2));
    }

    @Test
    void testHasCheckerOnBeam() {

    }

    @Test
    void promptUserPickDestination() {
    }

    @Test
    void canBearOff() {
    }

    @Test
    void checkForWin() {
    }

    @Test
    void checkForGammon() {
    }


}
