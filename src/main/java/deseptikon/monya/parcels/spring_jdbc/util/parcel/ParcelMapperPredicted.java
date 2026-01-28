package deseptikon.monya.parcels.spring_jdbc.util.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParcelMapperPredicted implements RowMapper {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcel = new Parcel();

        parcel.setId(rs.getInt("id"));
        parcel.setCadastralNumber(rs.getString("cadastral_number"));
        parcel.setArea(rs.getFloat("area"));
        parcel.setNote(rs.getString("note"));
        parcel.setCategory(rs.getString("category"));
        parcel.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split("_x000D_")));
        parcel.setUsageCode(rs.getString("usage_code"));
        parcel.setPredictedUsageCode(rs.getString("PREDICTED_USAGE_CODE"));
        parcel.setLocality(rs.getString("locality"));
        return parcel;
    }
}
