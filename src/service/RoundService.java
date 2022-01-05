package service;

import pojo.Child;

import java.util.ArrayList;

public final class RoundService {
    private static RoundService instance;

    private RoundService() { }

    /**
     * @return the RoundService instance for SINGLETON
     */
    public static RoundService getInstance() {
        if (instance == null) {
            instance = new RoundService();
        }
        return instance;
    }

    /**
     * @param kids the Child containing all existing children
     * @return the sum of all averageNiceScores
     */
    public Double sumAvg(final ArrayList<Child> kids) {
        double sum = 0d;
        for (Child c : kids) {
            sum += c.getAverageScore();
        }
        return sum;
    }
}
