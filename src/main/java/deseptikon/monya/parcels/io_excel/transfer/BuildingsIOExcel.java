package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.spring_jdbc.models.Building;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BuildingsIOExcel {
    List<Building> excelBuildingsDirectoryToInnerCNTable(String filePath, int worksheetIndex) throws IOException, SQLException;
    List<Building> excelConstructionsFileToInnerCNTable(String filePath, int worksheetIndex) throws IOException, SQLException;
}
