package deseptikon.monya.parcels.spring_jdbc.jdbc.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;

import javax.sql.DataSource;
import java.util.List;

public interface GetParcelDAO {

    List<String> getListColumnName();

    List<Parcel> getListParcels();

    List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTagsWithICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsForReplaceLatin(List<String> columnsName);

    List<Parcel> getListParcelsByTagsJoinICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName);

    List<Parcel> getListParcelsByTagsJoinListICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName, List <String> usageCodeBuildings);

}
