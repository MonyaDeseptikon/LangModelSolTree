package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.usage_code_new.model.ConditionsNew;
import deseptikon.monya.usage_code_new.usage_codes_old.model.Conditions;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class UC06_090New {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

//        ParcelCreateProvisionalList.erasePredictedUC();
        new UC06_090New().codeHandler(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");    }

    public void codeHandler(LmstQuery queryTemplate) {

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0F);
        conditionsNew.setLessThisShareAreaBuildings(1F);
        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0701", "0702", "0703", "0704", "0705", "0706", "0707", "0709", "0710", "0711", "0712", "0713", "0715", "0716", "0717", "0718",
                "0719", "0721", "0722", "0723", "0724", "0725", "0726", "0727", "0728", "0729", "0730", "0732",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031"));
        conditionsNew.setInnerCNTableName("BUILDINGS.PARCEL_INNER_CN");
        conditionsNew.setUsageCode("06:090");
        conditionsNew.setExcludeTags(List.of());
//        List.of("железнодорожный", "перевалочный", "линия_", "электропередача", "казарма", "зерно_", "растениеводство", "сельскохозяйственный")
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

//        new Conditions(List.of("[^\\d\\.]" + "6\\s*\\.\\s*9" + "[^\\.\\d]"),
//                excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F);
//        new Conditions(List.of("терминал"),
//                excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("ангар__"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("промышленный"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("навес__"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("производственный"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("промплощадка"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("склад__"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
//                new Conditions(List.of("оптовая", "торговля"),
//                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F)

        ));
        conditionsNew.assignmentCodeInnerCNCheck(queryTemplate);

    }
}



