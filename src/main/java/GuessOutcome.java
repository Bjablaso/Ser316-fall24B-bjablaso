public enum GuessOutcome {
    CORRECT(0.0),
    TOO_HIGH(1.0),
    TOO_LOW(2.0),
    NON_INTEGER(3.0),
    REPEATED_GUESS(4.0),
    EXCEEDED_GUESSES(5.0),
    GAME_OVER(6.0);

    private final double outcomeValue;

    GuessOutcome(double outcomeValue) {
        this.outcomeValue = outcomeValue;
    }

    public double getOutcomeValue() {
        return outcomeValue;
    }

}