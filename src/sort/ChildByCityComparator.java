package sort;

import enums.Cities;
import pojo.Child;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

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
                Integer c1 = 0;
                Integer c2 = 0;
                int counter = 0;
                for(Map.Entry e : sortOrder.entrySet()) {
                    counter ++;
                    if(e.getKey().equals(o1.getCity())) {
                        c1 = counter;
                    }

                    if(e.getKey().equals(o2.getCity())) {
                        c2 = counter;
                    }
                }
                if(c1.equals(c2)) {
                    return o1.getId().compareTo(o2.getId());
                }
                return c1.compareTo(c2);
            }
            return cityScore2.compareTo(cityScore);
        }
    }
