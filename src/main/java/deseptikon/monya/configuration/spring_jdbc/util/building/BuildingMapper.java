package deseptikon.monya.configuration.spring_jdbc.util.building;

import deseptikon.monya.configuration.spring_jdbc.models.Building;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingMapper implements RowMapper {

    @Override
    public Building mapRow(ResultSet rs, int rowNum) throws SQLException {
        Building building = new Building();

        building.setId(rs.getInt("id"));
        building.setCadastral_number(rs.getString("cadastral_number"));
        building.setObject_name(rs.getString("object_name"));
        building.setArea(rs.getFloat("area"));
        building.setNote(rs.getString("note"));
        building.setUsage_code(rs.getString("usage_code"));
        building.setParcel_cadastral_numbers(rs.getString("parcel_cadastral_numbers"));

        return building;
    }

}

