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
        System.out.println(timeTaken / 1000 + " секунд");
    }

    public void codeHandler(LmstQuery queryTemplate) {

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0F);
        conditionsNew.setLessThisShareAreaBuildings(1F);
        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0401","0402","0403","0404","0405","0406","0407","0408","0409","0410","0411","0412",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031"));
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
                conditionsNew.new InternalConditions(List.of("остановочный", "комплекс"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("рекламная", "конструкция"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("оптика"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("павильон"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("оптика"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true)
        ));
        conditionsNew.assignmentCodeInnerCNCheck(queryTemplate);

    }
}



