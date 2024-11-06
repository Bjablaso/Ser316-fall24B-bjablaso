import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlackBoxTest {


    static Stream<Arguments> provideGuessingGameInstances() {
        return Stream.of(
                Arguments.of(new NumberGuessGame1()),
                Arguments.of(new NumberGuessGame2()),
                Arguments.of(new NumberGuessGame3()),
                Arguments.of(new NumberGuessGame4()),
                Arguments.of(new NumberGuessGame5())
                );
    }


    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test makeGuess() across different classes")
    void testMakeGuess(GuessingGame guessingGame) throws GuessOutOfRangeException {
        // Example test case for all the classes
        //double result = guessingGame.makeGuess("example guess");

        // Check the result 
        //assertEquals(3.0, result);// This is just an example


        assertEquals(0.0, guessingGame.makeGuess("42"), "Expected correct guess outcome");


        assertEquals(1.99, guessingGame.makeGuess("43"), "Expected too high outcome near boundary");


        assertEquals(2.01, guessingGame.makeGuess("41"), "Expected too low outcome near boundary");


        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("0"),
                "Expected out-of-range exception for guess '0' (below range)");


        assertThrows(GuessOutOfRangeException.class, () -> guessingGame.makeGuess("101"),
                "Expected out-of-range exception for guess '101' (above range)");


        assertEquals(3.0, guessingGame.makeGuess("abc"), "Expected outcome for non-integer input");


        guessingGame.makeGuess("42");
        assertEquals(4.0, guessingGame.makeGuess("42"), "Expected outcome for repeated correct guess");


        for (int i = 0; i < 10; i++) {
            guessingGame.makeGuess("100");
        }
        assertEquals(5.0, guessingGame.makeGuess("50"), "Expected outcome for guess after exceeding limit");


        guessingGame.setGameOver(true);
        assertEquals(6.0, guessingGame.makeGuess("50"), "Expected outcome for guess after game over");
    }

}

