package sort;

import enums.Cities;
import pojo.Child;

import java.util.Comparator;
import java.util.LinkedHashMap;

    class ChildByCityComparator implements Comparator<Child> {
        private final LinkedHashMap<Cities, Double> sortOrder;

        public ChildByCityComparator(LinkedHashMap<Cities, Double> order) {
            this.sortOrder = order;
        }


        @Override
        public int compare(Child o1, Child o2) {
            Double cityScore = sortOrder.get(o1.getCity());
            if (cityScore == null)
            {
                throw new IllegalArgumentException("Bad city encountered: " +
                        o1.getCity());
            }
            Double cityScore2 = sortOrder.get(o1.getCity());
            if (cityScore2 == null)
            {
                throw new IllegalArgumentException("Bad city encountered: " +
                        o2.getCity());
            }
            if (cityScore == cityScore2) {
                return o2.getAverageScore().compareTo(o1.getAverageScore());
            }
            return cityScore2.compareTo(cityScore);
        }
    }
