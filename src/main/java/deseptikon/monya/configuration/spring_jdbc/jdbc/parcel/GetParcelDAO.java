package deseptikon.monya.configuration.spring_jdbc.jdbc.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.List;

public interface GetParcelDAO {

    List<String> getListColumnName();

    List<Parcel> getListParcels();

    List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTagsWithICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsForReplaceLatin(List<String> columnsName);

    List<Parcel> getListParcelsByTagsJoinICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName);

    List<Parcel> getListParcelsByTagsJoinListICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName, List<String> usageCodeBuildings);

    List<Parcel> getListParcelsByTagsJoinListICN(StringBuilder tags, StringBuilder excludeTags, String innerCNTableName, List<String> usageCodeBuildings);
//    List<Parcel> getListParcelsByTagsKLADREachColumn(StringBuilder district, StringBuilder city, StringBuilder typeLocality, StringBuilder locality, StringBuilder typeStreet, StringBuilder street);
    List<Parcel> getListParcelsByTagsKLADRNote(StringBuilder district, StringBuilder typeCity, StringBuilder city, StringBuilder typeLocality, StringBuilder locality, StringBuilder typeStreet, StringBuilder street);

}
