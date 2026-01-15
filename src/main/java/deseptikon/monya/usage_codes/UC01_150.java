package deseptikon.monya.usage_codes;

import deseptikon.monya.spring_jdbc_parcels.jdbc.QueryParcel;
import deseptikon.monya.spring_jdbc_parcels.model.Parcel;
import deseptikon.monya.usage_codes.model.Conditions;
import deseptikon.monya.usage_codes.servives.PrepareTags;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UC01_150 implements PrepareTags {

    //Исключаемые тэги
    List<String> excludeTagsTemplate = List.of("индивиду", "1.7", "обеспеч", "пашн", "обслуживан", "отдых",
            "жил", "дачн", "личн");
    //Поиск кода вида использования и условий
    //Точку внутри кода обязательно экранировать, - иначе воспринимает как любой символ
    Conditions codeOnly = new Conditions(List.of("[^\\d\\.]" + "1\\s*\\.\\s*15" + "[^\\.\\d]"),
            excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F);

    //Поиск тэгов и условий
    List<Conditions> conditionsList = List.of(
            new Conditions(List.of("сельскохозяйственное", "производство"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),
            new Conditions(List.of("зерноток"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),
            new Conditions(List.of("зернохранилище"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),


            new Conditions(List.of("крестьянское", "хозяйство"),
                    excludeTagsTemplate, 0F, 100000F, 0.1F),


            new Conditions(List.of("хранение", "переработка", "сельскохозяйственной"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),

            new Conditions(List.of("фермерское", "хозяйство"),
                    excludeTagsTemplate, 0F, 100000F, 0.1F),

            new Conditions(List.of("производство", "сельскохозяйственной", "продукции"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),

            new Conditions(List.of("зерноочистительный", "комплекс"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),
            new Conditions(List.of("арочный", "фуражный"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),

            new Conditions(List.of("зерносклад"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),
            new Conditions(List.of("картофелехранилище"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),
            new Conditions(List.of("мельничный", "комплекс"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),
            new Conditions(List.of("овощехранилище"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F),

            new Conditions(List.of("сельхозпроизводство"),
                    excludeTagsTemplate, 0F, 1000000F, 0.1F)
    );

    String usageCode = "01:150";

    public void assignmentCode(QueryParcel queryTemplate) throws SQLException {
        Set<Parcel> parcelList = new HashSet<>();

        parcelList.addAll(queryTemplate.getListParcelsByTags(queryTagForCode(codeOnly.getTags()), queryExcludeTags(codeOnly.getExcludeTags()),
                codeOnly.getMoreThisArea(), codeOnly.getLessThisArea()));

        for (Conditions condition : conditionsList) {
            StringBuilder tags = new StringBuilder();
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags = new StringBuilder();
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelList.addAll(queryTemplate.getListParcelsByTagsWithICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));
        }
//        CreateDB.erasePredictedUC();
        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode);

        int codeCount = 0;
        for (Parcel parcel : parcelList) {
            System.out.println(parcel);
            codeCount++;
        }
        System.out.println(codeCount);
    }
}
