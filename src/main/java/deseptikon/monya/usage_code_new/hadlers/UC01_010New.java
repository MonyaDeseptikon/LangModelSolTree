package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.usage_code_new.model.ConditionsNew;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class UC01_010New {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        ParcelCreateProvisionalList.erasePredictedUC();
        new UC01_010New().codeHandler(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

    public void codeHandler(LmstQuery queryTemplate){

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0F);
        conditionsNew.setLessThisShareAreaBuildings(0.05F);
        conditionsNew.setUsageCodeBuildingsMustBe(List.of(
                //только для сельхоз
                "0801", "0902", "0904", "0905", "0907",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031",
                //только для сельхоз
                "1009"));
        conditionsNew.setInnerCNTableName("BUILDINGS.PARCEL_INNER_CN");
        conditionsNew.setUsageCode("01:010");
        conditionsNew.setExcludeTags(List.of("животноводство", "скотоводство", "садоводство", "звероводство", "птицеводство", "пчеловодство", "свиноводство", "рыбоводство", "индивидуальный", "отдых",
                "жилой", "дачный", "овощеводство", "личный", "строительство"));
        conditionsNew.setConditionsNewList(List.of(
                conditionsNew.new InternalConditions(List.of("1.1"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("растениеводство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("пашня_"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("пастбище"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("1.0"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "использование"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственная", "деятельность"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("ведение", "сельского", "хозяйства"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "производство"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("производство", "сельскохозяйственной", "продукции"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("крестьянское", "хозяйство"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("фермерское", "хозяйство"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "использование"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("подсобное", "сельское", "хозяйство"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("размещение", "сельхозугодий"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
                conditionsNew.new InternalConditions(List.of("сельхозпроизводство"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, true, false)
        ));
        conditionsNew.assignmentCodeInnerCNCheck(queryTemplate);

    }
}



