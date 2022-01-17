package strategy;

import enums.Cities;
import enums.CityStrategyEnum;
import pojo.Child;
import pojo.Input;
import service.CityService;
import sort.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CityStrategy implements DistributionStrategy {

    @Override
    public void arrange(ArrayList<Child> kids) {
        ArrayList<Cities> cities = Input.getInstance().getInitialData().getCities();
        Sort.sortCity(cities);
        CityService cityService = CityService.getInstance();
        LinkedHashMap<Cities, Double> ordCities = new LinkedHashMap<>();

        for (Cities city : cities) {
            ordCities.put(city, cityService.calcCityScore(city, kids));
        }

        Sort.sortChildByCity(kids, ordCities);
    }
}
