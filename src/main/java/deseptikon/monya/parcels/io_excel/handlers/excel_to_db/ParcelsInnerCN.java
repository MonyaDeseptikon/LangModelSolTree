package deseptikon.monya.parcels.io_excel.handlers.excel_to_db;

import deseptikon.monya.parcels.io_excel.transfer.IOExcelDB;
import deseptikon.monya.parcels.io_excel.transfer.InnerCNIOExcel;
import deseptikon.monya.parcels.spring_jdbc.jdbc.QueryInnerCN;
import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ParcelsInnerCN {


    public static void main(String[] args) throws SQLException, IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        QueryInnerCN queryInnerCN = (QueryInnerCN) context.getBean("dataSourceForJdbcTemplateInnerCN");

        String innerCNTableName = "INNER_CN_04";

        InnerCNIOExcel innerCNIOExcel = new IOExcelDB();
        List<InnerCN> innerCNList = innerCNIOExcel.excelToInnerCNTable("\\\\Server20032\\каталог оценщиков\\1. ОТДЕЛ КАДАСТРОВОЙ ОЦЕНКИ\\ИрхаСА\\Языковая модель\\Объекты из группы 04 из предперечня ОКС 2027.xlsx", 0);
        queryInnerCN.insertInnerCN(innerCNList, innerCNTableName);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }


}
