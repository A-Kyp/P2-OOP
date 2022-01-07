package sort;

import enums.Cities;
import pojo.Child;
import pojo.Gift;
import service.CityService;

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

    public static void sortCity(LinkedHashMap<Cities, Double> cities) {
        // 1. get entrySet from LinkedHashMap object
        Set<Map.Entry<Cities, Double>> entrySet = cities.entrySet();

        // 2. convert LinkedHashMap to List of Map.Entry
        List<Map.Entry<Cities, Double>> entryList =
                new ArrayList<Map.Entry<Cities, Double>>(entrySet);

        // 3. sort list of entries using Collections class'
        // utility method sort(ls, cmptr)
        Collections.sort(entryList,
                new Comparator<Map.Entry<Cities, Double>>() {

                    @Override
                    public int compare(final Map.Entry<Cities, Double> o1,
                                       final Map.Entry<Cities, Double> o2) {
//                        if (o2.getValue().compareTo(o1.getValue()) == 0) {
//                            return o2.getKey().compareTo(o1.getKey());
//                        }
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

        /*4. iterating list and storing in LinkedHashMap*/
        LinkedHashMap<Cities, Double> sorted = new LinkedHashMap<>();
        for (Map.Entry<Cities, Double> map : entryList) {
            sorted.put(map.getKey(), map.getValue());
        }
        cities = sorted;
    }

    public static void sortChildByCity(final ArrayList<Child> children,
                                       final LinkedHashMap<Cities, Double> cities) {
        Collections.sort(children, new CityComparator(cities));
    }
}


