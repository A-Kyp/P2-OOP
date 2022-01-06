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
        double sum =0d;
        for (Child c : kids) {
            if(c.getCity().compareTo(city) == 0) {
                sum += c.getAverageScore();
            }
        }
        return sum / kids.size();
    }
}
