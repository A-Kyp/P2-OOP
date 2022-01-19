package pojo.database;

import enums.CityStrategyEnum;
import pojo.Child;
import pojo.Gift;

import java.util.ArrayList;

public final class AnnualChange {
    private final Double newSantaBudget;
    private final ArrayList<Child> newChildren = new ArrayList<>();
    private final ArrayList<Child> childrenUpdates = new ArrayList<>();
    private final ArrayList<Gift> newGifts = new ArrayList<>();
    private CityStrategyEnum strategy = null;

    public AnnualChange(final Double newSantaBudget, final ArrayList<Child> newChildren,
                        final ArrayList<Child> childrenUpdates, final ArrayList<Gift> newGifts,
                        final CityStrategyEnum cStrategy) {
        this.newSantaBudget = newSantaBudget;
        this.newChildren.addAll(newChildren);
        this.childrenUpdates.addAll(childrenUpdates);
        this.newGifts.addAll(newGifts);
        this.strategy = cStrategy;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public ArrayList<Child> getChildrenUpdates() {
        return childrenUpdates;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }
}
