package util;

import common.Constants;
import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojo.Child;
import pojo.Gift;

import java.util.ArrayList;

public final class Utils {
    private Utils() { }

    /**
     * Transforms a string into an enum
     * @param preference for gift
     * @return a Category Enum
     */
    public static Category toCategory(final String preference) {
        return switch (preference) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * Transforms a string into an enum
     * @return a Cities Enum
     */
    public static Cities toCity(final String city) {
        return switch (city) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * Transforms an array of JSON's into an array of Category
     * @param array of JSONs
     * @return a list of Category enum
     */
    public static ArrayList<Category> convertJSONArrayCategory(final JSONArray array) {
        if (array != null) {
            ArrayList<Category> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add(toCategory((String) object));
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * Transforms an array of JSON's into an array of children
     * @param array of JSONs
     * @return a list of children
     */
    public static ArrayList<Child> convertJSONArrayChild(final JSONArray array) {
        if (array != null) {
            ArrayList<Child> finalArray = new ArrayList<>();
            for (Object object : array) {
                Child kid = new Child.ChildBuilder(
                    (int) (long) ((JSONObject) object).get(Constants.ID))
                    .withNiceScore((double) (long) ((JSONObject) object).get(Constants.NICE_SCORE))
                    .withPreference(Utils.convertJSONArrayCategory((JSONArray) ((JSONObject) object)
                        .get(Constants.GIFTS_PREFERENCES)))
                    .withLastName((String) ((JSONObject) object).get(Constants.LAST_NAME))
                    .withFirstName((String) ((JSONObject) object).get(Constants.FIRST_NAME))
                    .withAge((int) (long) ((JSONObject) object).get(Constants.AGE))
                    .withCity(Utils.toCity((String) ((JSONObject) object).get(Constants.CITY)))
                    .build();
                finalArray.add(kid);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * <p>
     * Transforms an array of JSON's into an array of children. It is very similar to the
     * convertJSONArrayChild method, the difference being the notNull fields of the returned
     * Child array - this method returns children that have notNull values only for the fields
     * that are to be updated.
     * </p>
     * @param array of JSON
     * @return a list of Children
     */
    public static ArrayList<Child> convertJSONArrayChildUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<Child> finalArray = new ArrayList<>();
            for (Object object : array) {
                Object score = ((JSONObject) object).get(Constants.NICE_SCORE);
                Object giftPreference = ((JSONObject) object).get(Constants.GIFTS_PREFERENCES);
                if (score != null && giftPreference != null) {
                    Child kid = new Child.ChildBuilder(
                         (int) (long) ((JSONObject) object).get(Constants.ID))
                         .withNiceScore((double) (long) score)
                         .withPreference(Utils.convertJSONArrayCategory((JSONArray) giftPreference))
                         .build();
                    finalArray.add(kid);
                } else if (score == null) {
                    Child kid = new Child.ChildBuilder(
                         (int) (long) ((JSONObject) object).get(Constants.ID))
                         .withPreference(Utils.convertJSONArrayCategory((JSONArray) giftPreference))
                         .build();
                    finalArray.add(kid);
                } else {
                    Child kid = new Child.ChildBuilder(
                            (int) (long) ((JSONObject) object).get(Constants.ID))
                            .withNiceScore((double) (long) score)
                            .build();
                    finalArray.add(kid);
                }
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * Transforms an array of JSON's into an array of gift
     * @param array of JSONs
     * @return a list of gift
     */
    public static ArrayList<Gift> convertJSONArrayGift(final JSONArray array) {
        if (array != null) {
            ArrayList<Gift> finalArray = new ArrayList<>();
            for (Object toy : array) {
                Gift gift = new Gift(
                        (String) ((JSONObject) toy).get(Constants.PRODUCT_NAME),
                        (double) (long) ((JSONObject) toy).get(Constants.PRICE),
                        Utils.toCategory((String) ((JSONObject) toy).get(Constants.CATEGORY)));
                finalArray.add(gift);
            }
            return finalArray;
        } else {
            return null;
        }
    }
}
