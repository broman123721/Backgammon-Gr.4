import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckerTest {

    @Test
    void testCheckerInitialization() {
        Checker checker = new Checker("\u001B[37m", 0, 0, 25);

        assertEquals("\u001B[37m", checker.getColor_str(), "Checker color should be initialized");
        assertEquals(0, checker.getIndex_int(), "Checker index should be initialized");
        assertEquals(0, checker.getPosition_int(), "Checker position should be initialized");
        assertEquals(25, checker.getPip_int(), "Checker pip count should be initialized");
    }

    @Test
    void testSetterMethods() {
        Checker checker = new Checker("\u001B[37m", 0, 0, 25);

        checker.setColor_str("\u001B[31m");
        checker.setIndex_int(1);
        checker.setPosition_int(1);
        checker.setPip_int(20);

        assertEquals("\u001B[31m", checker.getColor_str(), "Checker color should be set");
        assertEquals(1, checker.getIndex_int(), "Checker index should be set");
        assertEquals(1, checker.getPosition_int(), "Checker position should be set");
        assertEquals(20, checker.getPip_int(), "Checker pip count should be set");
    }

    @Test
    void testBarIndex() {
        Checker checker = new Checker("\u001B[37m", 0, 0, 25);

        checker.setBarindex_int(1);
        assertEquals(1, checker.getBarindex_int(), "Checker bar index should be set");
    }
}
