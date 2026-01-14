package deseptikon.monya.io_excel.transfer;

import deseptikon.monya.spring_jdbc_parcels.model.Parcel;
import org.dhatim.fastexcel.Worksheet;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ExcelDAO {
    void readExcelFillDBParcelsProvisionalList(String filePath, int worksheetIndex) throws FileNotFoundException, SQLException;
    void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList) throws FileNotFoundException, SQLException;
}
