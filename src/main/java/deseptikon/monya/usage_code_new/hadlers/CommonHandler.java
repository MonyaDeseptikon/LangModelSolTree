package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class CommonHandler {
    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

        ParcelCreateProvisionalList.erasePredictedUC();

        new UC01_010New().codeHandler(queryTemplate);
        new UC01_180New().codeHandler(queryTemplate);
        new UC04_040New().codeHandler(queryTemplate);
        new UC06_090New().codeHandler(queryTemplate);
        new UC02_011New().codeHandler(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }
}
