package main;

import common.Constants;
import enums.Cities;
import fileio.out.JArrayChild;
import fileio.out.JArrayRounds;
import fileio.in.PreChecker;
import fileio.in.Reader;
import fileio.out.Writer;
import pojo.Child;
import pojo.Gift;
import pojo.Round;
import pojo.RoundPlayer;
import pojo.database.AnnualChange;
import pojo.database.Input;
import strategy.DistributionStrategy;
import strategy.StrategyFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);
        PreChecker.deleteFiles(outputDirectory.listFiles());

        //modify here for input/output files ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !
        String in = "tests/test22.json";
        String out = "output/out_22.json";

        beSanta(in, out);
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

        read.readData(); //populate the DB

        Round roundZero = new Round();
        ArrayList<Child> kids = in.getInitialData().getChildren();
        ArrayList<Gift> gifts = in.getInitialData().getGifts();
        ArrayList<Cities> cities = in.getInitialData().getCities();
        ArrayList<AnnualChange> changes = in.getAnnualChanges();
        RoundPlayer player = new RoundPlayer(roundZero);

        //================================== start roundZero =====================================
        double santaBudget = in.getInitialData().getSantaBudget();

        player.initial(santaBudget, kids, gifts);

        JArrayChild jArrayChild = new JArrayChild();
        jArrayChild.load(kids); //save the result of the initial round
        Writer.addToJSONArray(arrayRounds, jArrayChild); //add the results to the
                                                         // jsonArray

        //================================ play rounds ============================================
        for (AnnualChange change : changes) {
            DistributionStrategy strategy = StrategyFactory.createStrategy(change.getStrategy());
            santaBudget = change.getNewSantaBudget(); // update santaBudget

            player.normalRound(santaBudget, kids, gifts, change, cities, strategy);

            JArrayChild arrayChild = new JArrayChild();
            arrayChild.load(kids); //save the result of the initial round (P1)
            Writer.addToJSONArray(arrayRounds, arrayChild); //add the results to the
                                                            // jsonArray
        }
        Writer.writeRound(out, arrayRounds); //print results in JSON file
    }
}
