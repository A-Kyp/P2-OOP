package fileio;

import com.fasterxml.jackson.annotation.JsonProperty;
import pojo.Child;
import service.ChildService;

import java.util.ArrayList;

public final class JArrayChild {
    @JsonProperty
    private final ArrayList<Child> children = new ArrayList<>();

    public ArrayList<Child> getChildren() {
        return children;
    }

    /**
     * @param kids the result of a round (an array of Child) that is to be deep copied
     *             as to keep changes from a round to another
     */
    public void load(final ArrayList<Child> kids) {
        ChildService childService = ChildService.getInstance();
        for (Child c : kids) {
            Child clone = new Child();
            childService.deepCopy(c, clone);
            children.add(clone);
        }
    }
}
