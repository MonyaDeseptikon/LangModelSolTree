package deseptikon.monya.usage_code_new.usage_codes_old.model.uc;

import deseptikon.monya.usage_code_new.usage_codes_old.model.Conditions;

import java.util.List;

public interface UCBuilder {
     List <String> excludeTagsTemplate();

    Conditions codeOnlyCondition();

    List<Conditions> conditionsList();

    String usageCode();

    boolean isEmptyInnerCN();

    String innerCNTableName();

    List<String> usageCodeBuildingsMustBe();

}
