package deseptikon.monya.spring_jdbc_parcels.jdbc;

import deseptikon.monya.spring_jdbc_parcels.model.Parcel;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public interface ParcelDAO {
    public void setDataSource(DataSource dataSource);

    List<Parcel> getListColumnName();

    List<Parcel> getListParcels();

    List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTagsWithICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    void updateParcels(Set<Integer> idList, String usageCode);

    List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode);
}
