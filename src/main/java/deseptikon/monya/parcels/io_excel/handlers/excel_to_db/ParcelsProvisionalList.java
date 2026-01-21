package deseptikon.monya.parcels.io_excel.handlers.excel_to_db;

import deseptikon.monya.parcels.io_excel.transfer.IOExcelDB;
import deseptikon.monya.parcels.io_excel.transfer.ParcelIOExcel;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.sql.SQLException;

public class ParcelsProvisionalList {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ParcelIOExcel parcelIOExcel = new IOExcelDB();
        parcelIOExcel.readExcelFillDBParcelsProvisionalList("\\\\Server20032\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Языковая модель\\Объекты из группы 04 из предперечня ОКС 2027.xlsx", 1);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken/1000 + " секунд");
    }

}
