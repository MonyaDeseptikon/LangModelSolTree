package deseptikon.monya.usage_codes;

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

    protected Tags(List<String> utilizationByDoc, Boolean innerCadastralNumbersIsExist, BigDecimal moreThisArea,
                   BigDecimal lessThisArea, List<String> notUtilizationByDoc, BigDecimal shareInnerCadastralNumbersArea) {
        this.utilizationByDoc = utilizationByDoc;
        this.innerCadastralNumbersIsExist = innerCadastralNumbersIsExist;
        this.moreThisArea = moreThisArea;
        this.lessThisArea = lessThisArea;
        this.notUtilizationByDoc = notUtilizationByDoc;
        this.shareInnerCadastralNumbersArea = shareInnerCadastralNumbersArea;
        this.utilizationByDocLen = utilizationByDoc.stream().mapToInt(tag->tag.length()).sum();
    }


}
