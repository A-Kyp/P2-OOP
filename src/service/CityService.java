package service;

import enums.Cities;
import pojo.Child;

import java.util.ArrayList;

public class CityService {
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

    public double calcCityScore(Cities city, ArrayList<Child> kids) {
        Double sum =0d;
        int matched = 0;
        for (Child c : kids) {
            if(c.getCity().compareTo(city) == 0) {
                sum += c.getAverageScore();
                matched ++;
            }
        }
        return sum / matched;
    }
}
