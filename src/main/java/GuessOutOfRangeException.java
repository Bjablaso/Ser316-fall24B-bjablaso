/**
 * Custom exception for out-of-range guesses.
 */
class GuessOutOfRangeException extends Exception {
    private static final long serialVersionUID = 1L;

    public GuessOutOfRangeException(String message) {
        super(message);
    }
}
