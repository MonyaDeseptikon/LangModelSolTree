package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.db.create_tables.ParcelCreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.LmstQuery;
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

public class UC06_090 extends UC implements UCBuilder {
    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");


        ParcelCreateProvisionalList.erasePredictedUC();

        new UC06_090().assignmentCode(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

    @Override
    public void assignmentCode(LmstQuery queryTemplate) throws SQLException {
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

        }

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode());

        System.out.println(parcelList.size());
    }

    @Override
    public List<String> excludeTagsTemplate() {
        return List.of("железнодорожный", "перевалочный", "линия_", "электропередача", "казарма", "зерно_", "растениеводство", "сельскохозяйственный");
    }

    @Override
    public Conditions codeOnlyCondition() {
        return new Conditions(List.of("[^\\d\\.]" + "6\\s*\\.\\s*9" + "[^\\.\\d]"),
                excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F);
    }

    @Override
    public List<Conditions> conditionsList() {
        return List.of(
                new Conditions(List.of("терминал"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("ангар__"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("промышленный"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("навес__"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("производственный"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("промплощадка"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("склад__"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("оптовая", "торговля"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F)
        );
    }

    @Override
    public String usageCode() {
        return "06:090";
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
        return List.of("0701", "0702", "0703", "0704", "0705", "0706", "0707", "0709", "0710", "0711", "0712", "0713", "0715", "0716", "0717", "0718",
                "0719", "0721", "0722", "0723", "0724", "0725", "0726", "0727", "0728", "0729", "0730", "0732",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031");
    }
}
