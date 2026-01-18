package deseptikon.monya.inner_cn.spring_jdbc.util;

import deseptikon.monya.inner_cn.spring_jdbc.model.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapperReplaceLatin implements RowMapper {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcel = new Parcel();

        parcel.setId(rs.getInt("id"));
        parcel.setNote(rs.getString("note"));
        parcel.setCategory(rs.getString("category"));
        parcel.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcel.setLocality(rs.getString("locality"));
        return parcel;

    }
}
