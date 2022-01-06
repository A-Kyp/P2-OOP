package strategy;

import pojo.Child;

import java.util.ArrayList;

public interface DistributionStrategy {
    public void arrange(ArrayList<Child> kids);
}
