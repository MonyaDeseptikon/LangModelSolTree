package deseptikon.monya.configuration.spring_jdbc.util.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
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
        parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split("_x000D_\\n")));
//        parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split(System.lineSeparator())));
        parcel.setUsageCode(rs.getString("usage_code"));
        parcel.setPredictedUsageCode(rs.getString("PREDICTED_USAGE_CODE"));
        parcel.setLocality(rs.getString("locality"));
        parcel.setTYPE_LOCALITY(rs.getString("TYPE_LOCALITY"));
        parcel.setTYPE_STREET(rs.getString("TYPE_STREET"));
        parcel.setSTREET(rs.getString("STREET"));
        parcel.setTYPE_CITY(rs.getString("TYPE_CITY"));
        parcel.setCITY(rs.getString("CITY"));
        parcel.setTYPE_DISTRICT(rs.getString("TYPE_DISTRICT"));
        parcel.setDISTRICT(rs.getString("DISTRICT"));
        parcel.setKLADR(rs.getString("KLADR"));
        parcel.setExpKLADR(rs.getString("EXP_KLADR"));
        parcel.setREGEXP(rs.getString("REGEXP"));
        parcel.setOKATO(rs.getString("OKATO"));
        parcel.setOKTMO(rs.getString("OKTMO"));

        return parcel;
    }
}
