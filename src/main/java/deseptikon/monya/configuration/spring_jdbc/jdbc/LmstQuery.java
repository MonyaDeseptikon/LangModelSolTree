package deseptikon.monya.configuration.spring_jdbc.jdbc;

import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.building.BuildingsQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.CombineMethodsParcel;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.GetParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.UpdateParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.Building;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import deseptikon.monya.configuration.spring_jdbc.util.auxiliary_util.AuxiliaryArrayMakerToDB;
import deseptikon.monya.configuration.spring_jdbc.util.auxiliary_util.CodeKLADRMapper;
import deseptikon.monya.configuration.spring_jdbc.util.building.BuildingArrayMakerToDB;
import deseptikon.monya.configuration.spring_jdbc.util.building.BuildingMapper;
import deseptikon.monya.configuration.spring_jdbc.util.parcel.*;
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
    public void fillRegexpKLADRList(List<CodeKLADR> codeKLADRList) {
        String SQLUpdate = "UPDATE AUXILIARY.KLADR SET " +
                "REGEXP = :REGEXP " +
                "WHERE ID = :ID";
        template.batchUpdate(SQLUpdate, codeKLADRArrayMakerToDB(codeKLADRList));

    }

    @Override
    public void concatParcelsKLADRTags(List<Parcel> parcels) {
        String SQLUpdate = "UPDATE PARCELS.PARCEL_LIST_2026 SET " +

                "REGEXP = " +
                "CASE " +
                "WHEN REGEXP = '' " +
                "THEN :REGEXP " +
                "ELSE CONCAT(REGEXP, '; ', :REGEXP) " +
                "END, " +

                "EXP_KLADR = " +
                "CASE " +
                "WHEN EXP_KLADR = '' " +
                "THEN :EXP_KLADR " +
                "ELSE CONCAT(EXP_KLADR, '; ', :EXP_KLADR) " +
                "END " +

                "WHERE ID = :ID";

        template.batchUpdate(SQLUpdate, forKLADR(parcels));
    }

    //    !!! Не работало, т.к. в запросе была ошибка - не указал нижний регистр. Выполнение 70сек для 1000строк, 490сек - 10000строк
        //    Думал так будет быстрее , чем перебор всех значений, но что БД висит. Добавление в запрос ограничения по количеству строк результата не дал
        //    Запрос : SELECT * FROM PARCELS.VIEW_KLADR PVK INNER JOIN AUXILIARY.KLADR AK ON PVK.NOTE REGEXP AK.REGEXP
    @Override
    public List<Parcel> getListParcelsByTagsKLADRInnerJoin(String parcelTableName) {

        String SQLQuery = "SELECT PVK.ID, PVK.CADASTRAL_NUMBER, AK.CODE_KLADR AS AUX_KLADR, AK.REGEXP AS AUX_REGEXP FROM PARCELS." +
                parcelTableName + " PVK "+
                "INNER JOIN " +
                "AUXILIARY.KLADR AK " +
                "ON LOWER(PVK.NOTE) REGEXP AK.REGEXP";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperJoinKLADR());
    }

    @Override
    public List<Parcel> getListParcelsByTagsKLADRNote(StringBuilder district, StringBuilder typeCity, StringBuilder city, StringBuilder typeLocality, StringBuilder locality, StringBuilder typeStreet, StringBuilder street) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tags", district.append(typeCity).append(city).append(typeLocality).append(locality).append(typeStreet).append(street));
        System.out.println(parameters);
        String SQLQuery = "SELECT * FROM PARCELS.VIEW_KLADR PVK " +
                "WHERE LOWER(PVK.NOTE) REGEXP :tags";
        System.out.println(SQLQuery);
        return template.query(SQLQuery, parameters, new ParcelMapperPredicted());
    }


    @Override
    public List<CodeKLADR> getListCodeKLADR() {
        String SQLQuery = "SELECT * FROM AUXILIARY.KLADR";
        return jdbcTemplate.query(SQLQuery, new CodeKLADRMapper());
    }

    @Override
    public void insertCodeKLADRList(List<CodeKLADR> codeKLADRList) {
        String insertRowSQL = "INSERT INTO AUXILIARY.KLADR " +
                "(CODE_KLADR, TYPE_DISTRICT, DISTRICT, CITY, TYPE_CITY, LOCALITY, TYPE_LOCALITY, STREET, TYPE_STREET, CODE_OKATO) " +
                "VALUES (:CODE_KLADR, :TYPE_DISTRICT, :DISTRICT, :CITY, :TYPE_CITY, :LOCALITY, :TYPE_LOCALITY, :STREET, :TYPE_STREET, :CODE_OKATO)";

        template.batchUpdate(insertRowSQL, codeKLADRArrayMakerToDB(codeKLADRList));
    }


    @Override
    public List<Parcel> getListParcels() {
        String SQLQuery = "SELECT * FROM PARCELS.PARCEL_LIST_2026";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted());
    }

    @Override
    public List<Parcel> getListParcelsView(String viewName) {
        String SQLQuery = "SELECT * FROM PARCELS." +
                viewName;
        return jdbcTemplate.query(SQLQuery, new ParcelMapperViewKLADR());
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
                // Это было ошибкой, - осуществляется поиск фразы Optional.Empty : Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
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
                // Это было ошибкой, - осуществляется поиск фразы Optional.Empty : Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
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
                // Это было ошибкой, - осуществляется поиск фразы Optional.Empty : Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
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
                // Это было ошибкой, - осуществляется поиск фразы Optional.Empty : Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
                "LOWER(PP.utilization_by_doc) NOT REGEXP :excludeTags AND " +
                "PP.area >= :moreArea AND " +
                "PP.area <= :lessArea";
        return template.query(SQLQuery, parameters, new ParcelMapperPredicted());

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
                // Это было ошибкой, - осуществляется поиск фразы Optional.Empty : Если приходит значение (только для REGEX !) Пустая строка (не null), то условие не учитывается!!!
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

        String SQLUpdate = "UPDATE PARCELS.PARCEL_LIST_2026 SET " +
                "utilization_by_doc = :utilization_by_docList, " +
                "category = :categoryList, " +
                "note = :noteList, " +
                "locality = :localityList, " +
                "DISTRICT = :DISTRICT, " +
                "TYPE_DISTRICT = :TYPE_DISTRICT, " +
                "CITY = :CITY, " +
                "TYPE_CITY = :TYPE_CITY, " +
                "TYPE_LOCALITY = :TYPE_LOCALITY, " +
                "STREET = :STREET, " +
                "TYPE_STREET = :TYPE_STREET, " +
                "OTHER = :OTHER, " +
                "UTILIZATION_LAND_USE = :UTILIZATION_LAND_USE, " +
                "UTILIZATION_PERMITTED_USE_TEXT = :UTILIZATION_PERMITTED_USE_TEXT " +
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
