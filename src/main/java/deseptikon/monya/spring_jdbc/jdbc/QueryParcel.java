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

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;


    @Override
    public void setDataSource(DataSource dataSource) {
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

    @Override
    public List<Parcel> getListParcelsByTags(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea) {

        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(utilization_by_doc) NOT REGEXP ? AND " +
                "area >= ? AND " +
                "area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }

    @Override
    public List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea) {
        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(utilization_by_doc) NOT REGEXP ? AND " +
                "inner_cadastral_numbers = '' AND " +
                "area >= ? AND " +
                "area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }

    @Override
    public List<Parcel> getListParcelsByTagsWithICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea) {
        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(utilization_by_doc) NOT REGEXP ? AND " +
                "inner_cadastral_numbers != '' AND " +
                "area >= ? AND " +
                "area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);

    }

}
