package fileio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public final class JArrayRounds {
    @JsonProperty
    private final ArrayList<JArrayChild> annualChildren = new ArrayList<>();

    public ArrayList<JArrayChild> getAnnualChildren() {
        return annualChildren;
    }
}
