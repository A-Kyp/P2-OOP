package calculator;

import pojo.Child;

public final class TeenAverage extends AverageScoreCalculator {
    private static TeenAverage instance = null;

    private TeenAverage() { }

    /**
     * @return the TeenAverage instance for SINGLETON
     */
    public static TeenAverage getInstance() {
        if (instance == null) {
            instance = new TeenAverage();
        }
        return instance;
    }

    @Override
    public Double getAverage(final Child kid) {
        double sum = 0d;
        int weight = kid.getNiceScoreHistory().size();
        int totalWeight = weight * (weight + 1) / 2;
        weight = 1;

        for (Double score : kid.getNiceScoreHistory()) {
            sum += score * weight;
            weight++;
        }

        return sum / totalWeight;
    }
}
