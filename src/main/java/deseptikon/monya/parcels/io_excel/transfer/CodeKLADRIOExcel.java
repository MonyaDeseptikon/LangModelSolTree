package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.spring_jdbc.models.CodeKLADR;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CodeKLADRIOExcel {
    List<CodeKLADR> excelCodeKLADRToKLADR(String directoryPath, int worksheetIndex) throws IOException, SQLException;

}
