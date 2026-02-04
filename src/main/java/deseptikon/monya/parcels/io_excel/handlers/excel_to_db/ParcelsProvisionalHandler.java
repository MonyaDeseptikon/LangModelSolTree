package deseptikon.monya.parcels.io_excel.handlers.excel_to_db;

import deseptikon.monya.parcels.io_excel.transfer.IOExcelDB;
import deseptikon.monya.parcels.io_excel.transfer.ParcelIOExcel;
import deseptikon.monya.parcels.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ParcelsProvisionalHandler {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

        ParcelIOExcel parcelIOExcel = new IOExcelDB();
//        parcelIOExcel.readExcelFillDBParcelsProvisionalList("\\\\Server20032\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Языковая модель\\Объекты из группы 04 из предперечня ОКС 2027.xlsx", 1);

        List<Parcel> parcelList = parcelIOExcel.excelParcelsDirectoryToProvisionalList("\\\\192.168.0.118\\каталог оценщиков\\ОЦЕНКА 2026\\Предварительный перечень\\Предварительный перечень ЗУ\\На загрузку в АИС_ЗУ\\1C_для обработки", 0);

        queryTemplate.insertParcelList(parcelList);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

}
