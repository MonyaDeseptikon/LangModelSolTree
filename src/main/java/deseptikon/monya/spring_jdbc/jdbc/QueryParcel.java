package deseptikon.monya.spring_jdbc.jdbc;

import deseptikon.monya.spring_jdbc.model.Parcel;
import deseptikon.monya.spring_jdbc.util.ParcelMapperPredicted;
import deseptikon.monya.spring_jdbc.util.ParcelMapperSimple;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public class QueryParcel implements ParcelDAO {
        private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;


    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

    @Override
    public List<Parcel> getListParcels() {
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperSimple());
    }

    @Override
    public void updateParcels(Set<Integer> idList, String predicatedUsageCode) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("predicatedUsageCode", predicatedUsageCode);
        parameters.addValue("idList", idList);

        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET PREDICTED_USAGE_CODE = :predicatedUsageCode WHERE id IN (:idList)";
        template.update(SQLUpdate, parameters);
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
