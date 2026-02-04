package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ParcelIOExcel {
//    void readExcelFillDBParcelsProvisionalList(String filePath, int worksheetIndex) throws IOException, SQLException;
    void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList, List<String> colHeads) throws FileNotFoundException, SQLException;
    List<Parcel> excelParcelsDirectoryToProvisionalList(String directoryPath, int worksheetIndex) throws IOException, SQLException;
}
