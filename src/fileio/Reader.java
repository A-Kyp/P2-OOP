package fileio;

import common.Constants;
import enums.Cities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pojo.AnnualChange;
import pojo.Child;
import pojo.Gift;
import pojo.InitialData;
import pojo.Input;
import util.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Reader {
    private final JSONParser jp = new JSONParser();
    private final ArrayList<AnnualChange> annualChanges = new ArrayList<>();
    private final ArrayList<Child> children = new ArrayList<>();
    private final ArrayList<Gift> gifts = new ArrayList<>();
    private final ArrayList<Cities> cities = new ArrayList<>();
    private final String inputPath;

    /**
     * @param inputPath  The path to the input file
     */
    public Reader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * This method represents the reading process
     */
    public void readData() {
        try {
            // Parsing the contents of the JSON file
            JSONObject jObj = (JSONObject) jp.parse(new FileReader(inputPath));
            int jNumberOfYears = (int) ((long) jObj.get(Constants.NUMBER_OF_YEARS));
            double jSantaBudget = (double) (long) jObj.get(Constants.SANTA_BUDGET);
            JSONObject jIData = (JSONObject) jObj.get(Constants.INITIAL_DATA);
            JSONArray jChildren = (JSONArray) jIData.get(Constants.CHILDREN);
            JSONArray jGift = (JSONArray) jIData.get(Constants.SANTA_GIFT_LIST);
            JSONArray jAnnualChanges = (JSONArray) jObj.get(Constants.ANNUAL_CHANGES);

            if (jChildren != null) {
                for (Object kid : jChildren) {
                    children.add(new Child.ChildBuilder(
                      (int) (long) ((JSONObject) kid).get(Constants.ID))
                      .withNiceScore((double) (long) ((JSONObject) kid).get(Constants.NICE_SCORE))
                      .withPreference(Utils.convertJSONArrayCategory((JSONArray) ((JSONObject) kid)
                        .get(Constants.GIFTS_PREFERENCES)))
                      .withLastName((String) ((JSONObject) kid).get(Constants.LAST_NAME))
                      .withFirstName((String) ((JSONObject) kid).get(Constants.FIRST_NAME))
                      .withAge((int) (long) ((JSONObject) kid).get(Constants.AGE))
                      .withCity(Utils.toCity((String) ((JSONObject) kid).get(Constants.CITY)))
                      .build());
                }
            }

            if (jGift != null) {
                for (Object toy : jGift) {
                    gifts.add(new Gift((String) ((JSONObject) toy).get(Constants.PRODUCT_NAME),
                            (double) (long) ((JSONObject) toy).get(Constants.PRICE),
                            Utils.toCategory((String) ((JSONObject) toy).get(Constants.CATEGORY))));
                }
            }

            for (Child kid : children) {
                cities.add(kid.getCity());
            }

            InitialData iData = new InitialData(jSantaBudget, children, gifts, cities);

            for (Object change : jAnnualChanges) {
                annualChanges.add(new AnnualChange(
                        (double) (long) ((JSONObject) change).get(Constants.NEW_SANTA_BUDGET),
                        Utils.convertJSONArrayChild((JSONArray) (((JSONObject) change)
                                .get(Constants.NEW_CHILDREN))),
                        Utils.convertJSONArrayChildUpdate((JSONArray) (((JSONObject) change)
                                .get(Constants.CHILDREN_UPDATES))),
                        Utils.convertJSONArrayGift((JSONArray) (((JSONObject) change)
                                .get(Constants.NEW_GIFTS)))));
            }

            Input in = Input.getInstance(); //create the database (DB)
            in.setAnnualChanges(annualChanges); //populate the DB
            in.setInitialData(iData);
            in.setNumberOfYears(jNumberOfYears);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
