package deseptikon.monya.spring_jdbc_parcels.jdbc;

import deseptikon.monya.spring_jdbc_parcels.model.Parcel;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public interface ParcelDAO {
    void setDataSource(DataSource dataSource);

    List<String> getListColumnName();

    List<Parcel> getListParcels();

    List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTagsWithICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);

    List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea);


}
