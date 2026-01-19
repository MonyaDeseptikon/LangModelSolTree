package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InnerCNIOExcel {
    List<InnerCN> excelToInnerCNTable(String filePath, int worksheetIndex) throws IOException, SQLException;
}
