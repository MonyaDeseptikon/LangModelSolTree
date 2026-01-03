package deseptikon.monya.spring_jdbc.util;

import deseptikon.monya.spring_jdbc.model.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapperPredicted implements RowMapper {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcels = new Parcel();
        parcels.setId(rs.getInt("id"));
        parcels.setCadastralNumber(rs.getString("cadastral_number"));
        parcels.setArea(rs.getDouble("area"));
        parcels.setNote(rs.getString("note"));
        parcels.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcels.setInnerCadastralNumbers(rs.getString("inner_cadastral_numbers"));
        parcels.setUsageCode(rs.getString("usage_code"));
        parcels.setPredictedUsageCode(rs.getString("PREDICTED_USAGE_CODE"));
        return parcels;
    }
}
