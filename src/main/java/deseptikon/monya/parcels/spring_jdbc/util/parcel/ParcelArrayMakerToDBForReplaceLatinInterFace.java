package deseptikon.monya.parcels.spring_jdbc.util.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public interface ParcelArrayMakerToDBForReplaceLatinInterFace {
    default MapSqlParameterSource[] ParcelArrayMakerToDBForReplaceLatin(List<Parcel> parcels) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[parcels.size()];
        int count = 0;
        for (Parcel parcel : parcels) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("idList", parcel.getId());
            parameter.addValue("utilization_by_docList", parcel.getUtilizationByDoc());
            parameter.addValue("categoryList", parcel.getCategory());
            parameter.addValue("noteList", parcel.getNote());
            parameter.addValue("localityList", parcel.getLocality());
            parameters[count++] = parameter;
        }
        return parameters;
    }
}
