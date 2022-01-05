package sort;

import pojo.Gift;

import java.util.ArrayList;

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
}
