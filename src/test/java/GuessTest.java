import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GuessTest {

    static Stream<Arguments> provideGuessingGameInstance() {
        return Stream.of(
                Arguments.of(new GuessingGame.Builder()
                        .withDifficulty(DifficultyLevel.MEDIUM) // Default difficulty
                        .withCustomNumber(50) // Default correct number
                        .build())
        );
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstance")
    @DisplayName("Test makeGuess() in GuessingGame class")
    void testMakeGuess(GuessingGame guessingGame) throws GuessOutOfRangeException {
        // Test correct guess
        guessingGame.makeGuess("50");
        assertTrue(guessingGame.isGameOver(), "Game over state should be enacted for a correct guess");

        // Reset and test other scenarios
        guessingGame.resetGame();

        guessingGame.makeGuess("43");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue for a too-high guess");

        guessingGame.makeGuess("41");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue for a too-low guess");

        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("0"),
                "Expected out-of-range exception for guess '0' (below range)");
        assertFalse(guessingGame.isGameOver(), "Game should continue after out-of-range guess");

        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("101"),
                "Expected out-of-range exception for guess '101' (above range)");
        assertFalse(guessingGame.isGameOver(), "Game should continue after out-of-range guess");

        guessingGame.makeGuess("abc");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue after non-integer input");

        guessingGame.makeGuess("42");
        guessingGame.makeGuess("42");
        assertFalse(guessingGame.isGameOver(), "Game should not be over after repeated guesses");
        guessingGame.resetGame();

        // Test exceeding maximum attempts
        guessingGame.makeGuess("1");
        guessingGame.makeGuess("2");
        guessingGame.makeGuess("10");
        guessingGame.makeGuess("12");
        guessingGame.makeGuess("99");
        guessingGame.makeGuess("54");
        guessingGame.makeGuess("75");
        guessingGame.makeGuess("89");
        guessingGame.makeGuess("23");
        guessingGame.makeGuess("11");
        guessingGame.makeGuess("14");
        assertTrue(guessingGame.isGameOver(), "Game should be over after exceeding maximum attempts");

        // Test remaining in gameOver state
        guessingGame.setGameOver(true);
        guessingGame.makeGuess("50");
        assertTrue(guessingGame.isGameOver(), "Expected game to remain in gameOver state.");
    }
}
