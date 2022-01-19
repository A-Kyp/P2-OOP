package fileio.out;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import pojo.Child;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class writes the output in files
 */
public final class Writer {
    private Writer() { }

    /**
     * Transforms the output in a JSONObject
     *
     * @param out the file where the result will be written
     * @param array the array containing the results of each round
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void writeRound(final File out, final JArrayRounds array) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(out, array);
    }

    /**
     * @param arr the JArrayRounds instance that keeps the final result which is to be printed
     * @param arrayChild the JArrayChild that keep the result of a single round
     */
    public static void addToJSONArray(final JArrayRounds arr, final JArrayChild arrayChild) {
        arr.getAnnualChildren().add(arrayChild);
    }

    /**
     * <p>Make a deep copy of the children list in a JArrayChild object to make current round
     * changes persist permanently. Then put the loaded JArrayChild into the array that stores
     * the final result.</p>
     * @param kids the result of a round
     * @param arrayRounds the array of arrays that keeps the final result that is to be printed
     *                    to the json file
     */
    public static void writeToArray(final ArrayList<Child> kids, final JArrayRounds arrayRounds) {
        JArrayChild arrayChild = new JArrayChild();
        arrayChild.load(kids); //save the result of the initial round
        Writer.addToJSONArray(arrayRounds, arrayChild); //add the results to the jsonArray
    }
}

