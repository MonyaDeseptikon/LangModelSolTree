package deseptikon.monya.spring_jdbc.util;

import deseptikon.monya.spring_jdbc.model.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapperPredicted implements RowMapper {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcel = new Parcel();
        parcel.setId(rs.getInt("id"));
        parcel.setCadastralNumber(rs.getString("cadastral_number"));
        parcel.setArea(rs.getDouble("area"));
        parcel.setNote(rs.getString("note"));
        parcel.setCategory("category");
        parcel.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcel.setInnerCadastralNumbers(rs.getString("inner_cadastral_numbers"));
        parcel.setUsageCode(rs.getString("usage_code"));
        parcel.setPredictedUsageCode(rs.getString("PREDICTED_USAGE_CODE"));
        return parcel;
    }
}
