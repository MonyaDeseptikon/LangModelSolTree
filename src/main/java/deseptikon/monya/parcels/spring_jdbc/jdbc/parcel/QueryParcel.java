package deseptikon.monya.parcels.spring_jdbc.jdbc.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelArrayMakerToDBForReplaceLatinInterFace;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredicted;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredictedColName;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperReplaceLatin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public class QueryParcel implements GetParcelDAO, UpdateParcelDAO, ParcelArrayMakerToDBForReplaceLatinInterFace, CombineMethodsParcel {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

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
    public List<Parcel> getListParcelsByTagsInnerCNCondition(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isEmptyInnerCN) {

        if (isEmptyInnerCN) {
            return getListParcelsByTagsWithoutICN(tags, excludeTags, moreArea, lessArea);
        } else {
            return getListParcelsByTagsWithICN(tags, excludeTags, moreArea, lessArea);
        }
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

    public List<Parcel> getListParcelsByTagsJoinICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName) {
        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 PP INNER JOIN " +
                innerCNTableName + " PI " +
                "ON PP.inner_cadastral_numbers REGEXP PI.CADASTRAL_NUMBER " +
                "WHERE LOWER(PP.utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(PP.utilization_by_doc) NOT REGEXP ? AND " +
                "PP.area >= ? AND " +
                "PP.area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }


    public List<Parcel> getListParcelsByTagsJoinListICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName, List <String> usageCodeBuildings){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tags", tags);
        parameters.addValue("excludeTags", excludeTags);
        parameters.addValue("moreArea", moreArea);
        parameters.addValue("lessArea", lessArea);
        parameters.addValue("usageCodeBuildings", usageCodeBuildings);

//        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PRIVISIONAL_2026 PP INNER JOIN " +
                "(SELECT * FROM " +
                innerCNTableName + " " +
                "WHERE USAGE_CODE IN(:usageCodeBuildings)) " +
                 "BI " +
                "ON PP.inner_cadastral_numbers REGEXP BI.CADASTRAL_NUMBER " +
                "WHERE LOWER(PP.utilization_by_doc) REGEXP :tags AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(PP.utilization_by_doc) NOT REGEXP :excludeTags AND " +
                "PP.area >= :moreArea AND " +
                "PP.area <= :lessArea";
        return template.query(SQLQuery, parameters, new ParcelMapperPredicted());

//                jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);

//        SELECT * FROM PARCELS.PRIVISIONAL_2026 PP INNER JOIN (SELECT * FROM BUILDINGS.PARCEL_INNER_CN WHERE USAGE_CODE IN('0401', '0403')) BI
//        ON  PP.inner_cadastral_numbers  regexp BI.CADASTRAL_NUMBER
//        WHERE LOWER(PP.utilization_by_doc) REGEXP '.*магаз.*' AND LOWER(PP.utilization_by_doc) NOT REGEXP '.*сельско.*' AND p.area >= 0 AND p.area <= 10000000
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

        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET " +
                "utilization_by_doc = :utilization_by_docList, " +
                "category = :categoryList, " +
                "note = :noteList, " +
                "locality = :localityList " +
                "WHERE id = :idList";

        template.batchUpdate(SQLUpdate, ParcelArrayMakerToDBForReplaceLatin(parcels));
    }
}
