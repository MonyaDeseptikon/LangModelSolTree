package deseptikon.monya.configuration.io_excel.handlers.excel_to_db;

import deseptikon.monya.configuration.io_excel.transfer.IOExcelDB;
import deseptikon.monya.configuration.io_excel.transfer.ParcelIOExcel;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ParcelsProvisionalHandlerExcel {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

        ParcelIOExcel parcelIOExcel = new IOExcelDB();

        //Ориентируюсь по столбцу КН, - чтобы не попасть на пустые строки
        int cadastralNumberCell = 2;
        List<Parcel> parcelList = parcelIOExcel.excelParcelsDirectoryToProvisionalList(
                "\\\\192.168.0.118\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Языковая модель\\Перечень 2026\\18_02_26", 0,
                cadastralNumberCell);

        queryTemplate.insertParcelList(parcelList);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

}
