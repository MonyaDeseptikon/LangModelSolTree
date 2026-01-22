package deseptikon.monya.parcels.usage_codes.model;

import java.util.List;

public interface UCBuilder {
     List <String> setExcludeTagsTemplate ();

    Conditions setCodeOnly();

    List<Conditions> setConditionsList();

    String setUsageCode();

    boolean setIsEmptyInnerCN();

    String setInnerCNTableName();
}
