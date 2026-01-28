package deseptikon.monya.parcels;

import deseptikon.monya.parcels.db.create_tables.ParcelCreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.QueryBuilding;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.usage_codes.UC01_010;
import deseptikon.monya.parcels.usage_codes.UC01_150;
import deseptikon.monya.parcels.usage_codes.UC04_040;
import deseptikon.monya.parcels.usage_codes.UC06_090;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class CodesHandler {




    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");
        QueryBuilding queryBuilding = (QueryBuilding) context.getBean("dataSourceForJdbcTemplateBuilding");

        ParcelCreateProvisionalList.erasePredictedUC();
        new UC01_010().assignmentCode(queryTemplate, queryBuilding);
        new UC01_150().assignmentCode(queryTemplate, queryBuilding);
        new UC04_040().assignmentCode(queryTemplate, queryBuilding);
        new UC06_090().assignmentCode(queryTemplate, queryBuilding);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

}
