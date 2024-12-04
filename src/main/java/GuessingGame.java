import java.util.HashSet;
import java.util.Set;

public class GuessingGame {

    private int correctNumber;
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM; // Default difficulty level
    private Set<String> previousGuesses = new HashSet<>();
    private boolean gameOver = false;
    private int guessCount = 0;
    private double score = 0.0; // Starting score
    private ScoringStrategy scoringStrategy = new SimpleScoring(); // Default scoring strategy

    // Setters
    public void setDifficulty(DifficultyLevel level) {
        this.difficultyLevel = level;
        this.correctNumber = (int) (Math.random() * (level.getMax() - level.getMin() + 1)) + level.getMin();
    }

    public void setScoringStrategy(ScoringStrategy strategy) {
        this.scoringStrategy = strategy;
    }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    }

    // Getters
    public int getCorrectNumber() {
        return correctNumber;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // Game methods
    public double makeGuess(String guess) throws GuessOutOfRangeException {
        if (gameOver) {
            return GuessOutcome.GAME_OVER.getOutcomeValue();
        }
        if (guessCount >= 10) {
            gameOver = true; // Mark game over if max attempts are reached
            return GuessOutcome.EXCEEDED_GUESSES.getOutcomeValue();
        }
        int guessNum;
        try {
            guessNum = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            score -= 3.0; // Deduct points for invalid input
            return GuessOutcome.NON_INTEGER.getOutcomeValue();
        }
        if (guessNum < difficultyLevel.getMin() || guessNum > difficultyLevel.getMax()) {
            throw new GuessOutOfRangeException("Guess must be between " + difficultyLevel.getMin() + " and " + difficultyLevel.getMax());
        }
        if (previousGuesses.contains(guess)) {
            score -= 2.0; // Deduct points for repeated guess
            return GuessOutcome.REPEATED_GUESS.getOutcomeValue();
        }
        previousGuesses.add(guess);
        guessCount++;
        if (guessNum == correctNumber) {
            gameOver = true; // Mark game over on correct guess
            return GuessOutcome.CORRECT.getOutcomeValue();
        }
        score = scoringStrategy.calculateScore(correctNumber, guessNum, score);
        return guessNum > correctNumber
                ? GuessOutcome.TOO_HIGH.getOutcomeValue()
                : GuessOutcome.TOO_LOW.getOutcomeValue();
    }


    public int processValidGuesses(String[] guesses) {
        if (difficultyLevel == null) {
            throw new IllegalStateException("Difficulty level is not set.");
        }
        int validGuessCount = 0;
        for (String guess : guesses) {
            try {
                int guessNum = Integer.parseInt(guess);
                if (guessNum >= difficultyLevel.getMin() && guessNum <= difficultyLevel.getMax()) {
                    validGuessCount++;
                }
            } catch (NumberFormatException ignored) {
                // Skip invalid input
            }
        }
        return validGuessCount;
    }

    public void resetGame() {
        gameOver = false;
        guessCount = 0;
        previousGuesses.clear();
        score = 0.0;
        correctNumber = (int) (Math.random() * (difficultyLevel.getMax() - difficultyLevel.getMin() + 1)) + difficultyLevel.getMin();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Double calculateAverage(Set<Integer> guesses) {
        if (guesses == null || guesses.isEmpty()) {
            return null;
        }
        int sum = 0;
        for (Integer guess : guesses) {
            sum += guess;
        }
        return (double) sum / guesses.size();
    }
}





/**
 * Makes a guess and returns a code based on the outcome.
 * - 0: Correct guess
 * - 1.x: Too high, x is how far off the guess was, score reduces by x
 * - 2.x: Too low, x is how far off the guess was, score reduced by x
 * - 3.0: Non-integer value, score reduced by 3
 * - 4.0: Guess was already made
 * - 5.0: Game over after 10 incorrect guesses, should set flag accordingly
 * - 6.0: Guess made after game is over
 *<p></p>
 * The score is only set as described above.
 * The method should also change Guess_count when appropriate and previousGuesses to keep track
 * of what has been guessed already. Only valid guesses will count toward these.
 *
 * @param guess the user's guess as a string
 * @return a double indicating the outcome of the guess
 * @throws GuessOutOfRangeException if the guess is outside the allowed range (1-100)
 */


