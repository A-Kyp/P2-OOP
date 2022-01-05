package pojo;

import common.Constants;
import service.ChildService;
import service.RoundService;

import java.util.ArrayList;
import java.util.Objects;

public final class Round {
    private Double budgetUnit = 0d;
    private final RoundService roundService = RoundService.getInstance();
    private final ChildService childService = ChildService.getInstance();

    /**
     * Calculate average niceScore for each child
     * @param kids the Child array containing the children
     */
    public void calcAverageScore(final ArrayList<Child> kids) {
        for (Child c : kids) {
            childService.updateAvgScore(c);
        }
    }

    /**
     * Calculate budgetUnit
     * @param santaBudget the current round available santaBudget
     * @param kids the Child array containing the eligible children
     */
    public void calcBudgetUnit(final Double santaBudget, final ArrayList<Child> kids) {
        budgetUnit = santaBudget / roundService.sumAvg(kids);
    }

    /**
     * calculate assigned budget for each kid
     * @param kids the Child array containing the eligible children
     */
    public void calcAllocatedBudget(final ArrayList<Child> kids) {
        for (Child c : kids) {
            childService.updateAllocatedBudget(c, budgetUnit);
        }
    }

    /**
     * distribute gifts to children
     * @param kids the Child array containing the eligible children
     * @param gifts the Gift array containing the available gifts
     */
    public void distributeGifts(final ArrayList<Child> kids, final ArrayList<Gift> gifts) {
        for (Child c : kids) {
            childService.allocateGift(c, gifts);
        }
    }

    /**
     * annual change -> add new kids to the list of children
     * @param initial the initial array containing already existent children
     * @param newKids the array containing children to be added to the initial array
     */
    public void addNewChildren(final ArrayList<Child> initial, final ArrayList<Child> newKids) {
        if (newKids != null) {
            childService.updateMassHistory(newKids);
            initial.addAll(newKids);
        }
    }

    /**
     * annual change -> update existing kids info
     * @param initial the initial Child array containing already existent children
     * @param updates the Child array containing childrenUpdates
     */
    public void roundHistoryUpdate(final ArrayList<Child> initial, final ArrayList<Child> updates) {
        for (Child update : updates) {
            for (Child c : initial) {
                if (Objects.equals(c.getId(), update.getId())) {
                    if (update.getAverageScore() != null) {
                        c.getNiceScoreHistory().add(update.getAverageScore());
                    }
                    if (update.getGiftsPreferences() != null) {
                        childService.updatePreferences(c, update.getGiftsPreferences());
                    }
                }
            }
        }
    }

    /**
     * annual change -> update gift list
     * @param initial the initial Gift array containing already available gift
     * @param newGift the Gift array containing gifts to be added to the initial array
     */
    public void addNewGifts(final ArrayList<Gift> initial, final ArrayList<Gift> newGift) {
        if (newGift != null) {
            initial.addAll(newGift);
        }

    }

    /**
     * annual change -> everybody ages!! A year has passed!
     * @param kids the Child array containing all the existent children
     */
    public void aYearHasPassed(final ArrayList<Child> kids) {
        for (Child c : kids) {
            childService.updateAge(c);
        }
    }

    /**
     * annual change -> eliminate young adults
     * @param kids the Child array containing all the existent children, including potential
     *             young Adults
     */
    public void eliminateYoungAdults(final ArrayList<Child> kids) {
        kids.removeIf(c -> c.getAge() > Constants.TEEN_AGE);
    }

    /**
     * annual change -> reset receivedGifts
     * @param kids the Child array containing all the existent children
     */
    public void resetReceivedGifts(final ArrayList<Child> kids) {
        for (Child c : kids) {
            childService.resetReceivedGifts(c);
        }
    }
}
