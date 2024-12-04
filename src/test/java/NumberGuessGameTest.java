import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//assignment 3
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
                "Expected GuessgOutOfRangeException for out-of-range guess");
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
        assertEquals(1.0, game.makeGuess("41"));
    }

    @Test
    @DisplayName("Test 6: Too-low ")
    void test6() throws GuessOutOfRangeException {
        game.setCorrectNumber(50);
        assertEquals(2.0, game.makeGuess("49"));
    }

    @Test
    @DisplayName("Test 7: Exceeding max attempts")
    void test7() throws GuessOutOfRangeException {
        game.makeGuess("1");
        game.makeGuess("2");
        game.makeGuess("10");
        game.makeGuess("12");
        game.makeGuess("99");
        game.makeGuess("54");
        game.makeGuess("75");
        game.makeGuess("89");
        game.makeGuess("23");
        game.makeGuess("11");
        game.makeGuess("14");
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
        assertEquals(60, game.getCorrectNumber());
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
    @DisplayName("Test processValidGuesses with dynamic difficulty levels")
    void testProcessValidGuessesWithDifficultyLevels() {
        game.setDifficulty(DifficultyLevel.HARD);
        String[] guesses = {"10", "250", "180", "abc", "-5"};
        assertEquals(2, game.processValidGuesses(guesses)); // Valid: 10, 180
    }

    @Test
    @DisplayName("Test makeGuess with custom scoring strategy")
    void testMakeGuessWithCustomScoring() throws GuessOutOfRangeException {
        game.setDifficulty(DifficultyLevel.EASY);       // Set difficulty to EASY (range 1–50)
        game.setScoringStrategy(new PenaltyScoring()); // Set scoring strategy to PenaltyScoring
        game.setCorrectNumber(20);                     // Correct number is 20

        assertEquals(0.0, game.makeGuess("20"), "Correct guess should result in no penalty."); // Correct guess

        // For guess = 30, correct = 20, difference = 10
        // Penalty = (difference / 5) + 1 = (10 / 5) + 1 = 3
        // Starting score is 0.0, final score should be -3.0
        assertEquals(-3.0, game.makeGuess("30"), "Penalty calculation for difference of 10 should result in -3."); // Penalty applied
    }




}