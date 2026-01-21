package deseptikon.monya.parcels.spring_jdbc.jdbc.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;

import java.util.List;

public interface CombineMethodsParcel {
    List<Parcel> getListParcelsByTagsInnerCNCondition(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isEmptyInnerCN);
}
