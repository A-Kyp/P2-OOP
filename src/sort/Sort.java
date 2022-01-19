package sort;

import enums.Cities;
import pojo.Child;
import pojo.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

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

    /**
     * Sort cities in asc lexicographic order
     * @param cities the cities list to be ordered
     */
    public static void sortCity(final ArrayList<Cities> cities) {
        cities.sort(Comparator.comparing(Enum::toString));
    }

    /**
     * Sort children in (asc) order by ID
     * @param children the children list to be sorted
     */
    public static void sortChildById(final ArrayList<Child> children) {
        children.sort(Comparator.comparing(Child::getId));
    }

    /**
     * Sort children in (desc) order by niceScore
     * @param children the children list to be sorted
     */
    public static void sortChildByNiceScore(final ArrayList<Child> children) {
        children.sort((o1, o2) -> {
            if (o1.getAverageScore().compareTo(o2.getAverageScore()) == 0) {
                return o1.getId().compareTo(o2.getId());
            }
            return o2.getAverageScore().compareTo(o1.getAverageScore());
        });
    }

    /**
     * <p>Sort children in 1. (desc) order by city score
     *                      2. (asc) order by ID</p>
     * @param children children list to be sorted
     * @param cities cities map
     */
    public static void sortChildByCity(final ArrayList<Child> children,
                                       final LinkedHashMap<Cities, Double> cities) {
        children.sort(new ChildByCityComparator(cities));
    }
}


