package deseptikon.monya.parcels.spring_jdbc.jdbc;

import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class QueryBuilding implements BuildingArrayMakerToDB {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    public void setDataSourceInnerCN(DataSource dataSourceInnerCN) {
        this.jdbcTemplate = new JdbcTemplate(dataSourceInnerCN);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

    public List<Building> getListInnerCN(String innerCNTableName) {

        String SQLQuery = "SELECT * FROM PARCELS." +
                innerCNTableName;
        return jdbcTemplate.query(SQLQuery, new BuildingMapper());
    }
    public void insertInnerCN(final List <Building> buildingList, String tableName){
        String insertRowSQL = "INSERT INTO BUILDINGS." +
                tableName + " " +
                "(cadastral_number, object_type, object_name, object_assignation, object_permitted_uses, OKATO, OKTMO, area, note, usage_code, parcel_cadastral_numbers) " +
                "VALUES (:cadastral_number, :object_type, :object_name, :object_assignation, :object_permitted_uses, :OKATO, :OKTMO, :area, :note, :usage_code, :parcel_cadastral_numbers)";

        template.batchUpdate(insertRowSQL, insertInnerCNArrayMakerToDB(buildingList));
    }
}