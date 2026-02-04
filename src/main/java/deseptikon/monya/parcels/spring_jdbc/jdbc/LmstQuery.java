package deseptikon.monya.parcels.spring_jdbc.jdbc;

import deseptikon.monya.parcels.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.parcels.spring_jdbc.jdbc.building.BuildingsQuery;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.CombineMethodsParcel;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.GetParcelDAO;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.UpdateParcelDAO;
import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.spring_jdbc.util.auxiliary_util.AuxiliaryArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingMapper;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredicted;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredictedColName;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperReplaceLatin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public class LmstQuery implements GetParcelDAO, UpdateParcelDAO, ParcelArrayMakerToDB, BuildingArrayMakerToDB, CombineMethodsParcel, BuildingsQuery, AuxiliaryTableQuery, AuxiliaryArrayMakerToDB {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void insertCodeKLADRList(List<CodeKLADR> codeKLADRList) {
        String insertRowSQL = "INSERT INTO PARCELS.PARCEL_LIST_2026" +
                "(CODE_KLADR, TYPE_DISTRICT, DISTRICT, CITY, TYPE_CITY, LOCALITY, TYPE_LOCALITY, STREET, TYPE_STREET, CODE_OKATO) " +
                "VALUES (:CODE_KLADR, :TYPE_DISTRICT, :DISTRICT, :CITY, :TYPE_CITY, :LOCALITY, :TYPE_LOCALITY, :STREET, :TYPE_STREET, :CODE_OKATO)";

        template.batchUpdate(insertRowSQL, codeKLADRArrayMakerToDB(codeKLADRList));
    }

    @Override
    public List<String> getListColumnName() {
        String SQLQuery = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PARCEL_LIST_2026'";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredictedColName());
    }

    @Override
    public List<Parcel> getListParcels() {
        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026";
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
        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(utilization_by_doc) NOT REGEXP ? AND " +
                "area >= ? AND " +
                "area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }

    @Override
    public List<Parcel> getListParcelsByTagsWithoutICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea) {
        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
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
        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026 WHERE LOWER(utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(utilization_by_doc) NOT REGEXP ? AND " +
                "inner_cadastral_numbers != '' AND " +
                "area >= ? AND " +
                "area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }

    public List<Parcel> getListParcelsByTagsJoinICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName) {
        Object[] arg = new Object[]{tags, excludeTags, moreArea, lessArea};
        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026 PP INNER JOIN " +
                innerCNTableName + " PI " +
                "ON PP.inner_cadastral_numbers REGEXP PI.CADASTRAL_NUMBER " +
                "WHERE LOWER(PP.utilization_by_doc) REGEXP ? AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(PP.utilization_by_doc) NOT REGEXP ? AND " +
                "PP.area >= ? AND " +
                "PP.area <= ?";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted(), arg);
    }


    public List<Parcel> getListParcelsByTagsJoinListICN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, String innerCNTableName, List<String> usageCodeBuildings) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tags", tags);
        parameters.addValue("excludeTags", excludeTags);
        parameters.addValue("moreArea", moreArea);
        parameters.addValue("lessArea", lessArea);
        parameters.addValue("usageCodeBuildings", usageCodeBuildings);

        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026 PP INNER JOIN " +
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

