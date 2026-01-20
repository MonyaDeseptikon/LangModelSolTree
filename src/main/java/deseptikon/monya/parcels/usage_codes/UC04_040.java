package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.db.create_tables.CreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.model.UC;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UC04_040 extends UC {
    //Нижние подчеркивания после тэга - это костыль, чтобы не обрезалось окончание
    //Исключаемые тэги.
    List<String> excludeTagsTemplate = List.of();
    //Поиск кода вида использования и условий
    //Точку внутри кода обязательно экранировать, - иначе воспринимает как любой символ
    Conditions codeOnly = new Conditions(List.of("[^\\d\\.]" + "4\\s*\\.\\s*4" + "[^\\.\\d]"),
            excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F);

    //Поиск тэгов и условий
    List<Conditions> conditionsList = List.of(
            new Conditions(List.of("магазин"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("продажа", "товаров"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("размещение", "киоска"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("торговая", "палатка"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("коммерческая", "деятельность"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("торговый", "павильон"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("автосалон"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("аптека"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("остановочный", "комплекс"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("оптика"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
            new Conditions(List.of("рекламная", "конструкция"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F)
    );


    String usageCode = "04:040";

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
