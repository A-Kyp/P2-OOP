package calculator;

import common.Constants;
import pojo.Child;

public class AverageScoreCalculator {

    /**
     * This method returns the averageNiceScore of a Child object
     * @param kid the Child object for witch the averageNiceScore is calculated
     * @return the calculated averageNiceScore
     */
    public  Double getAverage(final Child kid) {
        return 0d;
    }
    public void getAverageB(final Child c) {
        double score = c.getAverageScore();
        score += score * c.getNiceScoreBonus() / 100;
        if (score > Constants.MAX_SCORE) {
            score = Constants.MAX_SCORE;
        }
        c.setAverageScore(score);
    };

}
