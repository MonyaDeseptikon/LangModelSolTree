package deseptikon.monya.configuration.spring_jdbc.util.auxiliary_util;

import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public interface AuxiliaryArrayMakerToDB {

    default MapSqlParameterSource[] codeKLADRArrayMakerToDB(List<CodeKLADR> codeKLADRList) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[codeKLADRList.size()];
        int count = 0;
        for (CodeKLADR codeKLADR : codeKLADRList) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("ID", codeKLADR.getId());
            parameter.addValue("CODE_KLADR", codeKLADR.getCodeKLADR());
            parameter.addValue("TYPE_DISTRICT", codeKLADR.getTypeDistrict());
            parameter.addValue("DISTRICT", codeKLADR.getDistrict());
            parameter.addValue("TYPE_CITY", codeKLADR.getTypeCity());
            parameter.addValue("CITY", codeKLADR.getCity());
            parameter.addValue("TYPE_LOCALITY", codeKLADR.getTypeLocality());
            parameter.addValue("LOCALITY", codeKLADR.getLocality());
            parameter.addValue("TYPE_STREET", codeKLADR.getTypeStreet());
            parameter.addValue("STREET", codeKLADR.getStreet());
            parameter.addValue("CODE_OKATO", codeKLADR.getCodeOKATO());
            parameter.addValue("REGEXP", codeKLADR.getREGEXP());

            parameters[count++] = parameter;
        }
        return parameters;
    }
}
