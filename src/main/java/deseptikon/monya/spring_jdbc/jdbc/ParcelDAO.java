package deseptikon.monya.spring_jdbc.jdbc;

import deseptikon.monya.spring_jdbc.model.Parcel;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public interface ParcelDAO {
    public void setDataSource(DataSource dataSource);

    public List<Parcel> getListParcels();

    public List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);
    public List<Parcel> getListParcelsByTagsWithICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    public void updateParcels(Set<Integer> idList, String usageCode);

    public List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode);
}
