import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessGameTest {

    @Test
    @DisplayName("Test correct guess")
    void testCorrectGuess() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withCustomNumber(50)
                .build();
        assertEquals(0.0, game.makeGuess("50"));
    }

    @Test
    @DisplayName("Test 2: Out-of-range guess throws exception")
    void testOutOfRangeGuess() {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.MEDIUM)
                .withCustomNumber(50)
                .build();

        // Use the 'game' variable properly before it goes out of scope
        assertThrows(GuessOutOfRangeException.class, () -> game.makeGuess("150"),
                "Expected GuessOutOfRangeException for out-of-range guess");

        // Verify that the game state is still valid after the exception
        assertFalse(game.isGameOver(), "Game should continue after an out-of-range guess.");
    }

    @Test
    @DisplayName("Test penalty for repeated guess")
    void testPenaltyForRepeatedGuess() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.MEDIUM)
                .withCustomNumber(50) // Set a custom correct number
                .build();

        // First guess (valid)
        double outcome1 = game.makeGuess("25");
        assertEquals(GuessOutcome.TOO_LOW.getOutcomeValue(), outcome1, "First guess should be too low.");

        // Second guess (repeated)
        double outcome2 = game.makeGuess("25");
        assertEquals(GuessOutcome.REPEATED_GUESS.getOutcomeValue(), outcome2, "Second guess should be identified as repeated.");

        // Ensure the penalty is applied
        assertTrue(game.isGameOver() == false, "Game should not be over after repeated guess.");

    }


    @Test
    @DisplayName("Test 5: Too-high guess")
    void testTooHighGuess() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withCustomNumber(40)
                .build();
        assertEquals(1.0, game.makeGuess("41"));
    }

    @Test
    @DisplayName("Test 6: Too-low guess")
    void testTooLowGuess() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withCustomNumber(50)
                .build();
        assertEquals(2.0, game.makeGuess("49"));
    }

    @Test
    @DisplayName("Test 7: Exceeding max attempts")
    void testExceedingMaxAttempts() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.MEDIUM)
                .build();

        // Make 10 individual guesses
        game.makeGuess("1");
        game.makeGuess("2");
        game.makeGuess("3");
        game.makeGuess("4");
        game.makeGuess("5");
        game.makeGuess("6");
        game.makeGuess("7");
        game.makeGuess("8");
        game.makeGuess("9");
        game.makeGuess("10");
        game.makeGuess("11");

        // Verify that the game is over after 10 attempts
        assertTrue(game.isGameOver(), "Game should be over after 10 guesses");
    }

    @Test
    @DisplayName("Test 8: Guess after game over")
    void testGuessAfterGameOver() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withCustomNumber(40)
                .build();
        game.setGameOver(true);
        assertEquals(6.0, game.makeGuess("40"));
    }

    @Test
    @DisplayName("Test 9: Check guess number correctness")
    void testGuessNumberCorrectness() {
        GuessingGame game = new GuessingGame.Builder()
                .withCustomNumber(60)
                .build();
        assertEquals(60, game.getCorrectNumber());
    }

    @Test
    @DisplayName("Test 11: Calculate average of guesses")
    void testCalculateAverage() {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.EASY)
                .build();
        Set<Integer> guesses = new HashSet<>();
        guesses.add(10);
        guesses.add(20);
        guesses.add(30);
        Double average = game.calculateAverage(guesses);
        assertEquals(20.0, average);
    }

    @Test
    @DisplayName("Test 12: Process range of guesses")
    void testProcessRangeOfGuesses() {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.MEDIUM)
                .build();
        String[] guesses = {"2", "50", "101", "99", "0"};
        int validGuessCount = game.processValidGuesses(guesses);
        assertEquals(3, validGuessCount);
    }

    @Test
    @DisplayName("Test processValidGuesses with dynamic difficulty levels")
    void testProcessValidGuessesWithDifficultyLevels() {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.HARD)
                .build();
        String[] guesses = {"10", "250", "180", "abc", "-5"};
        assertEquals(2, game.processValidGuesses(guesses)); // Valid: 10, 180
    }

    @Test
    @DisplayName("Test makeGuess with custom scoring strategy")
    void testMakeGuessWithCustomScoring() throws GuessOutOfRangeException {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.EASY)
                .withCustomNumber(20)
                .withScoringStrategy(new PenaltyScoring())
                .build();

        assertEquals(0.0, game.makeGuess("20"), "Correct guess should result in no penalty."); // Correct guess

        // For guess = 30, correct = 20, difference = 10
        // Penalty = (difference / 5) + 1 = (10 / 5) + 1 = 3
        // Starting score is 0.0, final score should be -3.0
        assertEquals(-3.0, game.makeGuess("30"), "Penalty calculation for difference of 10 should result in -3."); // Penalty applied
    }
}
