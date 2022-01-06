package strategy;

import pojo.Child;
import sort.Sort;

import java.util.ArrayList;

public class IdStrategy implements DistributionStrategy {
    @Override
    public void arrange(ArrayList<Child> kids) {
        Sort.sortChildById(kids);
    }
}
