package deseptikon.monya.inner_cn.spring_jdbc.util;

import deseptikon.monya.inner_cn.spring_jdbc.model.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapperSimple implements RowMapper {

    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcels = new Parcel();
        parcels.setId(rs.getInt("id"));
        parcels.setCadastralNumber(rs.getString("cadastral_number"));
        parcels.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcels.setUsageCode(rs.getString("usage_code"));
        return parcels;
    }


}
