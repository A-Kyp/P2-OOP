package sort;

import enums.Cities;
import pojo.Child;

import java.util.Comparator;
import java.util.LinkedHashMap;

    class CityComparator implements Comparator<Child> {
        private LinkedHashMap<Cities, Double> sortOrder;

        public CityComparator(LinkedHashMap<Cities, Double> order) {
            this.sortOrder = order;
        }


        @Override
        public int compare(Child o1, Child o2) {
            Double cityPos = sortOrder.get(o1.getCity());
            if (cityPos == null)
            {
                throw new IllegalArgumentException("Bad city encountered: " +
                        o1.getCity());
            }
            Double cityPos2 = sortOrder.get(o1.getCity());
            if (cityPos2 == null)
            {
                throw new IllegalArgumentException("Bad city encountered: " +
                        o2.getCity());
            }
            return cityPos.compareTo(cityPos2);
        }
    }
