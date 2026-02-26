package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.usage_code_new.model.ConditionsNew;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class UC04_040New {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

//        ParcelCreateProvisionalList.erasePredictedUC();
        new UC04_040New().codeHandler(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 / 60 + " минут");
    }

    public void codeHandler(LmstQuery queryTemplate) {

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0F);
        conditionsNew.setLessThisShareAreaBuildings(1F);
//        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0401", "0402", "0405", "0408",
//                //линейные объекты
//                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027",
////                "1022"
//                "1022.1", "1022.2", "1022.3",
////                "1030"
//                "1030.18","1030.24","1030.28","1030.29","1030.30","1030.31",
////                "1031"
//                "1031.1", "1031.2", "1031.3", "1031.4", "1031.5"
//        ));
        //В строгом соответствии с таблицей шифров ОКС
        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0401", "0402", "0405", "0408", "1030.24",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027"));

        conditionsNew.setInnerCNTableName("BUILDINGS.PARCEL_INNER_CN");
        conditionsNew.setUsageCode("04:040");
        conditionsNew.setExcludeTags(List.of());
        conditionsNew.setConditionsNewList(List.of(
                conditionsNew.new InternalConditions(List.of("4.4"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("магазин"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("продажа_"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("киоск__"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("палатка_"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("коммерческая"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("торговый"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("автосалон"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("аптека"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of( "комплекс__"),
                        List.of("остановочный"), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("рекламная", "конструкция"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("оптика_"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("павильон"),
                        List.of("остановочный"), 0F, Float.POSITIVE_INFINITY, true, true)
        ));
        conditionsNew.assignmentCodeInnerCNCheck(queryTemplate);

    }
}



