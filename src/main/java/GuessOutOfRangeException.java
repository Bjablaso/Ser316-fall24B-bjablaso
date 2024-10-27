/**
 * Custom exception for out-of-range guesses.
 */
class GuessOutOfRangeException extends Exception {
    public GuessOutOfRangeException(String message) {
        super(message);
    }
}
