package deseptikon.monya.configuration.spring_jdbc.jdbc.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.List;

public interface GetParcelBigResultset {
    void concatParcelsPredictedUsageCode(List<Parcel> parcels);

    List<Parcel> getListParcelsByCodeBigResultset(StringBuilder tags, StringBuilder excludeTags);
    List<Parcel> getListParcelsByTagsNewWithCNInnerJoinBigResultset(StringBuilder tags, StringBuilder excludeTags, List<String> usageCodeBuildingsMustBe);
    List<Parcel> getListParcelsByTagsNewWithoutCNBigResultset(StringBuilder tags, StringBuilder excludeTags);


}
