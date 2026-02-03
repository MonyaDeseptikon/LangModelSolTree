package deseptikon.monya.parcels.spring_jdbc.util.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
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
        parcel.setUtilizationPermittedUseText(rs.getString("utilization_permitted_use_text"));
        parcel.setUtilizationLandUse(rs.getString("utilization_land_use"));
        parcel.setOther(rs.getString("other"));
        parcel.setTYPE_STREET(rs.getString("TYPE_STREET"));
        parcel.setSTREET(rs.getString("STREET"));
        parcel.setTYPE_LOCALITY(rs.getString("TYPE_LOCALITY"));
        parcel.setTYPE_CITY(rs.getString("TYPE_CITY"));
        parcel.setCITY(rs.getString("CITY"));
        parcel.setTYPE_DISTRICT(rs.getString("TYPE_DISTRICT"));
        parcel.setDISTRICT(rs.getString("DISTRICT"));
        return parcel;
    }
}
