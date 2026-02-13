package deseptikon.monya.configuration.io_excel.handlers.db_to_excel;

import deseptikon.monya.configuration.io_excel.transfer.IOExcelDB;
import deseptikon.monya.configuration.io_excel.transfer.ParcelIOExcel;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.apache.commons.lang3.time.StopWatch;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UsageCodeDBtoExcel {


    public static void main(String[] args) throws IOException, SQLException, NoSuchFieldException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        ParcelIOExcel parcelIOExcel = new IOExcelDB();

        Set<Parcel> parcelList = new HashSet<>(queryTemplate.getListParcels());

        File file = new File("\\\\Server20032\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Языковая модель\\Тесты JAVA\\01_010\\TestNew.xlsx");

        FileOutputStream os = new FileOutputStream(file);
        Workbook wb = new Workbook(os, "MonyaDes", "1.0");

        Worksheet ws = wb.newWorksheet("PrdtUC");


        parcelIOExcel.readDBFillExcel(ws, parcelList);

        wb.finish();

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }
}
