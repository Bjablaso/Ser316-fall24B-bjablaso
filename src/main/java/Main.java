/**
 * Main function Run guess simulation.
 */
public class Main {
    /**
     * Function to run Program.
     * @param args can take in argument
     */
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();

        try {
            // simulate guess, work with implementation of makeGuess.
            System.out.println(game.makeGuess("50"));  // Too high
            System.out.println(game.makeGuess("35"));  // Too high
            System.out.println(game.makeGuess("abc"));  // Non-numeric input
            System.out.println(game.makeGuess("42"));  // Correct guess
            System.out.println(game.makeGuess("105"));  // Should throw GuessOutOfRangeException
        } catch (GuessOutOfRangeException e) {
            System.out.println(e.getMessage());
        }


        int numberGuess;
    }
}
