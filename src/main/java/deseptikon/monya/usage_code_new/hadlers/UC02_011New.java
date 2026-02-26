package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.GetParcelBigResultset;
import deseptikon.monya.usage_code_new.model.ConditionsNew;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class UC02_011New {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        GetParcelBigResultset queryTemplate = (GetParcelBigResultset) context.getBean("dataSourceForJdbcTemplateLMST");

        ParcelCreateProvisionalList.erasePredictedUC();
        new UC02_011New().codeHandler(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

    public void codeHandler(GetParcelBigResultset queryTemplate) {

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0F);
        conditionsNew.setLessThisShareAreaBuildings(1F);

        //В строгом соответствии с таблицей шифров ОКС
        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0203", "0203.1", "0203.2", "0203.3",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027"));

        conditionsNew.setInnerCNTableName("BUILDINGS.PARCEL_INNER_CN");
        conditionsNew.setUsageCode("02:011");
        conditionsNew.setExcludeTags(List.of());

        conditionsNew.setConditionsNewList(List.of(
                conditionsNew.new InternalConditions(List.of("2.1"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("индивидуальное", "жилищное"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("индивидуальный", "жилой", "дом__"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("индивидуальная", "застройка"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("ижс__"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("личное", "хозяйство", "жилой"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true)


        ));
        conditionsNew.assignmentCodeSimpleForBigResultset(queryTemplate);
    }
}



