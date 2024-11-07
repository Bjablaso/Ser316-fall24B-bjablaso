import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class guessTest {

    static Stream<Arguments> provideGuessingGameInstance() {
        return Stream.of(
                Arguments.of(new GuessingGame())
        );
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstance")
    @DisplayName("Test makeGuess() in GuessingGame class")
    void testMakeGuess(GuessingGame guessingGame) throws GuessOutOfRangeException {
        // Test for a correct guess
        assertEquals(0.0, guessingGame.makeGuess("42"), "Expected correct guess outcome");

        // Test for a guess that is slightly too high
        assertEquals(1.99, guessingGame.makeGuess("43"), "Expected too high outcome near boundary");

        // Test for a guess that is slightly too low
        assertEquals(2.01, guessingGame.makeGuess("41"), "Expected too low outcome near boundary");

        // Test for an out-of-range guess below the lower boundary
        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("0"),
                "Expected out-of-range exception for guess '0' (below range)");

        // Test for an out-of-range guess above the upper boundary
        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("101"),
                "Expected out-of-range exception for guess '101' (above range)");

        // Test for non-integer input
        assertEquals(3.0, guessingGame.makeGuess("abc"), "Expected outcome for non-integer input");

        // Test for repeated correct guess
        guessingGame.makeGuess("42");
        assertEquals(4.0, guessingGame.makeGuess("42"), "Expected outcome for repeated correct guess");

        // Test for guess after exceeding limit of attempts
        for (int i = 0; i < 10; i++) {
            guessingGame.makeGuess("100");
        }
        assertEquals(5.0, guessingGame.makeGuess("50"), "Expected outcome for guess after exceeding limit");

        // Test for guess after game is over
        guessingGame.setGameOver(true);
        assertEquals(6.0, guessingGame.makeGuess("50"), "Expected outcome for guess after game over");
    }
}