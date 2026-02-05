package deseptikon.monya.usage_codes;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.models.Building;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import deseptikon.monya.usage_codes.model.Conditions;
import deseptikon.monya.usage_codes.model.uc.UC;
import deseptikon.monya.usage_codes.model.uc.UCBuilder;
import deseptikon.monya.usage_codes.model.uc.checkAreaBuildings;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.*;

public class UC01_010 extends UC implements UCBuilder, checkAreaBuildings {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");


        ParcelCreateProvisionalList.erasePredictedUC();

        new UC01_010().assignmentCode(queryTemplate);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }


    @Override
    public void assignmentCode(LmstQuery queryTemplate) {
        Set<Parcel> parcelListAll = new HashSet<>();
        Set<Parcel> parcelListToCheckArea = new HashSet<>();

        parcelListAll.addAll(queryTemplate.getListParcelsByTags(queryTagForCode(codeOnlyCondition().getTags()), queryExcludeTags(codeOnlyCondition().getExcludeTags()),
                codeOnlyCondition().getMoreThisArea(), codeOnlyCondition().getLessThisArea()));

        for (Conditions condition : conditionsList()) {
            StringBuilder tags;
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags;
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelListAll.addAll(queryTemplate.getListParcelsByTagsWithoutICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));

            parcelListToCheckArea.addAll(queryTemplate.getListParcelsByTagsJoinListICN(tags, excludeTags, innerCNTableName(), usageCodeBuildingsMustBe()));
        }
        //Добавление в общий список только участков с долей площади ОКС меньше установленной величины
        parcelListAll.addAll(parcelListCheckedArea(parcelListToCheckArea, queryTemplate));

        Set<Integer> idList = new HashSet<>();
        parcelListAll.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode());

        System.out.println(parcelListAll.size());
    }

    @Override
    public Set<Parcel> parcelListCheckedArea(Set<Parcel> parcelListToCheckArea, LmstQuery queryTemplate) {
        Set<Building> buildingListToCheckArea = new HashSet<>();

        //Формирования списка кадастровых номеров ОКС для запроса площади в БД
        Set<String> innerCNListBuildings = new HashSet<>();
        parcelListToCheckArea.forEach(p -> innerCNListBuildings.addAll(p.getInnerCadastralNumbers()));
        buildingListToCheckArea.addAll(queryTemplate.getListAreaBuildings(innerCNListBuildings));

        //Формирование Мапы для проверки кадастровых номеров ОКС, расположенных на ЗУ
        Map<String, Float> buildingCNArea = new HashMap<>();
        buildingListToCheckArea.forEach(b -> buildingCNArea.put(b.getCadastral_number(), b.getArea()));

        //Исключение из списка Участков, участков у которых площадь ОКС менее 5% или 10%
        Iterator<Parcel> iteratorParcel = parcelListToCheckArea.iterator();
        while (iteratorParcel.hasNext()) {
            Parcel parcelTemp = iteratorParcel.next();
            float sumAreaBuildings = 0F;
            for (String innerCN : parcelTemp.getInnerCadastralNumbers()) {
                sumAreaBuildings = sumAreaBuildings + Optional.ofNullable(buildingCNArea.get(innerCN)).orElse(0F);
            }
            if (sumAreaBuildings / parcelTemp.getArea() > shareAreaBuildings()) iteratorParcel.remove();
        }
        return parcelListToCheckArea;
    }

    @Override
    public List<String> excludeTagsTemplate() {
        return List.of("животноводство", "скотоводство", "садоводство", "звероводство", "птицеводство", "пчеловодство", "свиноводство", "рыбоводство", "индивидуальный", "отдых",
                "жилой", "дачный", "овощеводство", "личный", "строительство");
    }

    @Override
    public Conditions codeOnlyCondition() {
        return new Conditions(List.of("[^\\d\\.]" + "1\\s*\\.\\s*1" + "[^\\.\\d]"),
                excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F);
    }

    @Override
    public List<Conditions> conditionsList() {
        return List.of(
                new Conditions(List.of("сельскохозяйственное", "использование"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F),
                new Conditions(List.of("сельскохозяйственное", "производство"),
                        List.of(), 200000F, Float.POSITIVE_INFINITY, 0.1F),
                new Conditions(List.of("сельскохозяйственная", "деятельность"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F),

                new Conditions(List.of("сельскохозяйственные", "угодья"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F),

                new Conditions(List.of("крестьянское", "хозяйство"),
                        excludeTagsTemplate(), 100000F, Float.POSITIVE_INFINITY, 0.1F),
                new Conditions(List.of("фермерское", "хозяйство"),
                        excludeTagsTemplate(), 100000F, Float.POSITIVE_INFINITY, 0.1F),

                new Conditions(List.of("многолетние", "насаждения"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F),

                new Conditions(List.of("растениеводство"),
                        excludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0.1F)
        );
    }


    @Override
    public String usageCode() {
        return "01:010";
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
        return List.of(
                //только для сельхоз
                "0801", "0902", "0904", "0905", "0907",
                //линейные объекты
                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031",
                //только для сельхоз
                "1009");
    }

    @Override
    public Float shareAreaBuildings() {
        return 0.05F;
    }
}
