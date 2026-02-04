package deseptikon.monya.parcels.spring_jdbc.util.auxiliary_util;

import deseptikon.monya.parcels.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface AuxiliaryArrayMakerToDB {

    default MapSqlParameterSource[] codeKLADRArrayMakerToDB(List<CodeKLADR> codeKLADRList) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[codeKLADRList.size()];
        int count = 0;
        for (CodeKLADR codeKLADR : codeKLADRList) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("idList", codeKLADR.getId());
            parameter.addValue("utilization_by_docList", codeKLADR.getUtilizationByDoc());
            parameter.addValue("categoryList", codeKLADR.getCategory());
            parameter.addValue("noteList", codeKLADR.getNote());
            parameter.addValue("localityList", codeKLADR.getLocality());
            parameter.addValue("DISTRICT", codeKLADR.getDISTRICT());
            parameter.addValue("TYPE_DISTRICT", codeKLADR.getTYPE_DISTRICT());
            parameter.addValue("CITY", codeKLADR.getCITY());
            parameter.addValue("TYPE_CITY", codeKLADR.getTYPE_CITY());
            parameter.addValue("TYPE_LOCALITY", codeKLADR.getTYPE_LOCALITY());
            parameter.addValue("STREET", codeKLADR.getSTREET());
            parameter.addValue("TYPE_STREET", codeKLADR.getTYPE_STREET());
            parameter.addValue("OTHER", codeKLADR.getOther());
            parameter.addValue("UTILIZATION_LAND_USE", codeKLADR.getUtilizationLandUse());
            parameter.addValue("UTILIZATION_PERMITTED_USE_TEXT", codeKLADR.getUtilizationPermittedUseText());
            , TYPE_DISTRICT, DISTRICT, CITY, TYPE_CITY, LOCALITY, TYPE_LOCALITY, STREET, TYPE_STREET, CODE_OKATO
            parameters[count++] = parameter;
        }
        return parameters;
    }
}
