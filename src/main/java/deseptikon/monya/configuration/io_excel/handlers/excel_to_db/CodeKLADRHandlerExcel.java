package deseptikon.monya.configuration.io_excel.handlers.excel_to_db;

import deseptikon.monya.configuration.io_excel.transfer.CodeKLADRIOExcel;
import deseptikon.monya.configuration.io_excel.transfer.IOExcelDB;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CodeKLADRHandlerExcel {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        AuxiliaryTableQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

        CodeKLADRIOExcel codeKLADRIOExcel = new IOExcelDB();

        List<CodeKLADR> codeKLADRList = codeKLADRIOExcel.excelCodeKLADRToKLADR("\\\\192.168.0.118\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Присваивание КЛАДР\\УР_КЛАДР_май_2025_итог.xlsm", 0);

        queryTemplate.insertCodeKLADRList(codeKLADRList);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

}
