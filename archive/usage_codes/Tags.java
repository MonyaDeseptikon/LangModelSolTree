package deseptikon.monya.parcels.usage_codes;

import java.math.BigDecimal;
import java.util.List;

public class Tags {
    List<String> utilizationByDoc;
    Boolean innerCadastralNumbersIsExist;
    BigDecimal moreThisArea;
    BigDecimal lessThisArea;
    List<String> notUtilizationByDoc;
    BigDecimal shareInnerCadastralNumbersArea;
    int utilizationByDocLen;

    public Tags(List<String> utilizationByDoc, List<String> notUtilizationByDoc, Boolean innerCadastralNumbersIsExist, BigDecimal moreThisArea,
                BigDecimal lessThisArea, BigDecimal shareInnerCadastralNumbersArea) {
        this.utilizationByDoc = utilizationByDoc;
        this.innerCadastralNumbersIsExist = innerCadastralNumbersIsExist;
        this.moreThisArea = moreThisArea;
        this.lessThisArea = lessThisArea;
        this.notUtilizationByDoc = notUtilizationByDoc;
        this.shareInnerCadastralNumbersArea = shareInnerCadastralNumbersArea;
        this.utilizationByDocLen = utilizationByDoc.stream().mapToInt(tag->tag.length()).sum();
    }

    protected Tags() {
    }

    public List<String> getUtilizationByDoc() {
        return utilizationByDoc;
    }

    public Boolean getInnerCadastralNumbersIsExist() {
        return innerCadastralNumbersIsExist;
    }

    public BigDecimal getMoreThisArea() {
        return moreThisArea;
    }

    public BigDecimal getLessThisArea() {
        return lessThisArea;
    }

    public List<String> getNotUtilizationByDoc() {
        return notUtilizationByDoc;
    }

    public BigDecimal getShareInnerCadastralNumbersArea() {
        return shareInnerCadastralNumbersArea;
    }

    public int getUtilizationByDocLen() {
        return utilizationByDocLen;
    }
}
