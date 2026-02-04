package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.db.create_tables.ParcelCreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.model.uc.UC;
import deseptikon.monya.parcels.usage_codes.model.uc.UCBuilder;
import deseptikon.monya.parcels.usage_codes.model.uc.checkAreaBuildings;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.*;

public class UC01_150 extends UC implements UCBuilder, checkAreaBuildings {

    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");


        ParcelCreateProvisionalList.erasePredictedUC();

        new UC01_150().assignmentCode(queryTemplate);

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
            if (sumAreaBuildings / parcelTemp.getArea() < shareAreaBuildings()) iteratorParcel.remove();
        }
        return parcelListToCheckArea;
    }

    @Override
    public List<String> excludeTagsTemplate() {
        return List.of("индивидуальный", "пашня_", "отдых__",
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
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("зерноток"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("зернохранилище"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),

                new Conditions(List.of("крестьянское", "хозяйство"),
                        excludeTagsTemplate(), 0F, 100000F, 0.1F),
                new Conditions(List.of("фермерское", "хозяйство"),
                        excludeTagsTemplate(), 0F, 100000F, 0.1F),

                new Conditions(List.of("хранение", "сельскохозяйственной"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("переработка", "сельскохозяйственной"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("производство", "сельскохозяйственной"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),

                new Conditions(List.of("зерноочистительный", "комплекс"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("арочный", "фуражный"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("навес__"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("зерносклад"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("картофелехранилище"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("мельничный", "комплекс"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),
                new Conditions(List.of("овощехранилище"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F),

                new Conditions(List.of("сельхозпроизводство"),
                        excludeTagsTemplate(), 0F, 200000F, 0.1F)
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
        return List.of("0701", "0704", "0705", "0706", "0707", "0710", "0711", "0712", "0713", "0715", "0716", "0717", "0718",
                "0719", "0722", "0723", "0724", "0725", "0726", "0727", "0728", "0729",
                //только для сельхозпроизводства
                "0801", "0902", "0904", "0905", "0907",
                //линейные объекты
//                "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031",
                //только для сельхозпроизводства
                "1009");
    }

    @Override
    public Float shareAreaBuildings() {
        return 0.05F;
    }
}
