package pojo;

import java.util.ArrayList;

public final class Input {
    private InitialData initialData = new InitialData();
    private ArrayList<AnnualChange> annualChanges = new ArrayList<>();
    private int numberOfYears;
    private static Input instance = null;

    private Input() { }

    /**
     * @return the Input instance for SINGLETON
     */
    public static Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public void setAnnualChanges(final ArrayList<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }
}
