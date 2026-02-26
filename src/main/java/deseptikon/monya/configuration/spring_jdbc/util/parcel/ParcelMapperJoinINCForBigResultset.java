package deseptikon.monya.configuration.spring_jdbc.util.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParcelMapperJoinINCForBigResultset implements RowMapper {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcel = new Parcel();

        parcel.setId(rs.getInt("id"));
        parcel.setCadastralNumber(rs.getString("cadastral_number"));
        parcel.setCadastralBlock(rs.getString("CADASTRAL_BLOCK"));
        parcel.setArea(rs.getFloat("area"));
        parcel.setCategory(rs.getString("category"));
        parcel.setApprovalDocumentName(rs.getString("APPROVAL_DOCUMENT_NAME"));
        parcel.setUtilizationByDoc(rs.getString("utilization_by_doc"));
        parcel.setUtilizationLandUse(rs.getString("UTILIZATION_LAND_USE"));
        parcel.setUtilizationPermittedUseText(rs.getString("UTILIZATION_PERMITTED_USE_TEXT"));
        parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split("_x000D_\\n")));
//        parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split(System.lineSeparator())));

        return parcel;
    }
}
