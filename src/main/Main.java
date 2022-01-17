package main;

import checker.Checker;
import common.Constants;
import enums.Cities;
import fileio.*;
import pojo.*;
import service.ChildService;
import sort.Sort;
import strategy.DistributionStrategy;
import strategy.StrategyFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH); //path to the tests folder
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);
        PreChecker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String extension;
            String test = "test";
            int index = test.length();
            extension = file.getName().substring(index);
            String filepath = Constants.OUTPUT_PATH + extension;
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                beSanta(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param inFile input file
     * @param outFile output file
     */
    public static void beSanta(final String inFile, final String outFile) throws IOException {
        File out = new File(outFile);
        Reader read = new Reader(inFile);
        Input in = Input.getInstance(); //the DB
        JArrayRounds arrayRounds = new JArrayRounds(); // for writing the JSON file
        Writer writer = new Writer(outFile); // also, for writing the JSON file
        ChildService childService = ChildService.getInstance();

        read.readData(); //populate the DB

        Round roundZero = new Round();
        ArrayList<Child> kids = in.getInitialData().getChildren();
        ArrayList<Gift> gifts = in.getInitialData().getGifts();
        ArrayList<Cities> cities = in.getInitialData().getCities();
        ArrayList<AnnualChange> changes = in.getAnnualChanges();
        RoundPlayer player = new RoundPlayer(roundZero);

        //start roundZero
        double santaBudget = in.getInitialData().getSantaBudget();
//        roundZero.eliminateYoungAdults(kids); // kick out young adults if there are any
//        roundZero.eliminateDuplicatePreferences(kids);
//        childService.updateMassHistory(kids); // update niceScoreHistory for each kid
//        roundZero.calcAverageScore(kids);   // Calculate AverageScore for each kid
//        roundZero.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit
//        roundZero.calcAllocatedBudget(kids); //Calculated allocated budget for each kid
//        roundZero.elvesChangeBudget(kids); //->
//        Sort.sortGift(gifts); //Sort gift list
//        roundZero.distributeGifts(kids, gifts); //distribute gifts to kids
        player.initial(santaBudget, kids, gifts);

        JArrayChild jArrayChild = new JArrayChild();
        jArrayChild.load(kids); //save the result of the initial round
        writer.addToJSONArray(arrayRounds, jArrayChild); //add the results to the
                                                         // jsonArray

        //start annualChanges
        int counter = 1;
        for (AnnualChange change : changes) {
            DistributionStrategy strategy = StrategyFactory.createStrategy(change.getStrategy());
            santaBudget = change.getNewSantaBudget(); // update santaBudget
//            roundZero.aYearHasPassed(kids); // everybody ages exactly 1 year :)
//            roundZero.eliminateYoungAdults(kids); // kick out young adults
//            roundZero.resetReceivedGifts(kids);
//            roundZero.roundHistoryUpdate(kids, change.getChildrenUpdates()); // update existing kids
//            roundZero.addNewChildren(kids, change.getNewChildren(), cities); // add new kids
//            roundZero.eliminateYoungAdults(kids); // kick out young adults
//            roundZero.eliminateDuplicatePreferences(kids);
//            Sort.sortChildById(kids);
//            roundZero.calcAverageScore(kids);   // re-calculate AverageScore for each kid
//            roundZero.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit
//            roundZero.calcAllocatedBudget(kids); // re-calculated allocated budget for each kid
//            roundZero.elvesChangeBudget(kids); //->
//            roundZero.addNewGifts(gifts, change.getNewGifts()); // update gift list
//            Sort.sortGift(gifts); // sort [updated] gift list
//            strategy.arrange(kids); //->
//            roundZero.distributeGifts(kids, gifts); //distribute gifts to kids
//            roundZero.yellowElf(kids, gifts); //->
//            Sort.sortChildById(kids);//->

            player.normalRound(santaBudget, kids, gifts, change, cities, strategy);
            JArrayChild arrayChild = new JArrayChild();
            arrayChild.load(kids); //save the result of the initial round
            writer.addToJSONArray(arrayRounds, arrayChild); //add the results to the
            // jsonArray
            if (counter == in.getNumberOfYears()) {
                break; //for test7.json
            }
            counter++;
        }
        writer.writeRound(out, arrayRounds); //print results in JSON file
    }
}
