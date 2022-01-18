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
            if (o1.getCity().compareTo(o2.getCity()) == 0) {
//                if (o1.getAverageScore().compareTo(o2.getAverageScore()) == 0) {
//                    return o1.getId().compareTo(o2.getId());
//                }
                return o1.getId().compareTo(o2.getId());
            }

            Double cityScore = sortOrder.get(o1.getCity());
            if (cityScore == null)
            {
                throw new IllegalArgumentException("Bad city encountered: " +
                        o1.getCity());
            }
            Double cityScore2 = sortOrder.get(o2.getCity());
            if (cityScore2 == null)
            {
                throw new IllegalArgumentException("Bad city encountered: " +
                        o2.getCity());
            }
            if (cityScore.equals(cityScore2)) {
                Integer c1 = 0;
                int c2 = 0;
                int counter = 0;
                for(Map.Entry<Cities, Double> e : sortOrder.entrySet()) {
                    counter ++;
                    if(e.getKey().equals(o1.getCity())) {
                        c1 = counter;
                    }

                    if(e.getKey().equals(o2.getCity())) {
                        c2 = counter;
                    }
                }
                return c1.compareTo(c2);
            }
            return cityScore2.compareTo(cityScore);
        }
    }
