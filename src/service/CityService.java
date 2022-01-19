package service;

import enums.Cities;
import pojo.Child;

import java.util.ArrayList;

public final class CityService {
    private static CityService instance;

    private CityService() { }

    /**
     * @return the ChildInstance for SINGLETON
     */
    public static CityService getInstance() {
        if (instance == null) {
            instance = new CityService();
        }
        return instance;
    }

    /**
     * @param city the list containing all the eligible children's cities
     * @param kids the eligible children
     * @return the city's score average
     */
    public Double calcCityScore(final Cities city, final ArrayList<Child> kids) {
        Double sum = 0d;
        int matched = 0;
        for (Child c : kids) {
            if (c.getCity().compareTo(city) == 0) {
                sum += c.getAverageScore();
                matched++;
            }
        }
        return sum / matched;
    }
}
