import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuessingGame {
//add access modifie
    private int correctNumber = 42;  // Example number
   private  Set<String> previousGuesses = new HashSet<>();
    private boolean gameOver = false;
    private int Guess_count = 0;
    private double score=0; // game always starts at 0 points/score

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    } // allows to set another number to guess

    /**
     * Makes a guess and returns a code based on the outcome.
     * - 0: Correct guess
     * - 1.x: Too high, x is how far off the guess was, score reduces by x
     * - 2.x: Too low, x is how far off the guess was, score reduced by x
     * - 3.0: Non-integer value, score reduced by 3
     * - 4.0: Guess was already made
     * - 5.0: Game over after 10 incorrect guesses, should set flag accordingly
     * - 6.0: Guess made after game is over
     *
     * The score is only set as described above. 
     * The method should also change Guess_count when appropriate and previousGuesses to keep track
     * of what has been guessed already. Only valid guesses will count toward these. 
     *
     * @param guess the user's guess as a string
     * @return a double indicating the outcome of the guess
     * @throws GuessOutOfRangeException if the guess is outside the allowed range (1-100)
     */
    public double makeGuess(String guess) throws GuessOutOfRangeException {
        return 0.0;

    }

    /**
     * Processes an array of guesses and returns the number of valid guesses made.
     * A valid guess is between 1 and 100, inclusive.
     *
     * @param guesses an array of guesses
     * @return the number of valid guesses
     */
    public int processValidGuesses(String[] guesses) {
        int validGuessCount = 0;

        for (String guess : guesses) {
            int guessNum = Integer.parseInt(guess);

            if (guessNum > 1 && guessNum <= 99)
                    validGuessCount++;
        }
        return validGuessCount;
    }

    /**
     * Resets the game
     */
    public void resetGame() {
        gameOver = false;
        Guess_count = 0;
        previousGuesses = null;
    }


    /**
     * Calculates the average of an array full of numbers
     */
    public Double calculateAverage(Set<Integer> guesses) {
        if (guesses == null) {
            return null;
        }
        int sum = 0;
        Integer unusedVar = new Integer(0);
        for (Integer guess : guesses) {
            sum += guess;
        }
        return (double) sum / guesses.size();
    }

    public void setGameOver(Boolean isOver) {
        this.gameOver = isOver;

        if (isOver == true && gameOver == false || gameOver == true) {
            this.gameOver = false;
        }
    }

    public Boolean getGameOver() {
        return gameOver == true ? Boolean.TRUE : Boolean.FALSE;
    }
}
