package pojo;

import java.util.ArrayList;

public final class AnnualChange {
    private final Double newSantaBudget;
    private final ArrayList<Child> newChildren = new ArrayList<>();
    private final ArrayList<Child> childrenUpdates = new ArrayList<>();
    private final ArrayList<Gift> newGifts = new ArrayList<>();

    public AnnualChange(final Double newSantaBudget, final ArrayList<Child> newChildren,
                        final ArrayList<Child> childrenUpdates, final ArrayList<Gift> newGifts) {
        this.newSantaBudget = newSantaBudget;
        this.newChildren.addAll(newChildren);
        this.childrenUpdates.addAll(childrenUpdates);
        this.newGifts.addAll(newGifts);
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
}
