package strategy;

import enums.Cities;
import enums.CityStrategyEnum;
import pojo.Child;
import pojo.Input;
import service.CityService;
import sort.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CityStrategy implements DistributionStrategy {

    @Override
    public void arrange(ArrayList<Child> kids) {
        ArrayList<Cities> cities = Input.getInstance().getInitialData().getCities();
        CityService cityService = CityService.getInstance();
        LinkedHashMap<Cities, Double> ordCities = new LinkedHashMap<>();

        for (Cities city : cities) {
            ordCities.put(city, cityService.calcCityScore(city, kids));
        }

        Sort.sortCity(ordCities);

        Sort.sortChildByCity(kids, ordCities);
    }
}
