package deseptikon.monya.usage_codes;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class CodesHandler {




    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");



        ParcelCreateProvisionalList.erasePredictedUC();
//        new UC01_010().assignmentCode(queryTemplate);
//        new UC01_150().assignmentCode(queryTemplate);
//        new UC04_040().assignmentCode(queryTemplate);
//        new UC06_090().assignmentCode(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

}
