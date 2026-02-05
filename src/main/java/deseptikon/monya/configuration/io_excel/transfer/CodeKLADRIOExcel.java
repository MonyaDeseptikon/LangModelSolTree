package deseptikon.monya.configuration.io_excel.transfer;

import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CodeKLADRIOExcel {
    List<CodeKLADR> excelCodeKLADRToKLADR(String directoryPath, int worksheetIndex) throws IOException, SQLException;

}
