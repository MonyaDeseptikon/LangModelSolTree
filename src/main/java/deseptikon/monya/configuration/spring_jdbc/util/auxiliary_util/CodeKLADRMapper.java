package deseptikon.monya.configuration.spring_jdbc.util.auxiliary_util;

import deseptikon.monya.configuration.spring_jdbc.models.Building;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeKLADRMapper implements RowMapper {

    @Override
    public CodeKLADR mapRow(ResultSet rs, int rowNum) throws SQLException {
        CodeKLADR codeKLADR = new CodeKLADR();

        codeKLADR.setId(rs.getInt("id"));
        codeKLADR.setCodeKLADR(rs.getString("CODE_KLADR"));
        codeKLADR.setCodeOKATO(rs.getString("CODE_OKATO"));
        codeKLADR.setDistrict(rs.getString("DISTRICT"));
        codeKLADR.setCity(rs.getString("CITY"));
        codeKLADR.setTypeLocality(rs.getString("TYPE_LOCALITY"));
        codeKLADR.setLocality(rs.getString("LOCALITY"));
        codeKLADR.setTypeStreet(rs.getString("TYPE_STREET"));
        codeKLADR.setStreet(rs.getString("STREET"));

        return codeKLADR;
    }

}

