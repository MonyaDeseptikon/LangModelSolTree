package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.db.create_tables.ParcelCreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.lmstQuery;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.model.uc.UC;
import deseptikon.monya.parcels.usage_codes.model.uc.UCBuilder;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UC04_040 extends UC implements UCBuilder {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        lmstQuery queryTemplate = (lmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");


        ParcelCreateProvisionalList.erasePredictedUC();

        new UC04_040().assignmentCode(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");

    }

    @Override
    public void assignmentCode(lmstQuery queryTemplate) throws SQLException {
        Set<Parcel> parcelList = new HashSet<>();

        parcelList.addAll(queryTemplate.getListParcelsByTags(queryTagForCode(codeOnlyCondition().getTags()), queryExcludeTags(codeOnlyCondition().getExcludeTags()),
                codeOnlyCondition().getMoreThisArea(), codeOnlyCondition().getLessThisArea()));

        for (Conditions condition : conditionsList()) {
            StringBuilder tags;
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags;
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelList.addAll(queryTemplate.getListParcelsByTagsWithoutICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));

            parcelList.addAll(queryTemplate.getListParcelsByTagsJoinListICN(tags, excludeTags, innerCNTableName(), usageCodeBuildingsMustBe()));

//            parcelList.addAll(queryTemplate.getListParcelsByTagsJoinICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea(), innerCNTableName()));
        }

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode());

        System.out.println(parcelList.size());
    }





    @Override
    public List<String> excludeTagsTemplate() {
        return List.of();
    }

    @Override
    public Conditions codeOnlyCondition() {
        return new Conditions(List.of("[^\\d\\.]" + "4\\s*\\.\\s*4" + "[^\\.\\d]"),
                excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F);
    }


    @Override
    public List<Conditions> conditionsList() {
       return  List.of(
//            new Conditions(List.of("магазин"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("продажа", "товаров"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("размещение", "киоска"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("торговая", "палатка"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("коммерческая", "деятельность"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("торговый", "павильон"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("автосалон"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("аптека"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("остановочный", "комплекс"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("оптика"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("рекламная", "конструкция"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F)

                new Conditions(List.of("магазин"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("продажа"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("киоска"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("палатка"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("коммерческая"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("торговый"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("автосалон"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("аптека"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("остановочный", "комплекс"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("оптика"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("рекламная", "конструкция"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("павильон"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F)
        );
    }

    @Override
    public String usageCode() {
        return "04:040";
    }

    @Override
    public boolean isEmptyInnerCN() {
        return false;
    }

    @Override
    public String innerCNTableName() {
        return "BUILDINGS.PARCEL_INNER_CN";
    }

    @Override
    public List<String> usageCodeBuildingsMustBe() {
        return List.of("0401","0402","0403","0404","0405","0406","0407","0408","0409","0410","0411","0412",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031");
    }
}
