package deseptikon.monya.parcels.io_excel.handlers.excel_to_db;

import deseptikon.monya.parcels.io_excel.transfer.CodeKLADRIOExcel;
import deseptikon.monya.parcels.io_excel.transfer.IOExcelDB;
import deseptikon.monya.parcels.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.parcels.spring_jdbc.models.CodeKLADR;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CodeKLADRHandler {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

        CodeKLADRIOExcel codeKLADRIOExcel = new IOExcelDB();

        List<CodeKLADR> codeKLADRList = codeKLADRIOExcel.excelCodeKLADRToKLADR("\\\\192.168.0.118\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Присваивание КЛАДР\\УР_КЛАДР_май_2025_СВОД.xlsx", 0);

        queryTemplate.insertCodeKLADRList(codeKLADRList);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

}
