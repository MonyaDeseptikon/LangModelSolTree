package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.usage_code_new.model.ConditionsNew;
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
        System.out.println(timeTaken / 1000 + " секунд");
    }

    public void codeHandler(LmstQuery queryTemplate) {

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0F);
        conditionsNew.setLessThisShareAreaBuildings(1F);
//        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0701", "0702", "0703", "0704", "0705", "0706", "0707", "0709", "0710", "0711", "0712", "0713", "0715", "0716", "0717", "0718",
//                "0719", "0721", "0722", "0723", "0724", "0725", "0726", "0727", "0728", "0729", "0730", "0732",
//                //линейные объекты
//                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027",
////                "1022"
//                "1022.1", "1022.2", "1022.3",
////                "1030"
//                "1030.5","1030.7","1030.10","1030.11","1030.16","1030.19","1030.21"                ,
////                "1031"
//                "1031.1", "1031.2", "1031.3", "1031.4", "1031.5"));
        //В строгом соответствии с таблицей шифров ОКС
        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0723", "0727", "0728", "1030.5", "1030.7", "1030.10", "1030.11", "1030.16", "1030.19", "1030.21",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027",
                // Для ЗУ 06:00
                "0605", "0607", "0701", "0702", "0703", "0704", "0722", "0725", "1002", "1003", "1007", "1008", "1009", "1019", "1030.3", "1030.20", "1030.22", "1030.29", "1037"
        ));


        conditionsNew.setInnerCNTableName("BUILDINGS.PARCEL_INNER_CN");
        conditionsNew.setUsageCode("06:090");
        conditionsNew.setExcludeTags(List.of());
//        List.of("железнодорожный", "перевалочный", "линия_", "электропередача", "казарма", "зерно_", "растениеводство", "сельскохозяйственный")
        conditionsNew.setConditionsNewList(List.of(
                conditionsNew.new InternalConditions(List.of("6.9"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("6.9.1"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("склад__"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("складская", "площадка"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("промышленная", "база_"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("погрузочный", "терминал"),
                        List.of("железнодорожный"), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("док__"),
                        List.of("железнодорожный"), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("оптовый", "склад"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("производственный", "склад"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
                conditionsNew.new InternalConditions(List.of("логистический", "центр"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true)
        ));
        conditionsNew.assignmentCodeInnerCNCheck(queryTemplate);
    }
}



