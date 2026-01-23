package deseptikon.monya.parcels.spring_jdbc.util.building;

import deseptikon.monya.parcels.spring_jdbc.models.Building;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public interface BuildingArrayMakerToDB {
    default MapSqlParameterSource[] insertInnerCNArrayMakerToDB(List<Building> buildingList) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[buildingList.size()];
        int count = 0;
        for (Building building : buildingList) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("idList", building.getId());
            parameter.addValue("cadastral_numberList", building.getCadastral_number());
            parameter.addValue("building_nameList", building.getBuilding_name());
            parameter.addValue("areaList", building.getArea());
            parameter.addValue("noteList", building.getNote());
            parameter.addValue("usage_codeList", building.getUsage_code());
            parameter.addValue("parcel_cadastral_numbersList", building.getParcel_cadastral_numbers());
            parameters[count++] = parameter;
        }
        return parameters;
    }
}
