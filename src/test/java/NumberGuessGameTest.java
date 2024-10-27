import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// assignment 3
class NumberGuessGameTest {

    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test
    @DisplayName("Test correct guess")
    void testCorrectGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(50);
        assertEquals(0.0, game.makeGuess("50"));
    }
}