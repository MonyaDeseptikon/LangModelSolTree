package deseptikon.monya.inner_cn.io_excel.handlers;

import deseptikon.monya.inner_cn.io_excel.transfer.IOExcelDB;
import deseptikon.monya.parcels.spring_jdbc.jdbc.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.model.Parcel;
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

public class DBtoExcel {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring _config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");
        Set<Parcel> parcelList = new HashSet<>();


        //Данные
        parcelList.addAll(queryTemplate.getListParcels());

        File file = new File("E:\\ЦКО БТИ УР\\Определение кода\\Данные\\Test.xlsx");
        FileOutputStream os = new FileOutputStream(file);
        Workbook wb = new Workbook(os, "MonyaDes", "1.0");
        Worksheet ws = wb.newWorksheet("PrdtUC");

        new IOExcelDB().readDBFillExcel(ws, parcelList, queryTemplate.getListColumnName());

        wb.finish();

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }
}
