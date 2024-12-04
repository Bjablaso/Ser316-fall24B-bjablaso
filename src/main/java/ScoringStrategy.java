public interface ScoringStrategy {
    double calculateScore(int correctNumber, int guessNum, double currentScore);
}

