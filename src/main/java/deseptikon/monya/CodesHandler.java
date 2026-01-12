package deseptikon.monya;

import deseptikon.monya.db.list_real_estate.CreateTables;
import deseptikon.monya.spring_jdbc.jdbc.QueryParcel;
import deseptikon.monya.usage_codes.UC01_010;
import deseptikon.monya.usage_codes.UC01_150;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class CodesHandler {




    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring _config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");

        CreateTables.erasePredictedUC();
        new UC01_010().assignmentCode(queryTemplate);
        new UC01_150().assignmentCode(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }


}
