package deseptikon.monya.io_excel.handlers;

import deseptikon.monya.db.list_real_estate.CreateTables;
import deseptikon.monya.io_excel.IOExcelDB;
import deseptikon.monya.spring_jdbc.jdbc.QueryParcel;
import deseptikon.monya.spring_jdbc.model.Parcel;
import deseptikon.monya.usage_codes.UC01_010;
import org.apache.commons.lang3.time.StopWatch;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DBtoExcel {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Set<Parcel> parcelList = new HashSet<>();
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring _config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");

        parcelList.addAll(queryTemplate.getListParcels());

        IOExcelDB ioExcelDB = new IOExcelDB();

        File file = new File("E:\\ЦКО БТИ УР\\Определение кода\\Данные\\Test.xlsx");
        FileOutputStream os = new FileOutputStream(file);
        Workbook wb = new Workbook(os, "assignmentCode", "1.0");

        Worksheet ws = wb.newWorksheet("PrdtUC");
        ws.value(0, 0, "This is a string in A1");
        ws.value(0, 1, 012);
        ws.value(0, 2, 1234);
        ws.value(0, 3, 123456L);
        ws.value(0, 4, 1.234);

        wb.finish();


        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }
}
