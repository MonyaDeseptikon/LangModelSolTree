package deseptikon.monya.parcels.usage_codes.model.uc;

import deseptikon.monya.parcels.usage_codes.model.Conditions;

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
