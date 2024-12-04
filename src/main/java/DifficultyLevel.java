public enum DifficultyLevel {
    EASY(1, 50),
    MEDIUM(1, 100),
    HARD(1, 200);

    private final int min;
    private final int max;

    DifficultyLevel(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}