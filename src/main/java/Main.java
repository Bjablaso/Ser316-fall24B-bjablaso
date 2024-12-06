/**
 * Main function Run guess simulation.
 */
public class Main {
    /**
     * Function to run Program.
     * @param args can take in argument
     */
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame.Builder()
                .withDifficulty(DifficultyLevel.MEDIUM)
                .withScoringStrategy(new SimpleScoring())
                .build();

        try {
            System.out.println(game.makeGuess("50"));
            System.out.println(game.makeGuess("35"));
            System.out.println(game.makeGuess("abc"));
            System.out.println(game.makeGuess("42"));
            System.out.println(game.makeGuess("105"));
        } catch (GuessOutOfRangeException e) {
            System.out.println(e.getMessage());
        }
    }
}
