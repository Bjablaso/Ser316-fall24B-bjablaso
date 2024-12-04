class PenaltyScoring implements ScoringStrategy {
    @Override
    public double calculateScore(int correctNumber, int guessNum, double currentScore) {
        double difference = Math.abs(correctNumber - guessNum);
        return currentScore - (difference / 5) - 1;
    }

}