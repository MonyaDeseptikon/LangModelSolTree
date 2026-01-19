package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ParcelIOExcel {
    void readExcelFillDBParcelsProvisionalList(String filePath, int worksheetIndex) throws FileNotFoundException, SQLException;
    void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList, List<String> colHeads) throws FileNotFoundException, SQLException;
}
