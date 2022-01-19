package strategy;

import pojo.Child;

import java.util.ArrayList;

public interface DistributionStrategy {
    /**
     * This methods sorts the children based on the current round's strategy
     * @param kids children to be sorted
     */
    void arrange(ArrayList<Child> kids);
}
