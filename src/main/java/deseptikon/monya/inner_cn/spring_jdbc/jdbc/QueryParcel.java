package deseptikon.monya.inner_cn.spring_jdbc.jdbc;

import deseptikon.monya.inner_cn.spring_jdbc.model.Parcel;
import deseptikon.monya.inner_cn.spring_jdbc.util.ParcelMapperPredicted;
import deseptikon.monya.inner_cn.spring_jdbc.util.ParcelMapperPredictedColName;
import deseptikon.monya.inner_cn.spring_jdbc.util.ParcelMapperReplaceLatin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public class QueryParcel implements GetParcelDAO, UpdateParcelDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;


    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

    @Override
    public List<String> getListColumnName() {
        String SQLQuery = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PRIVISIONAL_2026'";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredictedColName());
    }

    @Override
    public List<Parcel> getListParcels() {
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted());
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


    @Override
    public void updatePredictedUC(Set<Integer> idList, String predictedUsageCode) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("predictedUsageCode", predictedUsageCode);
        parameters.addValue("idList", idList);
        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET PREDICTED_USAGE_CODE = :predictedUsageCode WHERE id IN (:idList)";
        template.update(SQLUpdate, parameters);
    }

    @Override
    public void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("predicatedUsageCode", predicatedUsageCode);
        parameters.addValue("idList", idList);
        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET PREDICTED_USAGE_CODE = " +
                "CASE " +
                "WHEN PREDICTED_USAGE_CODE = '' " +
                "THEN :predicatedUsageCode " +
                "ELSE CONCAT(PREDICTED_USAGE_CODE, '; ', :predicatedUsageCode) " +
                "END " +
                "WHERE id IN (:idList)";
        template.update(SQLUpdate, parameters);
    }

    @Override
    public List<Parcel> getListParcelsForReplaceLatin(List<String> columnsName) {
        String colNameString = String.join(", ", columnsName);

        String SQLQuery = "SELECT " +
                colNameString + " " +
                "FROM PARCELS.PRIVISIONAL_2026";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperReplaceLatin());
    }

    @Override
    public void updateParcelsForReplaceLatin(final List<Parcel> parcels) {
//        List<ArrayList<Object>> parameters = new ArrayList<>(List.of());
//
//        parameters.addAll(parcels.stream().map(parcel -> new ArrayList<Object>(List.of(parcel.getUtilizationByDoc(), parcel.getCategory(), parcel.getNote(), parcel.getLocality(), parcel.getId()))).toList());


//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("idList", parcels.stream().map(p-> p.getId()).toList());
//        parameters.addValue("utilization_by_docList", parcels.stream().map(p-> p.getUtilizationByDoc()).toList());
//        parameters.addValue("categoryList", parcels.stream().map(p-> p.getCategory()).toList());
//        parameters.addValue("noteList", parcels.stream().map(p-> p.getNote()).toList());
//        parameters.addValue("localityList", parcels.stream().map(p-> p.getLocality()).toList());

        MapSqlParameterSource[] parameters = new MapSqlParameterSource[parcels.size()];
        int count = 0;
        for (Parcel parcel: parcels) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("idList", parcel.getId());
            parameter.addValue("utilization_by_docList", parcel.getUtilizationByDoc());
            parameter.addValue("categoryList", parcel.getCategory());
            parameter.addValue("noteList", parcel.getNote());
            parameter.addValue("localityList", parcel.getLocality());
            parameters[count++] = parameter;
        }
//
        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET " +
                "utilization_by_doc = :utilization_by_docList, " +
                "category = :categoryList, " +
                "note = :noteList, " +
                "locality = :localityList " +
                "WHERE id = :idList";
//        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET " +
//                "utilization_by_doc = ?, " +
//                "category = ?, " +
//                "note = ?, " +
//                "locality = ? " +
//                "WHERE id = ?";
        template.batchUpdate(SQLUpdate, parameters);
    }
}
