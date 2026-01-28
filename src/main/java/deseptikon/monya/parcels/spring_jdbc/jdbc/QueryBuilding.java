package deseptikon.monya.parcels.spring_jdbc.jdbc;

import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingMapper;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredicted;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public class QueryBuilding implements BuildingArrayMakerToDB {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    public void setDataSourceInnerCN(DataSource dataSourceInnerCN) {
        this.jdbcTemplate = new JdbcTemplate(dataSourceInnerCN);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

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

    public void insertInnerCN(final List <Building> buildingList, String tableName){
        String insertRowSQL = "INSERT INTO BUILDINGS." +
                tableName + " " +
                "(cadastral_number, object_type, object_name, object_assignation, object_permitted_uses, OKATO, OKTMO, area, note, usage_code, parcel_cadastral_numbers) " +
                "VALUES (:cadastral_number, :object_type, :object_name, :object_assignation, :object_permitted_uses, :OKATO, :OKTMO, :area, :note, :usage_code, :parcel_cadastral_numbers)";

        template.batchUpdate(insertRowSQL, insertInnerCNArrayMakerToDB(buildingList));
    }
}