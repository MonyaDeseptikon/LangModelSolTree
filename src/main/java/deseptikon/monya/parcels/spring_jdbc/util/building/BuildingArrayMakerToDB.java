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
            parameter.addValue("id", building.getId());
            parameter.addValue("cadastral_number", building.getCadastral_number());
            parameter.addValue("object_type", building.getObject_type());
            parameter.addValue("object_name", building.getObject_name());
            parameter.addValue("object_assignation", building.getObject_assignation());
            parameter.addValue("object_permitted_uses", building.getObject_permitted_uses());
            parameter.addValue("OKATO", building.getOKATO());
            parameter.addValue("OKTMO", building.getOKTMO());
            parameter.addValue("area", building.getArea());
            parameter.addValue("note", building.getNote());
            parameter.addValue("usage_code", building.getUsage_code());
            parameter.addValue("parcel_cadastral_numbers", building.getParcel_cadastral_numbers());
            parameters[count++] = parameter;
        }
        return parameters;
    }
}
