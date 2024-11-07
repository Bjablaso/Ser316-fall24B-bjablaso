import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
        guessingGame.makeGuess("42");
        assertTrue(guessingGame.isGameOver(), "Expected game to be in gameOver state after correct guess");

        guessingGame.resetGame();

        guessingGame.makeGuess("43");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue for a too-high guess");


        guessingGame.makeGuess("41");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue for a too-low guess");


        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("0"),
                "Expected out-of-range exception for guess '0' (below range)");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue after out-of-range guess");


        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("101"),
                "Expected out-of-range exception for guess '101' (above range)");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue after out-of-range guess");


        guessingGame.makeGuess("abc");
        assertFalse(guessingGame.isGameOver(), "Expected game to continue after non-integer input");


        guessingGame.makeGuess("42");
        guessingGame.makeGuess("42");
        assertTrue(guessingGame.isGameOver(), "Expected game to remain in gameOver state after repeated correct guess");
        guessingGame.resetGame();

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
        System.out.println(guessingGame.getGuess_count());
        assertTrue(guessingGame.isGameOver(), "Expected game to be in gameOver state after exceeding guess limit");


        guessingGame.setGameOver(true);
        guessingGame.makeGuess("50");
        assertTrue(guessingGame.isGameOver(), "Expected game to remain in gameOver state.");
    }
}