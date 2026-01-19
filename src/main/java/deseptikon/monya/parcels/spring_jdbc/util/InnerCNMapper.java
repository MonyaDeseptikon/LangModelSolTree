package deseptikon.monya.parcels.spring_jdbc.util;

import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InnerCNMapper implements RowMapper {

    @Override
    public InnerCN mapRow(ResultSet rs, int rowNum) throws SQLException {
        InnerCN innerCN = new InnerCN();

        innerCN.setId(rs.getInt("id"));
        innerCN.setCadastral_number(rs.getString("cadastral_number"));
        innerCN.setBuilding_name(rs.getString("building_name"));
        innerCN.setArea(rs.getFloat("area"));
        innerCN.setNote(rs.getString("note"));
        innerCN.setUsage_code(rs.getString("usage_code"));
        innerCN.setParcel_cadastral_numbers(rs.getString("parcel_cadastral_numbers"));

        return innerCN;
    }

}
