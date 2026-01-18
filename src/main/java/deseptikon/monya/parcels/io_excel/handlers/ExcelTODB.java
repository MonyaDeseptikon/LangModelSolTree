package deseptikon.monya.parcels.io_excel.handlers;

import deseptikon.monya.parcels.io_excel.transfer.IOExcelDB;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.sql.SQLException;

public class ExcelTODB {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        new IOExcelDB().readExcelFillDBParcelsProvisionalList("E:\\ЦКО БТИ УР\\Определение кода\\Данные\\ЗУ 2026 allUseLogicTree.xlsm", 1);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken/1000 + " секунд");
    }
}
