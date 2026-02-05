package deseptikon.monya.configuration.spring_jdbc.util.parcel;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapperPredictedColName implements RowMapper {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("COLUMN_NAME");
    }
}
