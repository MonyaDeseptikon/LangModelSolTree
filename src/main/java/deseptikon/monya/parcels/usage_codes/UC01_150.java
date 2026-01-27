package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.db.create_tables.ParcelCreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.model.UC;
import deseptikon.monya.parcels.usage_codes.model.UCBuilder;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UC01_150 extends UC implements UCBuilder {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");

        ParcelCreateProvisionalList.erasePredictedUC();

        new UC01_150().assignmentCode(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");

    }

    @Override
    public void assignmentCode(QueryParcel queryTemplate) throws SQLException {
        Set<Parcel> parcelList = new HashSet<>();

        parcelList.addAll(queryTemplate.getListParcelsByTags(queryTagForCode(codeOnlyCondition().getTags()), queryExcludeTags(codeOnlyCondition().getExcludeTags()),
                codeOnlyCondition().getMoreThisArea(), codeOnlyCondition().getLessThisArea()));

        for (Conditions condition : conditionsList()) {
            StringBuilder tags;
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags;
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelList.addAll(queryTemplate.getListParcelsByTagsWithoutICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));

            parcelList.addAll(queryTemplate.getListParcelsByTagsJoinListICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea(), innerCNTableName(), usageCodeBuildingsMustBe()));
        }

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode());

        System.out.println(parcelList.size());
    }

    @Override
    public List<String> excludeTagsTemplate() {
        return List.of("индивидуальный", "1.7__", "обеспечение", "пашня", "обслуживание", "отдых",
                "жилой", "дачный", "личный");
    }

    @Override
    public Conditions codeOnlyCondition() {
        return new Conditions(List.of("[^\\d\\.]" + "1\\s*\\.\\s*15" + "[^\\.\\d]"),
                excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F);
    }


    @Override
    public List<Conditions> conditionsList() {
        return List.of(
                new Conditions(List.of("сельскохозяйственное", "производство"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),
                new Conditions(List.of("зерноток"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),
                new Conditions(List.of("зернохранилище"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),

                new Conditions(List.of("крестьянское", "хозяйство"),
                        excludeTagsTemplate(), 0F, 100000F, 0.1F),

                new Conditions(List.of("хранение", "переработка", "сельскохозяйственной"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),

                new Conditions(List.of("фермерское", "хозяйство"),
                        excludeTagsTemplate(), 0F, 100000F, 0.1F),

                new Conditions(List.of("производство", "сельскохозяйственной", "продукции"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),

                new Conditions(List.of("зерноочистительный", "комплекс"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),
                new Conditions(List.of("арочный", "фуражный"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),

                new Conditions(List.of("зерносклад"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),
                new Conditions(List.of("картофелехранилище"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),
                new Conditions(List.of("мельничный", "комплекс"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),
                new Conditions(List.of("овощехранилище"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F),

                new Conditions(List.of("сельхозпроизводство"),
                        excludeTagsTemplate(), 0F, 1000000F, 0.1F)
        );
    }

    @Override
    public String usageCode() {
        return "01:150";
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
        return List.of("0401", "0402", "0403", "0404", "0405", "0406", "0407", "0408", "0409", "0410", "0411", "0412");
    }

}
