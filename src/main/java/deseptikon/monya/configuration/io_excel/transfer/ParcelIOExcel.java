package deseptikon.monya.configuration.io_excel.transfer;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ParcelIOExcel {
//    void readExcelFillDBParcelsProvisionalList(String filePath, int worksheetIndex) throws IOException, SQLException;
    void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList) throws FileNotFoundException, SQLException, NoSuchFieldException;
    List<Parcel> excelParcelsDirectoryToProvisionalList(String directoryPath, int worksheetIndex, int cadastralNumberCell) throws IOException, SQLException;
}
