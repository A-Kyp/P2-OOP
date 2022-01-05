package calculator;

import common.Constants;

public final class AverageFactory {
    private AverageFactory() { }

    /**
     * @param age criteria for returning the specialised AverageScoreCalculator instance
     * @return a suitable AverageScoreCalculator instance
     */
    public static AverageScoreCalculator create(final Integer age) {
        if (age < Constants.BABY_AGE) {
            return BabyAverage.getInstance();
        } else if (age < Constants.KID_AGE) {
            return KidAverage.getInstance();
        } else {
            return TeenAverage.getInstance();
        }
    }
}
