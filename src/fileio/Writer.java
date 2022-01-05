package fileio;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class writes the output in files
 */
public final class Writer {
    /**
     * The file where the data will be written
     */
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * Transforms the output in a JSONObject
     *
     * @param out the file where the result will be written
     * @param array the array containing the results of each round
     * @throws IOException in case of exceptions to reading / writing
     */
    public void writeRound(final File out, final JArrayRounds array) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(out, array);
    }

    /**
     * @param arr the JArrayRounds instance that keeps the final result which is to be printed
     * @param arrayChild the JArrayChild that keep the result of a single round
     */
    public void addToJSONArray(final JArrayRounds arr, final JArrayChild arrayChild) {
        arr.getAnnualChildren().add(arrayChild);
    }
}

