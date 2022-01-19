package strategy;

import pojo.Child;
import sort.Sort;

import java.util.ArrayList;

public final class NiceScoreStrategy implements DistributionStrategy {
    @Override
    public void arrange(final ArrayList<Child> kids) {
        Sort.sortChildByNiceScore(kids);
    }
}
