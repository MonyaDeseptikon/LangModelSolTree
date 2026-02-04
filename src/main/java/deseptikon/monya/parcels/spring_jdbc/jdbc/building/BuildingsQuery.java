package deseptikon.monya.parcels.spring_jdbc.jdbc.building;

import deseptikon.monya.parcels.spring_jdbc.models.Building;

import java.util.List;
import java.util.Set;

public interface BuildingsQuery {
    List<Building> getListBuildingsTable(String tableName);

    List<Building> getListAreaBuildings(Set<String> buildingsCN);

    void insertInnerCN(final List<Building> buildingList, String tableName);
}
