package fileio;

import java.io.File;

public final class PreChecker {
    private PreChecker() { }

    /**
     * @param directory The name of the output directory.
     */
    public static void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                if (!file.delete()) {
                    System.out.println("nu s-a sters");
                }
            }
        }
    }
}
