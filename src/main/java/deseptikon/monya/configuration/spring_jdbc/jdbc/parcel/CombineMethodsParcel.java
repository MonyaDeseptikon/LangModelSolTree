package deseptikon.monya.configuration.spring_jdbc.jdbc.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.List;

public interface CombineMethodsParcel {
    List<Parcel> getListParcelsByTagsInnerCNCondition(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isEmptyInnerCN);
    List<Parcel> getListParcelsByTagsKLADRInnerJoin();
}
