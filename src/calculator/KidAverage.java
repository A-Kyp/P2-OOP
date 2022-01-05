package calculator;

import pojo.Child;

public final class KidAverage extends AverageScoreCalculator {
    private static KidAverage instance = null;

    private KidAverage() { }

    /**
     * @return the KidAverage instance for SINGLETON
     */
    public static KidAverage getInstance() {
        if (instance == null) {
            instance = new KidAverage();
        }
        return instance;
    }

    @Override
    public Double getAverage(final  Child kid) {
        Double sum = 0d;
        for (Double score : kid.getNiceScoreHistory()) {
            sum += score;
        }
        return sum / kid.getNiceScoreHistory().size();
    }
}
