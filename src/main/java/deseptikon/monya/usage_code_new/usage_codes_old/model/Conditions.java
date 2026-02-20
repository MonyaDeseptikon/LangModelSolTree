package deseptikon.monya.usage_code_new.usage_codes_old.model;

import java.util.List;

public class Conditions {
    private List<String> tags;
    private Float moreThisArea;
    private Float lessThisArea;
    private List<String> excludeTags;
    private Float freeField;


    public Conditions(List<String> tags, List<String> excludeTags, Float moreThisArea,
                      Float lessThisArea, Float freeField) {
        this.tags = tags;
        this.moreThisArea = moreThisArea;
        this.lessThisArea = lessThisArea;
        this.excludeTags = excludeTags;
        this.freeField = freeField;

    }

    public Conditions() {

    }

    public List<String> getTags() {
        return tags;
    }

    public Float getMoreThisArea() {
        return moreThisArea;
    }

    public Float getLessThisArea() {
        return lessThisArea;
    }

    public List<String> getExcludeTags() {
        return excludeTags;
    }

    public Float getFreeField() {
        return freeField;
    }

}
