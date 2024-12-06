

public class SimpleScoring implements ScoringStrategy {
    @Override
    public double calculateScore(int correctNumber, int guessNum, double currentScore) {
        double difference = Math.abs(correctNumber - guessNum);
        return currentScore - difference / 10;
    }
}
