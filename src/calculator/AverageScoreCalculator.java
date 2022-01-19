package calculator;

import common.Constants;
import pojo.Child;

public class AverageScoreCalculator {

    /**
     * This method returns the averageNiceScore of a Child object
     * <p>This method is to be modified as necessary by each class that extends it depending on
     * the child that it is applied for</p>
     * @param kid the Child object for witch the averageNiceScore is calculated
     * @return the calculated averageNiceScore
     */
    public  Double getAverage(final Child kid) {
        return 0d;
    }

    /**
     * <p>No need to override this method as it does not change depending on the type of child it
     * is applied for</p>
     * @param c the child for whom the bonusScore is added
     */
    public void getAverageB(final Child c) {
        Double score = c.getAverageScore();
        score += score * c.getNiceScoreBonus() / Constants.PERCENTAGE;
        if (score > Constants.MAX_SCORE) {
            score = Constants.MAX_SCORE;
        }
        c.setAverageScore(score);
    };

}
