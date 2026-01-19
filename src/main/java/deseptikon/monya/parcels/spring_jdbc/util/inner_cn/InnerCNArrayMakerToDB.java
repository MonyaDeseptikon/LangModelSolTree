package deseptikon.monya.parcels.spring_jdbc.util.inner_cn;

import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public interface InnerCNArrayMakerToDB {
    default MapSqlParameterSource[] insertInnerCNArrayMakerToDB(List<InnerCN> innerCNList) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[innerCNList.size()];
        int count = 0;
        for (InnerCN innerCN : innerCNList) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("idList", innerCN.getId());
            parameter.addValue("cadastral_numberList", innerCN.getCadastral_number());
            parameter.addValue("building_nameList", innerCN.getBuilding_name());
            parameter.addValue("areaList", innerCN.getArea());
            parameter.addValue("noteList", innerCN.getNote());
            parameter.addValue("usage_codeList", innerCN.getUsage_code());
            parameter.addValue("parcel_cadastral_numbersList", innerCN.getParcel_cadastral_numbers());
            parameters[count++] = parameter;
        }
        return parameters;
    }
}
