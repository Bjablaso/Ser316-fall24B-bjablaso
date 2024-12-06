import java.util.HashSet;
import java.util.Set;

public class GuessingGame {

    private int correctNumber;
    private DifficultyLevel difficultyLevel;
    private Set<String> previousGuesses;
    private boolean gameOver;
    private int guessCount;
    private double score;
    private ScoringStrategy scoringStrategy;
    private static final int MAX_GUESSES = 10;

    // Private constructor for Builder
    GuessingGame(Builder builder) {
        this.difficultyLevel = builder.difficultyLevel;
        this.correctNumber = builder.correctNumber;
        this.previousGuesses = new HashSet<>();
        this.gameOver = false;
        this.guessCount = 0;
        this.score = 0.0;
        this.scoringStrategy = builder.scoringStrategy;
    }

    // Helper Methods
    private boolean isGuessValid(int guessNum) {
        return guessNum >= difficultyLevel.getMin() && guessNum <= difficultyLevel.getMax();
    }

    private void applyPenaltyForInvalidGuess(String guess) {
        if (previousGuesses.contains(guess)) {
            score -= 2.0; // Penalty for repeated guess
        } else {
            score -= 3.0; // Penalty for invalid guess
        }
    }

    // Game Methods
    public double makeGuess(String guess) throws GuessOutOfRangeException {
        if (gameOver) {
            return GuessOutcome.GAME_OVER.getOutcomeValue();
        }

        if (guessCount == MAX_GUESSES) {
            gameOver = true; // Mark game over if max attempts are reached
            System.out.println(gameOver);
            return GuessOutcome.EXCEEDED_GUESSES.getOutcomeValue();
        }

        int guessNum;
        try {
            guessNum = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            applyPenaltyForInvalidGuess(guess);
            return GuessOutcome.NON_INTEGER.getOutcomeValue();
        }

        if (!isGuessValid(guessNum)) {
            throw new GuessOutOfRangeException("Guess must be between " + difficultyLevel.getMin() + " and " + difficultyLevel.getMax());
        }

        if (previousGuesses.contains(guess)) {
            applyPenaltyForInvalidGuess(guess);
            return GuessOutcome.REPEATED_GUESS.getOutcomeValue();
        }

        previousGuesses.add(guess);
        guessCount++;
        System.out.println("Guess count : " + guessCount);

        if (guessNum == correctNumber) {
            gameOver = true;
            return GuessOutcome.CORRECT.getOutcomeValue();
        }

        score = scoringStrategy.calculateScore(correctNumber, guessNum, score);
        return guessNum > correctNumber ? GuessOutcome.TOO_HIGH.getOutcomeValue() : GuessOutcome.TOO_LOW.getOutcomeValue();
    }

    // Additional Methods
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getCorrectNumber() {
        return correctNumber;
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

    public void setCorrectNumber(int correctNumber) {
        if (difficultyLevel == null) {
            throw new IllegalStateException("Difficulty level must be set before setting a correct number.");
        }
        if (correctNumber < difficultyLevel.getMin() || correctNumber > difficultyLevel.getMax()) {
            throw new IllegalArgumentException("Correct number must be within the range of the difficulty level.");
        }
        this.correctNumber = correctNumber;
    }

    public int getGuessCount() {
        return guessCount;
    }


    // Builder Class
    public static class Builder {
        private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;
        private int correctNumber = -1; // Default placeholder
        private ScoringStrategy scoringStrategy = new SimpleScoring();

        public Builder withDifficulty(DifficultyLevel level) {
            this.difficultyLevel = level;
            this.correctNumber = (int) (Math.random() * (level.getMax() - level.getMin() + 1)) + level.getMin();
            return this;
        }

        public Builder withCustomNumber(int correctNumber) {
            this.correctNumber = correctNumber;
            return this;
        }

        public Builder withScoringStrategy(ScoringStrategy strategy) {
            this.scoringStrategy = strategy;
            return this;
        }

        public GuessingGame build() {
            if (correctNumber == -1) {
                throw new IllegalStateException("Correct number must be set either explicitly or via difficulty level.");
            }
            return new GuessingGame(this);
        }
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


