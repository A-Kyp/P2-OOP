package strategy;

import pojo.Child;
import sort.Sort;

import java.util.ArrayList;

public final class IdStrategy implements DistributionStrategy {
    @Override
    public void arrange(final ArrayList<Child> kids) {
        Sort.sortChildById(kids);
    }
}
