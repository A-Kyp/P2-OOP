package strategy;

import enums.Cities;
import pojo.Child;
import pojo.database.Input;
import service.CityService;
import sort.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CityStrategy implements DistributionStrategy {

    @Override
    public void arrange(ArrayList<Child> kids) {
        ArrayList<Cities> cities = Input.getInstance().getInitialData().getCities();
        Sort.sortCity(cities);
        CityService cityService = CityService.getInstance();
        LinkedHashMap<Cities, Double> ordCities = new LinkedHashMap<>();

        for (Cities city : cities) {
            Double cScore = cityService.calcCityScore(city, kids);
            if(cScore != 0d)
            ordCities.put(city, cScore);
        }

        Sort.sortChildByCity(kids, ordCities);

        ordCities.forEach((key, value) -> System.out.println(key + "|" + value + "\t"));

        for(Child c : kids) {
            System.out.println(c);
        }
    }
}
