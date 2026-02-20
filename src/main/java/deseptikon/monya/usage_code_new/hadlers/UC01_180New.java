package deseptikon.monya.usage_code_new.hadlers;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.usage_code_new.model.ConditionsNew;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class UC01_180New {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        ParcelCreateProvisionalList.erasePredictedUC();
        new UC01_180New().codeHandler(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

    public void codeHandler(LmstQuery queryTemplate) {

        ConditionsNew conditionsNew = new ConditionsNew();
        conditionsNew.setMoreThisShareAreaBuildings(0.05F);
        conditionsNew.setLessThisShareAreaBuildings(1F);
        conditionsNew.setUsageCodeBuildingsMustBe(List.of("0701", "0704", "0705", "0706", "0707", "0710", "0711", "0712", "0713", "0715", "0716", "0717", "0718",
                "0719", "0722", "0723", "0724", "0725", "0726", "0727", "0728", "0729",
                //только для сельхозпроизводства
                "0801", "0902", "0904", "0905", "0907",
                //линейные объекты
//                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031",
                //только для сельхозпроизводства
                "1009"));
        conditionsNew.setInnerCNTableName("BUILDINGS.PARCEL_INNER_CN");
        conditionsNew.setUsageCode("01:180");
        conditionsNew.setExcludeTags(List.of());
//        List.of("индивидуальный", "пашня_", "отдых__", "жилой", "дачный", "личный")
        conditionsNew.setConditionsNewList(List.of(
                conditionsNew.new InternalConditions(List.of("1.18"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("обеспечение", "сельскохозяйственное", "производство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, true, true),

                conditionsNew.new InternalConditions(List.of("1.0"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("1.0"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),

                //Район - ограничение по площади
                conditionsNew.new InternalConditions(List.of("транспортная", "станция", "сельскохозяйственная", "техника"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("машинная", "станция", "сельскохозяйственная", "техника"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("ремонтная", "станция", "сельскохозяйственная", "техника"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("ангар__", "сельскохозяйственная", "техника"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("гараж__", "сельскохозяйственная", "техника"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("амбар__", "сельское", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("водонапорная", "башня_", "сельское", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("трансформаторная", "станция", "сельское", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "использование"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственная", "деятельность"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("ведение", "сельского", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "производство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("производство", "сельскохозяйственной", "продукции"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("крестьянское", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("фермерское", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("подсобное", "сельское", "хозяйство"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("размещение", "сельхозугодий"),
                        List.of(), 0F, 200000F, true, false),
                conditionsNew.new InternalConditions(List.of("сельхозпроизводство"),
                        List.of(), 0F, 200000F, true, false),

//               //  Было в туре 2022
//                conditionsNew.new InternalConditions(List.of("зерноток"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("зернохранилище"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("хранение", "сельскохозяйственной"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("переработка", "сельскохозяйственной"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("зерноочистительный", "комплекс"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("арочный", "фуражный"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("зерносклад"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("картофелехранилище"),
//                        List.of(), 0F, 200000F, true, false),
//                conditionsNew.new InternalConditions(List.of("мельничный", "комплекс"),
//                        List.of(), 0F, 200000F, true, false),
//                 conditionsNew.new InternalConditions(List.of("овощехранилище"),
//                        List.of(), 0F, 200000F, true, false),

                //Город
                conditionsNew.new InternalConditions(List.of("транспортная", "станция", "сельскохозяйственная", "техника"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("машинная", "станция", "сельскохозяйственная", "техника"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("ремонтная", "станция", "сельскохозяйственная", "техника"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("ангар__", "сельскохозяйственная", "техника"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("гараж__", "сельскохозяйственная", "техника"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("амбар__", "сельское", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("водонапорная", "башня_", "сельское", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("трансформаторная", "станция", "сельское", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "использование"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("ведение", "сельского", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("сельскохозяйственное", "производство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("крестьянское", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("фермерское", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("подсобное", "сельское", "хозяйство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("размещение", "сельхозугодий"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
                conditionsNew.new InternalConditions(List.of("сельхозпроизводство"),
                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true)

//                //  Было в туре 2022
//                conditionsNew.new InternalConditions(List.of("зерноток"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("зернохранилище"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("хранение", "сельскохозяйственной"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("переработка", "сельскохозяйственной"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("зерноочистительный", "комплекс"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("арочный", "фуражный"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("зерносклад"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("картофелехранилище"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("мельничный", "комплекс"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true),
//                conditionsNew.new InternalConditions(List.of("овощехранилище"),
//                        List.of(), 0F, Float.POSITIVE_INFINITY, false, true)

        ));
        conditionsNew.assignmentCodeInnerCNCheck(queryTemplate);

    }
}