//        SELECT * FROM PARCELS.PARCEL_LIST_2026 PP INNER JOIN (SELECT * FROM BUILDINGS.PARCEL_INNER_CN WHERE USAGE_CODE IN('0401', '0403')) BI
//        ON  PP.inner_cadastral_numbers  regexp BI.CADASTRAL_NUMBER
//        WHERE LOWER(PP.utilization_by_doc) REGEXP '.*магаз.*' AND LOWER(PP.utilization_by_doc) NOT REGEXP '.*сельско.*' AND p.area >= 0 AND p.area <= 10000000
    }

    //Переопределение метода getListParcelsByTagsJoinListICN
    @Override
    public List<Parcel> getListParcelsByTagsJoinListICN(StringBuilder tags, StringBuilder excludeTags, String innerCNTableName, List<String> usageCodeBuildings) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tags", tags);
        parameters.addValue("excludeTags", excludeTags);
        parameters.addValue("usageCodeBuildings", usageCodeBuildings);

        String SQLQuery = "SELECT * FROM " +
                "(SELECT * FROM PARCELS.PARCEL_LIST_2026 WHERE inner_cadastral_numbers != '') PP " +
                "INNER JOIN " +
                "(SELECT * FROM " +
                innerCNTableName + " " +
                "WHERE USAGE_CODE IN(:usageCodeBuildings)) " +
                "BI " +
                "ON PP.inner_cadastral_numbers REGEXP BI.CADASTRAL_NUMBER " +
                "WHERE LOWER(PP.utilization_by_doc) REGEXP :tags AND " +
                // Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(PP.utilization_by_doc) NOT REGEXP :excludeTags";
        return template.query(SQLQuery, parameters, new ParcelMapperPredicted());
    }

    @Override
    public void updatePredictedUC(Set<Integer> idList, String predictedUsageCode) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("predictedUsageCode", predictedUsageCode);
        parameters.addValue("idList", idList);
        String SQLUpdate = "UPDATE PARCELS.PARCEL_LIST_2026 SET PREDICTED_USAGE_CODE = :predictedUsageCode WHERE id IN (:idList)";
        template.update(SQLUpdate, parameters);
    }

    @Override
    public void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("predicatedUsageCode", predicatedUsageCode);
        parameters.addValue("idList", idList);
        String SQLUpdate = "UPDATE PARCELS.PARCEL_LIST_2026 SET PREDICTED_USAGE_CODE = " +
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
                "FROM PARCELS.PARCEL_LIST_2026";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperReplaceLatin());
    }

    @Override
    public void updateParcelsForReplaceLatin(List<Parcel> parcels) {




                "utilization_permitted_use_text",
                "utilization_land_use",
                "other",
                "TYPE_STREET",
                "STREET",
                "TYPE_LOCALITY",
                "locality",
                "TYPE_CITY",
                "CITY",
                "TYPE_DISTRICT",
                "DISTRICT"






        parameter.addValue("DISTRICT", parcel.getDISTRICT());
        parameter.addValue("TYPE_DISTRICT", parcel.getTYPE_DISTRICT());
        parameter.addValue("CITY", parcel.getCITY());
        parameter.addValue("TYPE_CITY", parcel.getTYPE_CITY());
        parameter.addValue("TYPE_LOCALITY", parcel.getTYPE_LOCALITY());
        parameter.addValue("STREET", parcel.getSTREET());
        parameter.addValue("TYPE_STREET", parcel.getTYPE_STREET());
        parameter.addValue("OTHER", parcel.getOther());
        parameter.addValue("UTILIZATION_LAND_USE", parcel.getUtilizationLandUse());
        parameter.addValue("UTILIZATION_PERMITTED_USE_TEXT"


        String SQLUpdate = "UPDATE PARCELS.PARCEL_LIST_2026 SET " +
                "utilization_by_doc = :utilization_by_docList, " +
                "category = :categoryList, " +
                "note = :noteList, " +
                "locality = :localityList " +





                "WHERE id = :idList";

        template.batchUpdate(SQLUpdate, parcelArrayMakerToDBForReplaceLatin(parcels));
    }

    /// Здесь размещены запросы к таблице Buildings
    public List<Building> getListBuildingsTable(String tableName) {

        String SQLQuery = "SELECT * FROM BUILDINGS." +
                tableName;
        return jdbcTemplate.query(SQLQuery, new BuildingMapper());
    }

    public List<Building> getListAreaBuildings(Set<String> buildingsCN) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("buildingsCN", buildingsCN);
        String SQLQuery = "SELECT * FROM BUILDINGS.PARCEL_INNER_CN " +
                "WHERE cadastral_number IN(:buildingsCN)";
        return template.query(SQLQuery, parameters, new BuildingMapper());
    }

    public void insertInnerCN(final List<Building> buildingList, String tableName) {
        String insertRowSQL = "INSERT INTO BUILDINGS." +
                tableName + " " +
                "(cadastral_number, object_type, object_name, object_assignation, object_permitted_uses, OKATO, OKTMO, area, note, usage_code, parcel_cadastral_numbers) " +
                "VALUES (:cadastral_number, :object_type, :object_name, :object_assignation, :object_permitted_uses, :OKATO, :OKTMO, :area, :note, :usage_code, :parcel_cadastral_numbers)";

        template.batchUpdate(insertRowSQL, insertInnerCNArrayMakerToDB(buildingList));
    }
@Override
    public void insertParcelList(List<Parcel> parcelList) {
        String insertRowSQL = "INSERT INTO PARCELS.PARCEL_LIST_2026" +
                "(CADASTRAL_NUMBER, AREA, OKATO, OKTMO, KLADR, DISTRICT, TYPE_DISTRICT, CITY, TYPE_CITY, LOCALITY, TYPE_LOCALITY, SOVIET_VILLAGE, STREET, TYPE_STREET, OTHER, NOTE, APPROVAL_DOCUMENT_NAME, CATEGORY, UTILIZATION_LAND_USE, UTILIZATION_BY_DOC, UTILIZATION_PERMITTED_USE_TEXT, INNER_CADASTRAL_NUMBERS, USAGE_CODE) " +
                "VALUES (:CADASTRAL_NUMBER, :AREA, :OKATO, :OKTMO, :KLADR, :DISTRICT, :TYPE_DISTRICT, :CITY, :TYPE_CITY, :LOCALITY, :TYPE_LOCALITY, :SOVIET_VILLAGE, :STREET, :TYPE_STREET, :OTHER, :NOTE, :APPROVAL_DOCUMENT_NAME, :CATEGORY, :UTILIZATION_LAND_USE, :UTILIZATION_BY_DOC, :UTILIZATION_PERMITTED_USE_TEXT, :INNER_CADASTRAL_NUMBERS, :USAGE_CODE)";

        template.batchUpdate(insertRowSQL, excelParcelToDB(parcelList));
    }


}
