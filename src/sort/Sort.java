package sort;

import enums.Cities;
import pojo.Child;
import pojo.Gift;

import java.util.*;

public final class Sort {
    private Sort() { }

    /**
     * @param gifts the Gift array to be sorted by:
     *              1. category (asc)
     *              2. price (asc)
     */
    public static void sortGift(final ArrayList<Gift> gifts) {
        gifts.sort((o1, o2) -> {
            if (o1.getCategory().compareTo(o2.getCategory()) == 0) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
            return o1.getCategory().compareTo(o2.getCategory());
        });
    }

    public static void sortCity(ArrayList<Cities> cities) {
        cities.sort(new Comparator<Cities>() {
            @Override
            public int compare(Cities o1, Cities o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
    }

    public static void sortChildById(final ArrayList<Child> children) {
        children.sort(Comparator.comparing(Child::getId));
    }

    public static void sortChildByNiceScore(final ArrayList<Child> children) {
        children.sort((o1, o2) -> {
            if (o1.getAverageScore().compareTo(o2.getAverageScore()) == 0) {
                return o1.getId().compareTo(o2.getId());
            }
            return o2.getAverageScore().compareTo(o1.getAverageScore());
        });
    }

    public static void sortChildByCity(final ArrayList<Child> children,
                                       final LinkedHashMap<Cities, Double> cities) {
        children.sort(new ChildByCityComparator(cities));
    }
}


