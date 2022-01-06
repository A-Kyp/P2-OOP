package strategy;

import pojo.Child;
import sort.Sort;

import java.util.ArrayList;

public class NiceScoreStrategy implements DistributionStrategy {
    @Override
    public void arrange(ArrayList<Child> kids) {
        Sort.sortChildByNiceScore(kids);
    }
}
