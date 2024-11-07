import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    @DisplayName("Test 2: Out-of-range guess throws exception")
    void test2() {
        assertThrows(GuessOutOfRangeException.class, () -> game.makeGuess("150"),
                "Expected GuessOutOfRangeException for out-of-range guess");
    }

    @Test
    @DisplayName("Test 4: Repeated guess")
    void test4() throws GuessOutOfRangeException {

        game.makeGuess("50");
        game.makeGuess("50");
        assertEquals(4.0, game.makeGuess("50"));
    }

    @Test
    @DisplayName("Test 5: Too-high guess ")
    void test5() throws GuessOutOfRangeException {
        game.setCorrectNumber(40);
        assertEquals(1.99, game.makeGuess("41"));
    }

    @Test
    @DisplayName("Test 6: Too-low ")
    void test6() throws GuessOutOfRangeException {
        game.setCorrectNumber(50);
        assertEquals(2.01, game.makeGuess("49"));
    }

    @Test
    @DisplayName("Test 7: Exceeding max attempts")
    void test7() throws GuessOutOfRangeException {
        //int counter = 0;
        game.setCorrectNumber(50);
        for (int i = 0; i < 10; i++) {
            game.makeGuess("30");  // Incorrect guesses
           // counter++;
            System.out.println(  game.getGuess_count()); // <-- seem to be individuaal guesses not as a collective
        }
        assertTrue(game.isGameOver());
    }

    @Test
    @DisplayName("Test 8: Guess after game over")
    void test8() throws GuessOutOfRangeException {
        game.setGameOver(true);
        assertEquals(6.0, game.makeGuess("40"));
    }

    @Test
    @DisplayName("Test 9: Check guess number correction")
    void test9() {
        game.setCorrectNumber(60);
        assertEquals(60, game.correctNumber);
    }


    @Test
    @DisplayName("Test 11: Calculate average of guesses")
    void test11() {
        Set<Integer> guesses = new HashSet<>();
        guesses.add(10);
        guesses.add(20);
        guesses.add(30);
        Double average = game.calculateAverage(guesses);
        assertEquals(20.0, average);
    }

    @Test
    @DisplayName("Test 12:  Process range of guesses ")
    void test12() {
        String[] guesses = {"2", "50", "101", "99", "0"};
        int validGuessCount = game.processValidGuesses(guesses);
        assertEquals(3, validGuessCount);
    }

    @Test
    @DisplayName("Test 13: Check game state when max is exceeded")
    void test13() throws GuessOutOfRangeException {
        // treating guess as individual and not a stread of guess <-- reseting after every guess
        for (int i = 0; i < 10; i++) {
            game.makeGuess("30");  // Incorrect guesses
        }
        assertTrue(game.isGameOver());
    }

}