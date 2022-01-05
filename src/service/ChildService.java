package service;

import calculator.AverageFactory;
import calculator.AverageScoreCalculator;
import enums.Category;
import pojo.Child;
import pojo.Gift;

import java.util.ArrayList;

public final class ChildService {
    private static ChildService instance;

    private ChildService() { }

    /**
     * @return the ChildInstance for SINGLETON
     */
    public static ChildService getInstance() {
        if (instance == null) {
            instance = new ChildService();
        }
        return instance;
    }

    /**
     * @param c Child object to be updated
     * @param score the niceScore to be added to niceScoreHistory
     */
    public void updateHistory(final Child c, final Double score) {
        c.getNiceScoreHistory().add(score);
    }

    /**
     * <p>
     *     This methods continues the trick from the reading part.
     *     The niceScore that was read as part of the initial data and stored in the
     *     averageNiceScore will be moved to the niceScoreHistory array and the averageNiceScore
     *     field is now ready to be modified and to store the real calculated value
     * </p>
     * @param kids the Child array containing all existing children
     */
    public void updateMassHistory(final ArrayList<Child> kids) {
        for (Child c : kids) {
            this.updateHistory(c, c.getAverageScore());
        }
    }

    /**
     * @param c the Child object to be updated
     */
    public void updateAvgScore(final Child c) {
        AverageScoreCalculator calc;
        calc = AverageFactory.create(c.getAge());
        if (calc.getAverage(c) != null) {
            c.setAverageScore(calc.getAverage(c));
        } // else do nothing as the Child would be a young adult and is already eliminated
    }

    /**
     * @param c the Child object to be updated
     * @param budgetUnit the current round calculated budgetUnit
     */
    public void updateAllocatedBudget(final Child c, final Double budgetUnit) {
        c.setAssignedBudget(c.getAverageScore() * budgetUnit);
    }

    /**
     * @param c the Child object to be updated
     * @param gifts the sorted Gift array containing the available gifts
     */
    public void allocateGift(final Child c, final ArrayList<Gift> gifts) {
        double budget = c.getAssignedBudget();
        for (Category category : c.getGiftsPreferences()) {
            for (Gift g : gifts) {
                if (g.getCategory().compareTo(category) == 0 && g.getPrice() <= budget) {
                    c.getReceivedGifts().add(g);
                    budget -= g.getPrice();
                    break; //go to the next category
                }
            }
        }
    }

    /**
     * @param c the Child object to be updated
     * @param newPref the Category array containing the new giftPreferences to be added
     */
    public void updatePreferences(final Child c, final ArrayList<Category> newPref) {
        ArrayList<Category> combinedPref = new ArrayList<>();
        for (Category category : newPref) {
            if (!combinedPref.contains(category)) {
                combinedPref.add(category);
            }
        }

        for (Category category : c.getGiftsPreferences()) {
            if (!combinedPref.contains(category)) {
                combinedPref.add(category);
            }
        }
        c.setGiftsPreferences(combinedPref);
    }

    /**
     * @param c the Child object to be updated
     */
    public void updateAge(final Child c) {
        c.setAge(c.getAge() + 1);
    }

    /**
     * @param c the Child object to be updated
     */
    public void resetReceivedGifts(final Child c) {
        c.getReceivedGifts().clear();
    }

    /**
     * @param src the initial Child object to be deep copied
     * @param dest the new Child object which will ce the clone of the initial one
     */
    public void deepCopy(final Child src, final Child dest) {
        dest.setId(src.getId());
        dest.setLastName(src.getLastName());
        dest.setFirstName(src.getFirstName());
        dest.setCity(src.getCity());
        dest.setAge(src.getAge());
        dest.setAverageScore(src.getAverageScore());
        dest.setAssignedBudget(src.getAssignedBudget());
        dest.getGiftsPreferences().addAll(src.getGiftsPreferences());
        dest.getNiceScoreHistory().addAll(src.getNiceScoreHistory());
        dest.getReceivedGifts().addAll(src.getReceivedGifts());
    }
}
