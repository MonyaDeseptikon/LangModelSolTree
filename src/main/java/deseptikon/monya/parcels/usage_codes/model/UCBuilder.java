package deseptikon.monya.parcels.usage_codes.model;

import java.util.List;

public interface UCBuilder {
     List <String> excludeTagsTemplate();

    Conditions codeOnlyCondition();

    List<Conditions> conditionsList();

    String usageCode();

    boolean isEmptyInnerCN();

    String innerCNTableName();
}
