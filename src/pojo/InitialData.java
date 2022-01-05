package pojo;

import enums.Cities;

import java.util.ArrayList;

public final class InitialData {
    private Double santaBudget;
    private final ArrayList<Child> children = new ArrayList<>();
    private final ArrayList<Gift> gifts = new ArrayList<>();
    private ArrayList<Cities> cities = new ArrayList<>();

    public InitialData() { }
    public InitialData(final Double santaBudget, final ArrayList<Child> children,
                       final ArrayList<Gift> gifts, final ArrayList<Cities> cities) {
        this.santaBudget = santaBudget;
        this.children.addAll(children);
        this.gifts.addAll(gifts);
        this.cities = cities;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public ArrayList<Cities> getCities() {
        return cities;
    }
}
