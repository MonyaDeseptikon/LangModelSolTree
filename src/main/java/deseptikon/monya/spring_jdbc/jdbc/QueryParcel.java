package deseptikon.monya.spring_jdbc.jdbc;

import deseptikon.monya.spring_jdbc.model.Parcel;
import deseptikon.monya.spring_jdbc.util.ParcelMapperPredicted;
import deseptikon.monya.spring_jdbc.util.ParcelMapperSimple;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class QueryParcel implements ParcelDAO {
    //    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Override
    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public List<Parcel> getListParcels() {
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperSimple());
    }

    @Override
    public void updateParcels(Integer id, String predicatedUsageCode) {

        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET PREDICTED_USAGE_CODE = ? WHERE id = ?";
        jdbcTemplate.update(SQLUpdate, predicatedUsageCode, id);
    }


    public List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, String innerCN, Float moreArea, Float lessArea) {
        if (excludeTags.isEmpty()) {
            Object[] arg = new Object[]{tags, innerCN, moreArea, lessArea};
            String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                    "inner_cadastral_numbers = ? AND " +
                    "area >= ? AND " +
                    "area <= ?";
            return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
        } else {
            Object[] arg = new Object[]{tags, excludeTags, innerCN, moreArea, lessArea};
            String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                    "LOWER(utilization_by_doc) NOT REGEXP ? AND " +
                    "inner_cadastral_numbers = ? AND " +
                    "area >= ? AND " +
                    "area <= ?";
            return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
        }
    }

    public List<Parcel> getListParcelsByICN(String innerCN) {
        Object[] arg = new Object[]{innerCN};
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 WHERE " +
                "inner_cadastral_numbers = ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }

}
