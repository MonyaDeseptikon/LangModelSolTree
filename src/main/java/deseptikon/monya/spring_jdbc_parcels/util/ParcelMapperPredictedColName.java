package deseptikon.monya.spring_jdbc_parcels.util;

import deseptikon.monya.spring_jdbc_parcels.model.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParcelMapperPredictedColName implements RowMapper {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcel = new Parcel();
        parcel.setIdHead(rs.getString("id"));
        parcel.setCadastralNumber(rs.getString("cadastral_number"));
        parcel.setArea(rs.getDouble("area"));
        parcel.setNote(rs.getString("note"));
        parcel.setCategory(rs.getString("category"));
        parcel.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcel.setInnerCadastralNumbers(rs.getString("inner_cadastral_numbers"));
        parcel.setUsageCode(rs.getString("usage_code"));
        parcel.setPredictedUsageCode(rs.getString("PREDICTED_USAGE_CODE"));
        parcel.setLocality(rs.getString("locality"));
        return parcel;
    }
}
