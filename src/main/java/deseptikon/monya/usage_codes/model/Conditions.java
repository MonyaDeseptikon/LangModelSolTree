package deseptikon.monya.usage_codes.model;

import java.util.List;

public class Conditions {
    private List<String> tags;
    private Float moreThisArea;
    private Float lessThisArea;
    private List<String> excludeTags;
    private Float shareInnerCadastralNumbersArea;
    private int utilizationByDocLen;

    public Conditions(List<String> tags, List<String> excludeTags, Float moreThisArea,
                      Float lessThisArea, Float shareInnerCadastralNumbersArea) {
        this.tags = tags;
        this.moreThisArea = moreThisArea;
        this.lessThisArea = lessThisArea;
        this.excludeTags = excludeTags;
        this.shareInnerCadastralNumbersArea = shareInnerCadastralNumbersArea;
        this.utilizationByDocLen = tags.stream().mapToInt(tag->tag.length()).sum();
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

    public Float getShareInnerCadastralNumbersArea() {
        return shareInnerCadastralNumbersArea;
    }

    public int getUtilizationByDocLen() {
        return utilizationByDocLen;
    }
}
