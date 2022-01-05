package calculator;

import common.Constants;
import pojo.Child;

public final class BabyAverage extends AverageScoreCalculator {
    private static BabyAverage instance = null;

    private BabyAverage() { }

    /**
     * @return the BabyAverage instance for SINGLETON
     */
    public static BabyAverage getInstance() {
        if (instance == null) {
            instance = new BabyAverage();
        }
        return instance;
    }

    @Override
    public Double getAverage(final Child kid) {
        return Constants.BABY_SCORE;
    }
}
