package pojo;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public final class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private ArrayList<Category> giftsPreferences = new ArrayList<>();
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private Double assignedBudget;
    private final ArrayList<Gift> receivedGifts = new ArrayList<>();

    public Child() { }

    private Child(final ChildBuilder builder) {
        this.id = builder.id;
        this.averageScore = builder.avgNiceScore;
        this.giftsPreferences = builder.preferences;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.age = builder.age;
        this.city = builder.city;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Cities getCity() {
        return city;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public static final class ChildBuilder {
        private final Integer id;
        private Integer age;
        private Double avgNiceScore;
        private final ArrayList<Double> niceScoreHistory = new ArrayList<>();
        private String lastName;
        private String firstName;
        private Cities city;
        private  ArrayList<Category> preferences = new ArrayList<>();
        private Double allocatedBudget;
        private final ArrayList<Gift> receivedGifts = new ArrayList<>();

        public ChildBuilder(final Integer id) {
            this.id = id;
        }

        /**
         * @param lName lastName value
         * @return a ChildBuilderObject with a notNull value for the lastName field
         */
        public ChildBuilder withLastName(final String lName) {
            this.lastName = lName;
            return this;
        }

        /**
         * @param fName firstName value
         * @return a ChildBuilderObject with a notNull value for the firstName field
         */
        public ChildBuilder withFirstName(final String fName) {
            this.firstName = fName;
            return this;
        }

        /**
         * @param a age value
         * @return a ChildBuilderObject with a notNull value for the age field
         */
        public ChildBuilder withAge(final Integer a) {
            this.age = a;
            return this;
        }

        /**
         * @param c city value
         * @return a ChildBuilderObject with a notNull value for the city field
         */
        public ChildBuilder withCity(final Cities c) {
            this.city = c;
            return this;
        }

        /**
         * <p>
         * This method puts the read initial value of the nice score or updated niceScore value
         * in the averageNiceScore field. This value is later placed in the niceScoreHistory
         * array, and then replaced with the real (calculated) value of the averageNiceScore.
         * It is just an initial trick just for reading's sake.
         * </p>
         * @param niceScore averageNiceScore value
         * @return a ChildBuilderObject with a notNull value for the averageNiceScore field
         */
        public ChildBuilder withNiceScore(final Double niceScore) {
            this.avgNiceScore = niceScore;
            return this;
        }

        /**
         * @param pref giftPreference value
         * @return a ChildBuilderObject with a notNull value for the giftPreference field
         */
        public ChildBuilder withPreference(final ArrayList<Category> pref) {
            this.preferences = pref;
            return this;
        }

        /**
         * @return The built Child object
         */
        public Child build() {
            return new Child(this);
        }
    }
}
