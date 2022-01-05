package main;

import common.Constants;
import fileio.JArrayChild;
import fileio.JArrayRounds;
import fileio.PreChecker;
import fileio.Reader;
import fileio.Writer;
import pojo.AnnualChange;
import pojo.Child;
import pojo.Gift;
import pojo.Input;
import pojo.Round;
import service.ChildService;
import sort.Sort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to run the code
 */
public final class SingleTest {

    private SingleTest() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File outputDirectory = new File(Constants.RESULT_PATH);
        PreChecker.deleteFiles(outputDirectory.listFiles());

        String in = "tests/test7.json";
        String out = "output/test7.json";

        beSanta(in, out);

        //Checker.calculateScore(); //uncomment this line for checker
    }

    /**
     * @param inFile input file
     * @param outFile output file
     */
    public static void beSanta(final String inFile, final String outFile) throws IOException {
        File out = new File(outFile);
        Reader read = new Reader(inFile);
        Input in = Input.getInstance(); //the DB
        ChildService childService = ChildService.getInstance();
        JArrayRounds arrayRounds = new JArrayRounds();
        Writer writer = new Writer(outFile);

        read.readData(); //populate the DB

        Round roundZero = new Round();
        ArrayList<Child> kids = in.getInitialData().getChildren();
        ArrayList<Gift> gifts = in.getInitialData().getGifts();
        ArrayList<AnnualChange> changes = in.getAnnualChanges();
        double santaBudget = in.getInitialData().getSantaBudget();

        //start roundZero
        roundZero.eliminateYoungAdults(kids); // kick out young adults
        childService.updateMassHistory(kids); // update niceScoreHistory for each kid
        roundZero.calcAverageScore(kids);   // Calculate AverageScore for each kid
        roundZero.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit
        roundZero.calcAllocatedBudget(kids); //Calculated allocated budget for each kid
        Sort.sortGift(gifts); //Sort gift list
        roundZero.distributeGifts(kids, gifts); //distribute gifts to kids
        JArrayChild jArrayChild = new JArrayChild();
        jArrayChild.load(kids);
        writer.addToJSONArray(arrayRounds, jArrayChild); //add the results to the
                                                         // jsonArray

        for (AnnualChange change : changes) {
            santaBudget = change.getNewSantaBudget(); //update santaBudget
            roundZero.aYearHasPassed(kids); // everybody ages
            roundZero.eliminateYoungAdults(kids); // kick out young adults
            roundZero.resetReceivedGifts(kids);
            roundZero.roundHistoryUpdate(kids, change.getChildrenUpdates()); // update existing kids
            roundZero.addNewChildren(kids, change.getNewChildren()); // add new kids
            roundZero.eliminateYoungAdults(kids); // kick out young adults
            roundZero.calcAverageScore(kids);   // re-calculate AverageScore for each kid
            roundZero.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit
            roundZero.calcAllocatedBudget(kids); // re-calculated allocated budget for each kid
            roundZero.addNewGifts(gifts, change.getNewGifts()); // update gift list
            Sort.sortGift(gifts); // sort [updated] gift list
            roundZero.distributeGifts(kids, gifts); //distribute gifts to kids
            JArrayChild arrayChild = new JArrayChild();
            arrayChild.load(kids);
            writer.addToJSONArray(arrayRounds, arrayChild); //add the results to the
            // jsonArray
        }
        writer.writeRound(out, arrayRounds); //print results in JSON file
    }
}
