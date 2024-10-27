public class Main {
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();

        try {
            // Simulating a few guesses, this will not work when you get it only after you implement makeGuess in assignment 3
            System.out.println(game.makeGuess("50"));  // Too high
            System.out.println(game.makeGuess("35"));  // Too high
            System.out.println(game.makeGuess("abc"));  // Non-numeric input
            System.out.println(game.makeGuess("42"));  // Correct guess
            System.out.println(game.makeGuess("105"));  // Should throw GuessOutOfRangeException
        } catch (GuessOutOfRangeException e) {
            System.out.println(e.getMessage());
        }


        int numberGUESS;
    }
}
