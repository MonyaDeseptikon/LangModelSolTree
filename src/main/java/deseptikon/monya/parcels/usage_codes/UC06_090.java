package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.model.UC;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UC06_090 extends UC {
    //Нижние подчеркивания после тэга - это костыль, чтобы не обрезалось окончание
    //Исключаемые тэги.
    List<String> excludeTagsTemplate = List.of("железнодорожный", "перевалочный", "линия_", "электропередача", "казарма", "зерновой", "растениеводство");
    //Поиск кода вида использования и условий
    //Точку внутри кода обязательно экранировать, - иначе воспринимает как любой символ
    Conditions codeOnly = new Conditions(List.of("[^\\d\\.]" + "6\\s*\\.\\s*9" + "[^\\.\\d]"),
            excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F);

    //Поиск тэгов и условий
    List<Conditions> conditionsList = List.of(
            new Conditions(List.of("погрузочный", "терминал"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("склад__"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("промышленная", "база_"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("производственная", "база_"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("промплощадка"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("производственный", "склад__"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("база_", "оптовая", "торговля"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F)
    );


            String usageCode = "06:090";

    @Override
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

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode);


        System.out.println(parcelList.size());
    }


}
